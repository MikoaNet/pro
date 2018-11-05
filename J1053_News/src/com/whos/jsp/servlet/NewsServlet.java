package com.whos.jsp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whos.jsp.dao.NewsInfoDao;
import com.whos.jsp.dao.NewsTypeDao;
import com.whos.jsp.entity.NewsInfo;
import com.whos.jsp.entity.NewsType;
import com.whos.jsp.entity.UserInfo;

public class NewsServlet extends HttpServlet {

	private NewsInfoDao dao = new NewsInfoDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到method
		String method = request.getParameter("method");
		
		if(method.equals("gotoAdd")){
			gotoAdd(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("gotoAdmin")){
			gotoAdmin(request,response);
		}else if(method.equals("gotoModify")){
			gotoModify(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("del")){
			del(request,response);
		}
	}

	//跳转到添加新闻页面
	public void gotoAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsTypeDao typeDao = new NewsTypeDao();
		List<NewsType> topicList= typeDao.getTopicList();
		request.setAttribute("topicList", topicList);
		request.getRequestDispatcher("/admin/news_add.jsp").forward(request, response);
	}
	
	//添加新闻
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String newsTitle = request.getParameter("newsTitle");
		String newsAuthor = request.getParameter("newsAuthor");
		String newsSummary = request.getParameter("newsSummary");
		String newsContent = request.getParameter("newsContent");
		String newsPic = request.getParameter("newsPic");
		
		//保存到对象
		NewsInfo news = new NewsInfo();
		news.setNewsAuthor(newsAuthor);
		news.setNewsContent(newsContent);
		news.setNewsPic(newsPic);
		news.setNewsSummary(newsSummary);
		news.setNewsTitle(newsTitle);
		news.setTypeId(typeId);
		
		boolean success = dao.saveNews(news);
		if(success){
			gotoAdmin(request, response);
		}else{
			request.setAttribute("error", "保存失败");
			gotoAdd(request,response);
		}
	}
	
	//跳转到admin页面
	public void gotoAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NewsInfo> list = dao.getNewsList();
		request.setAttribute("newsList",list);
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}
	
	//跳转到修业页面
	public void gotoModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		NewsInfo news = dao.getNews(newsId);
		request.setAttribute("news", news);
		
		NewsTypeDao typeDao = new NewsTypeDao();
		List<NewsType> topicList= typeDao.getTopicList();
		request.setAttribute("topicList", topicList);
		
		request.getRequestDispatcher("/admin/news_modify.jsp").forward(request, response);
	}
	
	//修改新闻
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String newsTitle = request.getParameter("newsTitle");
		String newsAuthor = request.getParameter("newsAuthor");
		String newsSummary = request.getParameter("newsSummary");
		String newsContent = request.getParameter("newsContent");
		String newsPic = request.getParameter("newsPic");
		
		//保存到对象
		NewsInfo news = new NewsInfo();
		news.setNewsAuthor(newsAuthor);
		news.setNewsContent(newsContent);
		news.setNewsPic(newsPic);
		news.setNewsSummary(newsSummary);
		news.setNewsTitle(newsTitle);
		news.setTypeId(typeId);
		news.setNewsId(newsId);
		
		boolean success = dao.updateNews(news);
		if(success){
			gotoAdmin(request, response);
		}else{
			request.setAttribute("error", "修改失败");
			gotoModify(request,response);
		}
	}
	
	//删除新闻
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int newsId = Integer.parseInt(request.getParameter("newsId"));
		//根据newsId删除新闻
		boolean success = dao.delNews(newsId);
		if(!success){
			request.setAttribute("error", "删除失败");
		}
		gotoAdmin(request, response);
	}
}
