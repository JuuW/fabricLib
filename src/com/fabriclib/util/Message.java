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
			color = "class = 'SucMsg'";
		}else if(getMsgType().equals("E")){
			color = "class = 'errMsg'";
		}else{
			color = "";
		}
		
		return "<div "+color+">"+msg+"</div>";
	}
    
}
