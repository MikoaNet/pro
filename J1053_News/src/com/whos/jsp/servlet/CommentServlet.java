package com.whos.jsp.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whos.jsp.dao.CommentInfoDao;
import com.whos.jsp.dao.NewsInfoDao;
import com.whos.jsp.entity.CommentInfo;
import com.whos.jsp.entity.NewsInfo;
import com.whos.jsp.util.CommenMethod;

public class CommentServlet extends HttpServlet {

	private CommentInfoDao dao = new CommentInfoDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("read")){
			read(request,response);
		}else if(method.equals("save")){
			save(request,response);
		}else if(method.equals("del")){
			del(request,response);
		}
		
	}
	
	//��ת��readҳ��
	public void read(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�������Ϣ
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		NewsInfo news = new NewsInfoDao().getNews(newsId);
		news.setNewsPic(URLEncoder.encode(news.getNewsPic(),"UTF-8"));
		request.setAttribute("news", news);
		//�õ�����IP
		/*String ip = request.getLocalAddr();*/
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		
		//�õ�����
		List<CommentInfo> commentList = dao.getComment(newsId);
		request.setAttribute("commentList", commentList);
		request.setAttribute("ip", ip);
		
		request.setAttribute("internationalList", CommenMethod.internationalList);
		request.setAttribute("inlandList", CommenMethod.inlandList);
		request.setAttribute("enList", CommenMethod.enList);
		
		request.getRequestDispatcher("/newspages/news_read.jsp").forward(request, response);
	}
	
	//��������
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		String username = request.getParameter("username");
		String ip = request.getParameter("ip");
		String content = request.getParameter("content");
		
		CommentInfo comment = new CommentInfo();
		comment.setContent(content);
		comment.setIp(ip);
		comment.setNewsId(newsId);
		comment.setUsername(username);
		
		//����
		boolean success = dao.saveComment(comment);
		if(!success){
			request.setAttribute("error", "����ʧ��");
		}
		read(request, response);
	}
	
	//ɾ������
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		
		//��������idɾ������
		boolean success = dao.delComment(commentId);
		if(!success){
			request.setAttribute("error", "ɾ��ʧ��");
		}
		read(request, response);
	}
}
