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
		//逻辑代码
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//得到隐藏文本域的值
		String saveCookie = req.getParameter("saveCookie");
		
		//得到验证码
		String code = (String)req.getSession().getAttribute("code");
		String currentCode = req.getParameter("code");
		if(!code.equals(currentCode)){
			req.setAttribute("error", "验证码错误");
			req.getRequestDispatcher("index").forward(req, resp);
			return;
		}
		
		UserInfoDao dao = new UserInfoDao();
		UserInfo user = dao.getUser(username);
		
		if(user!=null){
			if(user.getPassword().equals(password)){
				//登录成功
				req.setAttribute("success", "success");
				//得到session
				HttpSession session = req.getSession();
				
				session.setAttribute("user", user);
				
				if(saveCookie!=null && saveCookie.equals("1")){
					//得到Cookie
					Cookie c1 = new Cookie("username", username);
					c1.setMaxAge(60*60*24*30);
					resp.addCookie(c1);
					
					Cookie c2 = new Cookie("password", password);
					c2.setMaxAge(60*60*24*30);
					resp.addCookie(c2);
				}
				
				req.getRequestDispatcher("index").forward(req, resp);
				
				
				
			}else{
				//密码错误
				req.setAttribute("error", "密码错误");
				req.getRequestDispatcher("index").forward(req, resp);
			}
		}else{
			//用户名不存在
			req.setAttribute("error", "用户名不存在");
			req.getRequestDispatcher("index").forward(req, resp);
		}
	}

	
	//销毁
	public void destroy() {
		
	}

	//初始化
	public void init() throws ServletException {
		
	}
}
