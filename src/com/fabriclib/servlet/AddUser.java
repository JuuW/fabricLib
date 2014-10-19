package com.fabriclib.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.tables.user.UserIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Message;

@WebServlet(name = "AddUserServlet", asyncSupported = true, description = "add fabric", urlPatterns = { "/AddUser.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class AddUser extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CustomLog.info(this.getClass().getName() + ".POST ");
		

		

		StringBuffer html = new StringBuffer("<div>");

		
		User user =  new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		try {
			Message msg = UserIO.save(user);
			html.append(msg.getMsgType() + ":  "+ msg.getMsg());
		} catch (Exception e) {
			html.append("User:").append(user.getUsername())
			.append(". saving is failed!");
			e.printStackTrace();
		}

		html.append("</div>");
				
		CustomLog.info(html.toString());
		
		print(resp,html.toString());
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		print(resp,"This servlet should be request by POST modole!");
	}
}
