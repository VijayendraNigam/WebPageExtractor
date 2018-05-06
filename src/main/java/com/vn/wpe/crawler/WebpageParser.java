package com.vn.wpe.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebpageParser {

	private static final Logger LOGGER = Logger.getLogger(WebpageParser.class.getName());

	public static List<String> getGoogleSearchResultUrls(Document document) {

		List<String> urls = new ArrayList<>();
		try {
			Elements searchLinks = document.select("div[class=g]");

			for (Element link : searchLinks) {
				Elements title = link.select("h3[class=r]");
				//LOGGER.info("Title : " + title.text());

				Elements getLink = title.select("a[href]");
				//LOGGER.info("Link : " + getLink.attr("abs:href"));

				urls.add(getLink.attr("abs:href"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return urls;
	}

	public static List<String> getJSLibraries(Document document) {

		List<String> jsList = new ArrayList<>();
		try {
			Elements scriptTags = document.select("script[src]");
			for (Element script : scriptTags) {
				//LOGGER.info("Tag type: " + script.tagName());
				//LOGGER.info("Link : " + script.attr("abs:src"));
				//LOGGER.info("Type Element : " + script.attr("type"));

				jsList.add(script.attr("abs:src"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsList;
	}

}

