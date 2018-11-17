package com.flizzet.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities for Date and Time.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class DateUtils {

	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM/dd/yy HH:mm:ss");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM/dd/yy");
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss");
	private static final DateFormat TIME_FORMAT_NO_SECONDS = new SimpleDateFormat(
			"HH:mm");
	private static final Date DATE = new Date();

	// FIXME Time is constantly the same from system start
	/** Suppress default constructor for noninstantiability */
	private DateUtils() {
		throw new AssertionError();
	}

	/** Returns the DATE with time */
	public static String getDateAndTime(boolean showSeconds) {
		if (showSeconds) {
			return (DATE_TIME_FORMAT.format(DATE));
		}
		return (DATE_FORMAT.format(DATE)) + " "
				+ (TIME_FORMAT_NO_SECONDS.format(DATE));
	}

	/** Returns date */
	public static String getDate() {

		return (DATE_FORMAT.format(DATE));
	}

	/** Returns time */
	public static String getTime(boolean seconds) {
		if (seconds) {
			return (TIME_FORMAT.format(DATE));
		}
		return (TIME_FORMAT_NO_SECONDS.format(DATE));
	}

}