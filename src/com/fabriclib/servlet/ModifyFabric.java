package com.fabriclib.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.tables.user.UserIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Message;

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
		List<Message> msglist = new ArrayList<Message>();
		
		Fabric fabric =  new Fabric();
		fabric.setContent(req.getParameter("content"));
		try {
			boolean msg = FabricIO.update(fabric);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msglist.add(new Message("E","parse request items error:"));
		print(resp, Jackson.getJson(msglist));
	}

	

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}
