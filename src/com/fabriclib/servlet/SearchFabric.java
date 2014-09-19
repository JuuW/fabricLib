package com.fabriclib.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "SearchFabricServlet", description = "search fabric ", urlPatterns = { "/SearchFabric" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class SearchFabric extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fabric fabric = new Fabric();
		fabric.setHangerNo(req.getParameter("hangerNo"));
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
		
		List<Fabric> items = FabricIO.getByExample(fabric);

		StringBuffer html = new StringBuffer("");
//		StringBuffer html = new StringBuffer("<div>");
//
//		if(!items.isEmpty()){
//			html.append("<table>");
//			html.append("<tr><th>Hanger No.</th><th>Article</th></tr>");
//			for (Fabric item : items) {
//				html.append("<tr><td>").append(item.getHangerNo()).append("</td></td>").append(item.getArticle()).append("</td></tr>");
//			}
//			html.append("</table>");
//		}

		html.append(Jackson.getJson(items));
//		html.append("</div>");
		CustomLog.info(html.toString());

		print(resp, html.toString());
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
