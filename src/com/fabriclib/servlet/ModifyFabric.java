package com.fabriclib.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fabriclib.db.tables.ts.FabricIO;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "ModifyFabricServlet", description = "modify fabric ", urlPatterns = { "/ModifyFabric.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class ModifyFabric extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ModifyFabric.class);
	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String id =req.getParameter("fid");
		String column =req.getParameter("column");
		String value =req.getParameter("value");
//		value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		log.info("id="+id);
		log.info("value="+value);
		log.info("column="+column);
		
        String result = "false";
		
		try {
			if(FabricIO.updateColumn(Long.valueOf(id),column,value)){
				result= value;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		print(resp,result);
	}

	

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}
