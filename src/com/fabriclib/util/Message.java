package com.fabriclib.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message {
	
	private String msgType ;
	private String msg ;
	@JsonIgnore
	private Object obj ;
	
	public Message(){
		
	}
	
	public Message(String msgType,String msg){
		this.msgType  = msgType;
		this.msg  = msg;
	}
	
	public Message(String msgType,String msg,Object obj){
		this.msgType  = msgType;
		this.msg  = msg;
		this.obj  = obj;
	}
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	@JsonIgnore
	public String getMsgHtml() {
		String color = "";
		if(getMsgType().equals("S")){
			color = "style='background-color:rgba(0,255,0,.5)'";
		}else if(getMsgType().equals("E")){
			color = "style='background-color:rgba(255,0,0,.5)'";
		}else{
			color = "style='background-color:rgba(0,0,0,.5)'";
		}
		
		return "<span "+color+">"+msg+"</span>";
	}

	@Override
	public String toString() {
		return "Message [msgType=" + msgType + ", msg=" + msg + ", obj=" + obj
				+ "]";
	}
	
}
