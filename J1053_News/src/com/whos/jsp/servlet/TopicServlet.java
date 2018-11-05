package com.whos.jsp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whos.jsp.dao.NewsTypeDao;
import com.whos.jsp.entity.NewsType;
import com.whos.jsp.util.Page;

public class TopicServlet extends HttpServlet {

	private NewsTypeDao dao = new NewsTypeDao();
	private Page page = new Page();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//得到method
		String method = (String)request.getParameter("method");
		
		if(method.equals("add")){
			//添加
			add(request,response);
		}else if(method.equals("list")){
			//列表
			list(request,response);
		}else if(method.equals("gotoModify")){
			//跳转到修改页面
			gotoModify(request,response);
		}else if(method.equals("update")){
			//修改
			update(request,response);
		}else if(method.equals("delete")){
			//删除
			delete(request,response);
		}
		
	}

	//添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到主题
		String typeName = request.getParameter("typeName");
		boolean success = dao.saveTopic(typeName);
		
		if(success){
			list(request,response);
		}else{
			request.setAttribute("error", "保存失败");
			request.getRequestDispatcher("/admin/topic_add.jsp").forward(request, response);
		}
	}
	
	//list
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//当前页
		int currentPage = page.getCurrentPage();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//每页多少行
		int pageSize = page.getPageSize();
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		//总共多少行
		int countSize = dao.getCount();
		//总页数
		int countPage = 0;
		if( countSize % pageSize == 0 ){
			countPage = countSize / pageSize;
		}else{
			countPage = countSize / pageSize + 1;
		}
		page.setCountPage(countPage);
		page.setCountSize(countSize);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);
		
		request.setAttribute("page", page);
		
		//获取topicList
		List<NewsType> topicList = dao.getList(pageSize, currentPage);
		
		//request加list
		request.setAttribute("topicList", topicList);
		request.getRequestDispatcher("/admin/topic_list.jsp").forward(request, response);
		
	}
	
	//跳转到修改页面
	public void gotoModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		//传值
		request.setAttribute("typeId", typeId);
		request.setAttribute("typeName", typeName);
		request.getRequestDispatcher("/admin/topic_modify.jsp").forward(request, response);
	}
	
	//修改
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String typeName = request.getParameter("typeName");
		
		//修改数据
		boolean success = dao.updateTopic(typeId,typeName);
		
		if(success){
			//跳转到编辑主题
			list(request,response);
		}else{
			request.setAttribute("error", "保存失败");
			gotoModify(request,response);
		}
	}
	
	//删除
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到typeId
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		//根据id做删除操作
		boolean success = dao.delTopic(typeId);
		if(success){
			list(request, response);
		}else{
			request.setAttribute("error", "删除失败");
			list(request, response);
		}
	}
}
