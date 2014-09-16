package com.fabriclib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.util.DatabaseIO;
import com.fabriclib.util.CustomLog;

@WebServlet(name = "AddUserServlet", asyncSupported = true, description = "add fabric", urlPatterns = { "/AddUser" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class AddUser extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomLog.info(this.getClass().getName() + ".POST ");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		CustomLog.info("username:" + username);
		CustomLog.info("password:" + password);
		PrintWriter out = resp.getWriter();

		StringBuffer html = new StringBuffer("div");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		try {
			if (DatabaseIO.save(user)) {
				html.append("user:").append(username)
						.append(" saving is successful!");
			} else {
				html.append("user:").append(username)
						.append(" saving is failed!");
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

		html.append("</div>");
		CustomLog.info(html.toString());
		out.print(html.toString());
		out.flush();
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		CustomLog.info(this.getServletName() + ".GET ");
		out.print("This servlet should be request by POST modole!");
	}
}
