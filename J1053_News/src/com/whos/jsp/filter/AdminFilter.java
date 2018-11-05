package com.whos.jsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.whos.jsp.entity.UserInfo;

public class AdminFilter implements Filter{

	//����
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	//ִ��
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("error", "���¼");
			request.getRequestDispatcher("/index").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}

	//��ʼ��
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}