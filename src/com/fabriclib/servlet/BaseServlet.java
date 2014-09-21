package com.fabriclib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.util.CustomLog;

public abstract class BaseServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		CustomLog.info("Initial:");
	}

	
	protected void print(HttpServletResponse resp,String info) throws IOException{
		CustomLog.info(info);
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
		
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
//		try {
			doGetDoer(req, resp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			print(resp,e.getMessage());
//			throw e;
//		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
	
			doPostDoer(req, resp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			print(resp,e.getMessage());
//			throw e;
//		}
		
		
	}
	
	
	protected abstract void doPostDoer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	protected abstract void doGetDoer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
