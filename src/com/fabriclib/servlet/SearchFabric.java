package com.fabriclib.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Tool;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "SearchFabricServlet", description = "search fabric ", urlPatterns = { "/SearchFabric.do" }, initParams = {
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
		String hangerNo = req.getParameter("hangerNo");
		if(Tool.isNotEmpty(hangerNo)){
			fabric.setHangerNo(hangerNo);
		}
		String inputDate = req.getParameter("inputDate");
		if(Tool.isNotEmpty(inputDate)){
			fabric.setInputDate(Tool.dateToString(inputDate));
		}
		String cstructnWarp = req.getParameter("cstructnWarp");
		if(Tool.isNotEmpty(cstructnWarp)){
			fabric.setCstructnWarp(cstructnWarp);
		}
		String cstructnWeft = req.getParameter("cstructnWeft");
		if(Tool.isNotEmpty(cstructnWeft)){
			fabric.setCstructnWeft(cstructnWeft);
		}
		String yarnWarp = req.getParameter("yarnWarp");
		if(Tool.isNotEmpty(yarnWarp)){
			fabric.setYarnWarp(yarnWarp);
		}
		String yarnWeft = req.getParameter("yarnWeft");
		if(Tool.isNotEmpty(yarnWeft)){
			fabric.setYarnWeft(yarnWeft);
		}
		String content = req.getParameter("content");
		if(Tool.isNotEmpty(content)){
			fabric.setContent(content);
		}
		String status = req.getParameter("status");
		if(Tool.isNotEmpty(status)){
			fabric.setStatus(status);
		}
		String weaving = req.getParameter("weaving");
		if(Tool.isNotEmpty(weaving)){
			fabric.setWeaving(weaving);
		}
		String finishing = req.getParameter("finishing");
		if(Tool.isNotEmpty(finishing)){
			fabric.setFinishing(finishing);
		}

		String width = req.getParameter("width");
		if(Tool.isNotEmpty(width)){
			fabric.setWidth(width);
		}
		
		String weight = req.getParameter("weight");
		if(Tool.isNotEmpty(weight)){
			fabric.setWeight(weight);
		}
		String article = req.getParameter("article");
		if(Tool.isNotEmpty(article)){
			fabric.setArticle(article);
		}
		String originalPrice = req.getParameter("originalPrice");
		if(Tool.isNotEmpty(originalPrice)){
			fabric.setOriginalPrice(originalPrice);
		}
		String finalPrice = req.getParameter("finalPrice");
		if(Tool.isNotEmpty(finalPrice)){
			fabric.setFinalPrice(finalPrice);
		}
		
		List<Fabric> items = FabricIO.getByExample(fabric);
		String returnData = Jackson.getJson(items);
		CustomLog.info(returnData);
		print(resp, returnData);
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPostDoer(req,resp);

	}

}
