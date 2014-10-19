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
    public static String LANGUAGE = "en";
    public static String msg1(String s1){
    	if(LANGUAGE.equals("en")){
    		return "parse request items error:"+s1;
    	}else{
    		return "解析请求错误:"+s1;
    	}
    }
	public static String msg2(String hangerNo, String message) {
		if(LANGUAGE.equals("en")){
    		return "hangerNo:" + hangerNo
    				+ ". saving is failed!" + message;
    	}else{
    		return "hangerNo:" + hangerNo
    				+ ". 保存失败!" + message;
    	}
	}
	public static String msg3(String hangerNo) {
		if(LANGUAGE.equals("en")){
    		return "The hangerNo " + hangerNo + " is existed.";
    	}else{
    		return "记录 hangerNo " + hangerNo + " 已经存在.";
    	}
	}
	public static String msg4() {
		
		// TODO Auto-generated method stub
		if(LANGUAGE.equals("en")){
    		return "Save to database successfuly！";
    	}else{
    		return "数据保存成功！";
    	}
	}
}
