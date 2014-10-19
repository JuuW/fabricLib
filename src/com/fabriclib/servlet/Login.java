package com.fabriclib.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.tables.user.UserIO;
import com.fabriclib.db.util.Properties;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Tool;

@WebServlet(name = "LoginServlet", description = "log in", urlPatterns = { "/Login.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class Login extends BaseServlet{

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String language = req.getParameter("language");
		CustomLog.info("Loginfo: username ="+username);
		User user = null;
		if(Tool.isNotEmpty(username)&&Tool.isNotEmpty(password)){
			user = UserIO.login(username,password);
			if (user!=null) {
				req.getSession().setAttribute("username", username);
				req.getSession().setAttribute("language", language);
				Properties.LANGUAGE = language;
			}else{
				
			}
		}
		String returnData = Jackson.getJson(user);
		CustomLog.info(returnData);
		print(resp, returnData);
		
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPostDoer(req,resp);
	}

}
