package com.fabriclib.util;

import org.apache.log4j.Logger;

import com.fabriclib.db.util.Properties;

public class CustomLog {
	private static Logger log = Logger.getLogger(Logger.class);
	
	public static void info(String msg){
		log.info(Properties.LOG_PREFIX+msg);
	}
	public static void error(String msg){
		log.error(Properties.LOG_PREFIX+msg);
	}
	public static void debug(String msg){
		log.debug(Properties.LOG_PREFIX+msg);
	}
}
