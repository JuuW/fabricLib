package com.fabriclib.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.tables.user.User;
import com.fabriclib.db.tables.user.UserIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Message;

@WebServlet(name = "DeleteFabricServlet", asyncSupported = true, description = "delete fabric", urlPatterns = { "/DeleteFabric.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class DeleteFabric extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DeleteFabric.class);
	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		long id = Long.valueOf( req.getParameter("hangerId"));
		log.info("-----Delete " +id);
		
//		Fabric fabric = new Fabric();
//		fabric.setId(id);
//		
//		List<Fabric> items = FabricIO.getByExample(fabric);
//		
//		Fabric fabricResult = items.get(0);
//		
//		fabricResult.setDeleted("T");
//		fabricResult.setFinishing("==========");
		String result = "false";
		
		try {
			result = String.valueOf(FabricIO.updateDelete(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		print(resp,result);
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		print(resp,"This servlet should be request by POST modole!");
	}
}
