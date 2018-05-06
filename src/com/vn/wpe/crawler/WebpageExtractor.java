package com.vn.wpe.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vn.wpe.common.Constants;

public interface WebpageExtractor {

	public static Document extract(String url) throws IOException {
		
		return Jsoup.connect(url)
					.timeout(Constants.URL_CONNECTION_TIMEOUT)
					.get();
	}

}