package com.fabriclib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fabriclib.db.util.Properties;
import com.fabriclib.util.CustomLog;

public abstract class BaseServlet extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		CustomLog.info("Initial:");
	}

	
	protected void print(HttpServletResponse resp,String info) throws IOException{
		PrintWriter out = resp.getWriter();
		out.print(info.toString());
		out.flush();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		req.getContentType();
		resp.setCharacterEncoding(Properties.ENCODING);
		req.setCharacterEncoding(Properties.ENCODING);
			doGetDoer(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding(Properties.ENCODING);
		req.setCharacterEncoding(Properties.ENCODING);
		
			doPostDoer(req, resp);
	}
	
	
	protected abstract void doPostDoer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	protected abstract void doGetDoer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
