package com.fabriclib.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fabriclib.db.tables.user.User;

public class Jackson {
	public static String getJson(List items){
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String result ="";
		ObjectMapper mapper = new ObjectMapper();
				 try {
				 result = mapper.writeValueAsString(items);
				 } catch (JsonGenerationException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 } catch (JsonMappingException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 }
		return result;
	}
	
	public static String getJson(Object item){
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String result ="";
		ObjectMapper mapper = new ObjectMapper();
				 try {
				 result = mapper.writeValueAsString(item);
				 } catch (JsonGenerationException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 } catch (JsonMappingException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 }
		return result;
	}
	
	public static void main(String[] args) {
		User user =  new User();
		user.setUsername("jaso");
		user.setPassword("jaso");
		List users = new ArrayList<User>();
		users.add(user);
		users.add(user);
		System.out.println(getJson(user));
		System.out.println(getJson(users));
	}

}
