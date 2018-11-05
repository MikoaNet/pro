package com.whos.jsp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPicServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String src = request.getParameter("src");
		//System.out.println(src);
		File file = new File(src);
		FileInputStream input = new FileInputStream(file);
		//接收数组
		byte[] b = new byte[(int)file.length()];
		input.read(b);
		
		response.getOutputStream().write(b);
		
		input.close();
	}

}
