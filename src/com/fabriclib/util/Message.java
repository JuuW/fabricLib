package com.fabriclib.util;

public class Message {
	
	private String msgType ;
	private String msg ;
	
	public Message(){
		
	}
	
	public Message(String msgType,String msg){
		this.msgType  = msgType;
		this.msg  = msg;
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
    
}
