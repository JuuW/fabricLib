package com.fabriclib.util;

import org.apache.log4j.Logger;

import com.fabriclib.db.util.Properties;

public class CustomLog {
	private static Logger log = Logger.getLogger(Logger.class);
	
	public static void info(Object msg){
		log.info(Properties.LOG_PREFIX+msg);
	}
	public static void error(Object msg){
		log.error(Properties.LOG_PREFIX+msg);
	}
	public static void debug(Object msg){
		log.debug(Properties.LOG_PREFIX+msg);
	}
}
