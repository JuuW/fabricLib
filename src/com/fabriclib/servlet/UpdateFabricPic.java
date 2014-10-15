package com.fabriclib.servlet;

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

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;
import com.fabriclib.util.Message;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "UpdateFabricPicServlet", description = "UpdateFabricPic", urlPatterns = { "/UpdateFabricPic.do" }, initParams = {
		@WebInitParam(name = "mock", value = "mock"),
		@WebInitParam(name = "mock", value = "mock") })
public class UpdateFabricPic extends BaseServlet {
	private static final long serialVersionUID = 1L;

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
						paras.put(item.getFieldName(), item.getString());
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
		fabric.setId(Long.valueOf(paras.get("hangerId")));
		
		CustomLog.info("input paramater:"+Jackson.getJson(fabric));
		try {
			saveFile(req,fileitem,fabric.getId());
		} catch (Exception e) {
			msglist.add(new Message("E", "hangerId:" + fabric.getId()
					+ ". pic saving is failed!" + e.getMessage()));
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
