package com.fabriclib.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class NullSerializer extends StdSerializer<Object> {



protected NullSerializer(Class<Object> arg0) {
		super(arg0);
	}

//	@Override
//	public Class<Object> handledType() {
//		// TODO Auto-generated method stub
//		return super.handledType();
//	}
	@Override
	public void serialize(Object arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		if(arg0 == null){
			
			arg1.writeString("");
		}else{
			arg1.writeObject(arg0);
		}
		
	}

}
