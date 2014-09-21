package com.fabriclib.servlet;

import java.io.File;
import java.io.IOException;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fabriclib.db.tables.ts.Fabric;
import com.fabriclib.db.tables.ts.FabricIO;
import com.fabriclib.util.CustomLog;
import com.fabriclib.util.Jackson;

//@WebServlet(name="mytest", 
//urlPatterns={"/myurl"}, 
//initParams={ @InitParam(name="n1", value="v1"), @InitParam(name="n2", value="v2") }) 
@WebServlet(name = "UploadFabricServlet", description = "upload fabric ", urlPatterns = { "/UploadFabric" }, initParams = {
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
		StringBuffer html = new StringBuffer();
		File file;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext context = req.getServletContext();
		String filePath = context.getInitParameter("file-upload");

		// 验证上传内容了类型
		String contentType = req.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存中存储文件的最大值
			factory.setSizeThreshold(maxMemSize);
			// 本地存储的数据大于 maxMemSize.
			factory.setRepository(new File("c:\\temp"));

			// 创建一个新的文件上传处理程序
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大上传的文件大小
			upload.setSizeMax(maxFileSize);
			try {
				// 解析获取的文件
				List fileItems = upload.parseRequest(req);

				// 处理上传的文件
				Iterator i = fileItems.iterator();

				html.append("file uploaded!");

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// 获取上传文件的参数
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						// 写入文件
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath,
									fileName.substring(fileName
											.lastIndexOf("\\")));
						} else {
							file = new File(filePath,
									fileName.substring(fileName
											.lastIndexOf("\\") + 1));
						}
						fi.write(file);
						html.append("Uploaded Filename: " + filePath + fileName
								+ "<br>");
					}
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else {
			html.append("no file uploaded!");
		}
		CustomLog.info(html.toString());

		print(resp, html.toString());
	}

	@Override
	protected void doGetDoer(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
