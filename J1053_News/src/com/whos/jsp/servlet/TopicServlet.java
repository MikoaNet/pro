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
		
		//�õ�method
		String method = (String)request.getParameter("method");
		
		if(method.equals("add")){
			//���
			add(request,response);
		}else if(method.equals("list")){
			//�б�
			list(request,response);
		}else if(method.equals("gotoModify")){
			//��ת���޸�ҳ��
			gotoModify(request,response);
		}else if(method.equals("update")){
			//�޸�
			update(request,response);
		}else if(method.equals("delete")){
			//ɾ��
			delete(request,response);
		}
		
	}

	//���
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�����
		String typeName = request.getParameter("typeName");
		boolean success = dao.saveTopic(typeName);
		
		if(success){
			list(request,response);
		}else{
			request.setAttribute("error", "����ʧ��");
			request.getRequestDispatcher("/admin/topic_add.jsp").forward(request, response);
		}
	}
	
	//list
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ǰҳ
		int currentPage = page.getCurrentPage();
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//ÿҳ������
		int pageSize = page.getPageSize();
		if(request.getParameter("pageSize")!=null){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		//�ܹ�������
		int countSize = dao.getCount();
		//��ҳ��
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
		
		//��ȡtopicList
		List<NewsType> topicList = dao.getList(pageSize, currentPage);
		
		//request��list
		request.setAttribute("topicList", topicList);
		request.getRequestDispatcher("/admin/topic_list.jsp").forward(request, response);
		
	}
	
	//��ת���޸�ҳ��
	public void gotoModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		//��ֵ
		request.setAttribute("typeId", typeId);
		request.setAttribute("typeName", typeName);
		request.getRequestDispatcher("/admin/topic_modify.jsp").forward(request, response);
	}
	
	//�޸�
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		String typeName = request.getParameter("typeName");
		
		//�޸�����
		boolean success = dao.updateTopic(typeId,typeName);
		
		if(success){
			//��ת���༭����
			list(request,response);
		}else{
			request.setAttribute("error", "����ʧ��");
			gotoModify(request,response);
		}
	}
	
	//ɾ��
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�õ�typeId
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		//����id��ɾ������
		boolean success = dao.delTopic(typeId);
		if(success){
			list(request, response);
		}else{
			request.setAttribute("error", "ɾ��ʧ��");
			list(request, response);
		}
	}
}
