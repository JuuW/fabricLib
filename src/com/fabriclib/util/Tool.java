package com.fabriclib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {
	public static Date dateToString(String dateStr, String format) {
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat(format);
		String str = formatDate.format(dateStr);
		try {
			date = formatDate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date dateToString(String dateStr) {
		return dateToString(dateStr, "yyyy-MM-dd");
	}

	public static boolean isEmpty(String str) {
		boolean result = true;

		if (str != null) {
			if (!str.equals("")) {
				if (!str.isEmpty()) {
					result = false;
				}
			}
		}
		return result;
	}
}
