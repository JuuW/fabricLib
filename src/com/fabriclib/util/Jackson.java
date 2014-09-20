package com.fabriclib.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fabriclib.db.tables.user.User;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Jackson {
	public static String getJson(List items) {
//		 ObjectWriter ow = new
//		 ObjectMapper().writer().withDefaultPrettyPrinter();
		String result = "";
		ObjectMapper mapper = new ObjectMapper();

		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
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

	public static String getJson(Object item) {
		// ObjectWriter ow = new
		// ObjectMapper().writer().withDefaultPrettyPrinter();
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		// to enable standard indentation ("pretty-printing"):
//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
//		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		// to write java.util.Date, Calendar as number (timestamp):
//		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// DeserializationFeature for changing how JSON is read as POJOs:

		// to prevent exception when encountering unknown property:
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// to allow coercion of JSON empty String ("") to null Object value:
		mapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		// JsonParser.Feature for configuring parsing settings:

		// to allow C/C++ style comments in JSON (non-standard, disabled by default)
//		mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
		// to allow (non-standard) unquoted field names in JSON:
//		mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		// to allow use of apostrophes (single quotes), non standard
//		mapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

		// JsonGenerator.Feature for configuring low-level JSON generation:

		// to force escaping of non-ASCII characters:
//		mapper.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
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
		User user = new User();
		user.setUsername("jaso");
		user.setPassword("jaso");
		List users = new ArrayList<User>();
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		users.add(user);
		// System.out.println(getJson(user));
		System.out.println(getJson(users));
	}

}
