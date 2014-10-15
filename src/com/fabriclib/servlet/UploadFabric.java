package com.fabriclib.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.db.util.Properties;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Message;
import com.fabriclib.util.Tool;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "UploadFabricServlet", description = "upload fabric ", urlPatterns = { "/UploadFabric.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class UploadFabric extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// StringBuffer html = new StringBuffer();
		StringBuffer html = new StringBuffer("<div>");

		String fileStr = getFile(req, resp);
		List<Fabric> items = getList(fileStr);
		try {
			Message msg =  FabricIO.saveOrUpdateList(items);
			html.append(msg.getMsgType() + ":  "+ msg.getMsgHtml());
			
		} catch (Exception e) {
			Message msg =  new Message("E","Upload is failed!"+e.getMessage());
			
			html.append(msg.getMsgType() + ":  "+ msg.getMsgHtml());

			e.printStackTrace();
		}
		html.append("</div>");
		
		CustomLog.info(html.toString());
		
		print(resp,html.toString());
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	
	private List<Fabric> getList(String fileStr) {
		List<Fabric> items = new ArrayList<Fabric>();
		CustomLog.info(fileStr);
		String[] linesStr = fileStr.split("\r\n");
		
		for (int i = 1; i < linesStr.length; i++) {
			String lineStr = linesStr[i];
			CustomLog.info(lineStr);
			if(Tool.isNotEmpty(lineStr)){
				CustomLog.info(lineStr);
				lineStr = lineStr + ",==mock==";
				String[] column = lineStr.split(",");
				
				for (int j = 0; j < column.length; j++) {
					System.err.println(column[j]);
				}
				Fabric fabric = new Fabric();
				fabric.setHangerNo(column[0]);
				fabric.setCstructnWarp(column[1]);
				fabric.setCstructnWeft(column[2]);
				fabric.setYarnWarp(column[3]);
				fabric.setYarnWeft(column[4]);
				fabric.setContent(column[5]);
				fabric.setStatus(column[6]);
				fabric.setWeaving(column[7]);
				fabric.setFinishing(column[8]);
				fabric.setWidth(column[9]);
				fabric.setArticle(column[10]);
				fabric.setOriginalPrice(column[11]);
				fabric.setFinalPrice(column[12]);
				fabric.setInputDate(new Date());
				CustomLog.info(fabric.toString());
				items.add(fabric);
			}
			
		}

		return items;

	}

	private String getFile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 如果是文件上传类型
		StringBuffer fileStr = new StringBuffer();
//		PrintWriter out = resp.getWriter();
		if (ServletFileUpload.isMultipartContent(req)) {
			// 得到文件上传工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(5000 * 1024);
			factory.setRepository(new File("c:/temp"));

			// 处理文件上传核心类
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setFileSizeMax(50000 * 1024);
			// 设置文件上传类的编码格式
			fileUpload.setHeaderEncoding("UTF-8");
			// 集合数据 : FileItem对象 注意: 每一个表单域 对应一个 FileItem对象(封装)
		
				try {
					List<FileItem> fileItemList = fileUpload.parseRequest(req);
					for (FileItem item : fileItemList) {
						// 如果这个文本域是文件类型的
						if (!item.isFormField()) {
							CustomLog.info(item.getFieldName());
							InputStream inputStream =  item.getInputStream();
							BufferedInputStream bis=new BufferedInputStream(inputStream);
						
							int tempbyte;
				            while ((tempbyte = bis.read()) != -1) {
				            	char c = (char) tempbyte;
				                System.out.write(tempbyte);
				                System.out.print("=="+tempbyte);
				                fileStr.append(c);
				            }
							
						} else {
//                           CustomLog.info(item.getFieldName());
//                           CustomLog.info(item.getString());
						}
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return fileStr.toString();
	}
}
