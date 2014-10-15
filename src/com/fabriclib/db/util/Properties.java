package com.fabriclib.db.util;

import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Set;

public final class Properties {

	private static java.util.Properties properties = new java.util.Properties();
	static{
		try{
			InputStream inStream  = Properties.class.getResourceAsStream("/system-info.prop");
			properties.load(inStream);
			
			list();
			inStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void list(){
		Set<Entry<Object, Object>> entries = properties.entrySet();
		
		Object[] objects = entries.toArray();
		
		for (Object obj:objects) {
			Entry<Object, Object> entry = (Entry<Object, Object>) obj;
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
		
	}
    public final static String LOG_PREFIX = properties.getProperty("log-prefix");
    public final static String ENCODING = properties.getProperty("encoding-servlet");
}
