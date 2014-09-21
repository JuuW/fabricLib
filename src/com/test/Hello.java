package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.util.CustomLog;
import com.test.db.DatabaseIO;
import com.test.db.table.User;

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Hello() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		CustomLog.info("Hello");
	}

	// /**
	// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// */
	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// }

	public void doPostDoer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String linesS = request.getParameter("lines");

		int lines = 0;

		try {
			lines = Integer.valueOf(linesS);
		} catch (Exception e) {
			System.out.println("行数cuowu");
		}
		CustomLog.info("用户:" + username);
		CustomLog.info("密码:" + password);
		CustomLog.info("行数:" + lines);
		PrintWriter out = response.getWriter();
		// try {
		// String result = FileAccessUtil.readFile(path);

		response.setContentType("text/html");
		out.println("<h2>Hello " + username + "</h2>");
		out.println("<table>");
		for (int i = 0; i < lines; i++) {
			out.println("<tr>");
			for (int j = 0; j < lines; j++) {
				out.println("<td> " + i + j + " </td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");

		out.println("<div id = 'msg'>");
		try {
			DatabaseIO.save(new User(username, password));
			out.println("<div id = 'info_msg'>");
			out.println("保存用户" + username + "成功!");
			out.println("</div>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println("<div id = 'err_msg'>");
			out.println("保存用户" + username + "失败!<br>");
			out.println(e.getMessage());
			out.println("</div>");
			e.printStackTrace();
		}
		out.println("</div>");

		// } catch (Exception ex) {
		// ex.printStackTrace();
		// response.setContentType("text/html; charset=GBK");
		// out.println( ex.getMessage() </font><br><br>");
		// }finally{
		out.flush();
		// out.close();
		// }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		try {
			doPostDoer(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			out.print(e.getMessage());
//			out.flush();
//			throw e;
//		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
