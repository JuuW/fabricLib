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

	public static boolean isNotEmpty(Object obj) {
		boolean result = false;
        
		String str;
		
		if (obj != null) {
			
			str = obj.toString().trim();
			if (!str.equals("")) {
				if (!str.isEmpty()) {
					
					result = true;
				}
			}
		}
		return result;
	}
}
