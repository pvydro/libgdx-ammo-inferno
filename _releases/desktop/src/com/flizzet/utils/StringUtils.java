package com.flizzet.utils;

/**
 * Utils for Strings.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StringUtils {

    /** Suppress default constructor for noninstantiability */
    private StringUtils() {
	throw new AssertionError();
    }

    /** Capitalizes the first letter of a String
     * @param s The initial String to be capitalized */
    public static String capitalizeFirstLetter(String s) {
	if (s == null || s.length() == 0) {
	    return s;
	}
	return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
