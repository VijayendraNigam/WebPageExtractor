package com.vn.wpe.pattern;

public interface PatternMatcher {

	public static boolean match(String input, String pattern) {
		
		/**
		 * This component could be enhanced to better pattern matching :
		 * - KMP algorithm
		 * - NLP machine learning (Fuzzy logic)
		 */
		return input.contains(pattern);
	}
}

