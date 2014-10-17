package com.fabriclib.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Message;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "AddFabricServlet", description = "add fabric ", urlPatterns = { "/AddFabric.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class AddFabric extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AddFabric.class);
	@Override
	protected void doPostDoer(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String path = req.getSession().getServletContext().getRealPath("/");
		FileItem fileitem = null;
		CustomLog.info("path="+path);
		List<Message> msglist = new ArrayList<Message>();
		CustomLog.info(ServletFileUpload.isMultipartContent(req));
		
		Map<String, String> paras = new HashMap<String, String>();
		
		if (ServletFileUpload.isMultipartContent(req)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024 * 10);
			factory.setRepository(new File(path));
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setFileSizeMax(50000 * 1024);
			// 设置文件上传类的编码格式
			fileUpload.setHeaderEncoding("UTF-8");

			try {
				List<FileItem> fileItemList = fileUpload.parseRequest(req);
				CustomLog.info("loop request items,size  =" + fileItemList.size());
				for (FileItem item : fileItemList) {
//					CustomLog.info(item.getFieldName()+"="+item.getString());
					// 如果这个文本域是文件类型的
					if (item.isFormField()) {
						log.info(item.getContentType());
						CustomLog.info(item.getFieldName()+"="+item.getString());
//						String value=new String(item.getString().getBytes("ISO-885Array-1"),"UTF-8");
						String value= item.getString();
//						paras.put(item.getFieldName(), item.getString());
						value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
						paras.put(item.getFieldName(), value);
						
					} else {
						fileitem = item;
					}
				}
			} catch (Exception e) {
				msglist.add(new Message("E","parse request items error:"+e.getMessage()));
				e.printStackTrace();
			}
		}
		
		Fabric fabric = new Fabric();
		fabric.setHangerNo(paras.get("hangerNo"));
		fabric.setInputDate(new Date());
		fabric.setCstructnWarp(paras.get("cstructnWarp"));
		fabric.setCstructnWeft(paras.get("cstructnWeft"));
		fabric.setYarnWarp(paras.get("yarnWarp"));
		fabric.setYarnWeft(paras.get("yarnWeft"));
		fabric.setContent(paras.get("content"));
		fabric.setStatus(paras.get("status"));
		fabric.setWeaving(paras.get("weaving"));
		
//		String finishing = new String(paras.get("finishing").getBytes("ISO-8859-1"),"UTF-8");
		fabric.setFinishing(paras.get("finishing"));
//		fabric.setFinishing(paras.get("finishing"));
		fabric.setWidth(paras.get("width"));
		fabric.setWeight(paras.get("weight"));
		fabric.setArticle(paras.get("article"));
		fabric.setOriginalPrice(paras.get("originalPrice"));
		fabric.setFinalPrice(paras.get("finalPrice"));
//		fabric.setDeleted("");
		fabric.setPrintOut("");

		CustomLog.info("input paramater:"+Jackson.getJson(fabric));
		try {
			Message message = FabricIO.save(fabric);
			msglist.add(message);
			saveFile(req,fileitem,message.getObj());
		} catch (Exception e) {
			msglist.add(new Message("E", "hangerNo:" + fabric.getHangerNo()
					+ ". saving is failed!" + e.getMessage()));
			e.printStackTrace();
		}
		
		print(resp, Jackson.getJson(msglist));
	}

	private void saveFile(HttpServletRequest req,FileItem item,Object obj) throws Exception {
		CustomLog.info("read file");
		String value = item.getName();// input content
		String path = req.getSession().getServletContext().getRealPath("/");
		value = value.substring(value.lastIndexOf("\\") + 1,
				value.length());
		value = "fabric_"+obj+".jpg";
		CustomLog.info("new file name:"+value);
		OutputStream fileOutStream = new FileOutputStream(
				new File(path+"img/", value));

		InputStream fileInputStream = item.getInputStream();
		byte[] buffer = new byte[1024];

		// read

		int length = 0;

		while ((length = fileInputStream.read(buffer)) > 0) {

			fileOutStream.write(buffer, 0, length);

		}
		fileInputStream.close();

		fileOutStream.close();

//		item.write(new File(path, value));
		
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

}
