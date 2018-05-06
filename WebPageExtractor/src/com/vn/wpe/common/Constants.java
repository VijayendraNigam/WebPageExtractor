package com.vn.wpe.common;

public class Constants {

	/**
	 * Default timeout set to 1 minute.
	 */
	public static final int URL_CONNECTION_TIMEOUT = 60 * 1000;

	/**
	 * Default threadpool is set to 10 since there could be at max 10 search results
	 * pages per google search.
	 */
	public static final int THREADPOOL_SIZE = 10;

	/**
	 * Default set to India location.
	 */
	public static final String GOOGLE_DOMAIN = "www.google.co.in";

	/**
	 * Default set to 5
	 */
	public static final int RESULT_COUNTS = 5;

}
