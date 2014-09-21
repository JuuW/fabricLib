package com.fabriclib.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Message;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "AddFabricServlet", description = "add fabric ", urlPatterns = { "/AddFabric" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class AddFabric extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Fabric fabric =  new Fabric();
		fabric.setHangerNo( req.getParameter("hangerNo"));
		fabric.setInputDate( new Date());
		fabric.setCstructnWarp( req.getParameter("cstructnWarp"));
		fabric.setCstructnWeft( req.getParameter("cstructnWeft"));
		fabric.setYarnWarp( req.getParameter("yarnWarp"));
		fabric.setYarnWeft( req.getParameter("yarnWeft"));
		fabric.setContent( req.getParameter("content"));
		fabric.setStatus( req.getParameter("status"));
		fabric.setWeaving( req.getParameter("weaving"));
		fabric.setFinishing( req.getParameter("finishing"));
		fabric.setWidth( req.getParameter("width"));
		fabric.setWeight( req.getParameter("weight"));
		fabric.setArticle( req.getParameter("article"));
		fabric.setOriginalPrice( req.getParameter("originalPrice"));
		fabric.setFinalPrice( req.getParameter("finalPrice"));
		req.getSession().getAttribute("username");

		StringBuffer html = new StringBuffer("<div>");

		try {
			Message msg = FabricIO.save(fabric);
			html.append(msg.getMsgType() + ":  "+ msg.getMsgHtml());
		} catch (Exception e) {
			Message msg =  new Message("E","hangerNo:"+fabric.getHangerNo()
					+". saving is failed!"+e.getMessage());
			
			html.append(msg.getMsgType() + ":  "+ msg.getMsgHtml());
			e.printStackTrace();
		}

		html.append("</div>");
				
		CustomLog.info(html.toString());
		
		print(resp,html.toString());
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}
