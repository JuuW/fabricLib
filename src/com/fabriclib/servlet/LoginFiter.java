package com.fabriclib.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Tool;

public class LoginFiter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)req;  
        HttpServletResponse httpResponse = (HttpServletResponse)resp; 
		String servletPath = httpRequest.getServletPath(); 
		CustomLog.info("request Path is" + servletPath);
		if (servletPath.equals("/login.html")||servletPath.equals("/Login.do")) {  
            chain.doFilter(httpRequest, httpResponse);  
            return;  
        }  
        HttpSession session = httpRequest.getSession();  
        if (Tool.isNotEmpty(session.getAttribute("username"))) {
        	
        	chain.doFilter(httpRequest, httpResponse);  
        } else  {
        	httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");  
	    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
