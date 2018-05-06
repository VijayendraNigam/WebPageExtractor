package com.vn.wpe.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;

import com.vn.wpe.common.Constants;
import com.vn.wpe.common.Helper;
import com.vn.wpe.common.JSLibrary;
import com.vn.wpe.common.JSLibrary.WIKIPEDIA;
import com.vn.wpe.common.MapUtils;
import com.vn.wpe.crawler.WebpageExtractor;
import com.vn.wpe.crawler.WebpageParser;
import com.vn.wpe.pattern.PatternMatcher;

public class Solution {

	private void listTopJSLibraries(String keyword) throws IOException, InterruptedException {

		String googleSearchUrl = Helper.getGoogleSearchUrl(keyword);

		// Extracting the Google search result page and the search result links.
		Document googleSearchResultDocument = WebpageExtractor.extract(googleSearchUrl);
		List<String> googleSearchResultUrls = WebpageParser.getGoogleSearchResultUrls(googleSearchResultDocument);

		Helper.printResults("Google Search Result Urls", googleSearchResultUrls);

		ExecutorService executorService = Executors.newFixedThreadPool(Constants.THREADPOOL_SIZE);
		Set<Callable<List<String>>> callables = new HashSet<Callable<List<String>>>();

		// Asynchronously extracting each search result page and parsing the javascript libraries used.
		for (String searchLink : googleSearchResultUrls) {

			callables.add(new Callable<List<String>>() {

				@Override
				public List<String> call() throws Exception {
					Document searchLinkDocument = WebpageExtractor.extract(searchLink);
					return WebpageParser.getJSLibraries(searchLinkDocument);
				}
			});
		}

		List<Future<List<String>>> futures = executorService.invokeAll(callables);

		// Combining all the javascript libraries search result running across multiple threads
		List<String> jsLibraries = new ArrayList<>();
		for (Future<List<String>> future : futures) {
			try {
				jsLibraries.addAll(future.get());
			} catch (ExecutionException e) {
			}
		}
		executorService.shutdown();

		Helper.printResults("All JS Libraries Extracted", jsLibraries);
		
		// Initializing the MAP of all known javascript libraries with occurrence set to O.
		Map<String, Integer> jsLibraryWikiMap = Helper.getJsLibraryMap(JSLibrary.WIKIPEDIA.getAll());

		// Maintaining the occurrence count of each javascript libraries found in pattern match
		for (String js : jsLibraries) {
			for (JSLibrary.WIKIPEDIA jsLibraryWiki : WIKIPEDIA.values()) {
				String[] patterns = jsLibraryWiki.getPatterns();
				for (String pattern : patterns) {
					if (PatternMatcher.match(js, pattern)) {
						jsLibraryWikiMap.put(jsLibraryWiki.name(), jsLibraryWikiMap.get(jsLibraryWiki.name()) + 1);
						break;
					}
				}
			}
		}
		
		// Sorting the map on values to get the most occurring libraries on top
		jsLibraryWikiMap = MapUtils.sortByValue(jsLibraryWikiMap);

		// Fetching 5 top most occurred javascript libraries
		Helper.printResults("Top Javascript Libraries Used", jsLibraryWikiMap.entrySet()
																			.stream()
																			.limit(Constants.RESULT_COUNTS)
																			.filter(e -> e.getValue() != 0)
																			.map(e -> e.getKey() + "  [ " + e.getValue() + "]")
																	        .collect(Collectors.toList()));

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		Solution solution = new Solution();
		solution.listTopJSLibraries("github");
	}

}
