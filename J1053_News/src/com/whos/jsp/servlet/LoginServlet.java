package com.whos.jsp.servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whos.jsp.dao.UserInfoDao;
import com.whos.jsp.entity.UserInfo;
import com.whos.jsp.util.CommenMethod;

public class LoginServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//�߼�����
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//�õ������ı����ֵ
		String saveCookie = req.getParameter("saveCookie");
		
		//�õ���֤��
		String code = (String)req.getSession().getAttribute("code");
		String currentCode = req.getParameter("code");
		if(!code.equals(currentCode)){
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("index").forward(req, resp);
			return;
		}
		
		UserInfoDao dao = new UserInfoDao();
		UserInfo user = dao.getUser(username);
		
		if(user!=null){
			if(user.getPassword().equals(password)){
				//��¼�ɹ�
				req.setAttribute("success", "success");
				//�õ�session
				HttpSession session = req.getSession();
				
				session.setAttribute("user", user);
				
				if(saveCookie!=null && saveCookie.equals("1")){
					//�õ�Cookie
					Cookie c1 = new Cookie("username", username);
					c1.setMaxAge(60*60*24*30);
					resp.addCookie(c1);
					
					Cookie c2 = new Cookie("password", password);
					c2.setMaxAge(60*60*24*30);
					resp.addCookie(c2);
				}
				
				req.getRequestDispatcher("index").forward(req, resp);
				
				
				
			}else{
				//�������
				req.setAttribute("error", "�������");
				req.getRequestDispatcher("index").forward(req, resp);
			}
		}else{
			//�û���������
			req.setAttribute("error", "�û���������");
			req.getRequestDispatcher("index").forward(req, resp);
		}
	}

	
	//����
	public void destroy() {
		
	}

	//��ʼ��
	public void init() throws ServletException {
		
	}
}
