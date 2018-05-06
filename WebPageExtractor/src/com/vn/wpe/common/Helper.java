package com.vn.wpe.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Helper {

	public static final String getGoogleSearchUrl(String keyword) {

		return "https://" + Constants.GOOGLE_DOMAIN + "/search?q=" + keyword;
	}

	public static final HashMap<String, Integer> getJsLibraryMap(List<String> keys) {
		HashMap<String, Integer> jsLibraryMap = new HashMap<>();
		for (String key : keys) {
			jsLibraryMap.put(key, 0);
		}
		return jsLibraryMap;
	}

	public static void printResults(String message, Collection<?> objects) {

		System.out.println("##########################################################");
		System.out.println("#########      " + message + "      #####");
		System.out.println("##########################################################");

		for (Object object : objects) {
			System.out.println(object);
		}

		System.out.println("##########################################################");
	}
}

