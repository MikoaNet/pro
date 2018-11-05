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
import com.whos.jsp.util.CommenMethod;
import com.whos.jsp.util.Page;

public class IndexServlet extends HttpServlet {

	private Page page = new Page();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("typeId");
		int typeId = 0;
		if(id!=null){
			typeId = Integer.parseInt(id);
			//���õ�ǰҳ
			page.setCurrentPage(1);
		}

		CommenMethod.get();
		
		NewsTypeDao dao = new NewsTypeDao();
		NewsInfoDao infoDao = new NewsInfoDao();
		// �õ�����
		List<NewsType> topicList = dao.getTopicList();
		
		//�õ�ͼƬ����
		List<NewsInfo> picList = infoDao.getPicList();
		
		
		List<NewsInfo> newsList = null;

		// ��ҳ
		// ��ǰҳ
		int currentPage = page.getCurrentPage();
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// ÿҳ������
		int pageSize = page.getPageSize();
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		// �ܹ�������
		int countSize = infoDao.count(typeId);
		// ��ҳ��
		int countPage = 0;
		if (countSize % pageSize == 0) {
			countPage = countSize / pageSize;
		} else {
			countPage = countSize / pageSize + 1;
		}
		page.setCountPage(countPage);
		page.setCountSize(countSize);
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);

		request.setAttribute("page", page);

		// �õ������б�
		if (typeId == 0) {
			newsList = infoDao.getNewsList(pageSize, currentPage);
		} else {
			newsList = infoDao.getNewsList(typeId,pageSize,currentPage);
		}

		request.setAttribute("newsList", newsList);
		request.setAttribute("topicList", topicList);
		request.setAttribute("picList", picList);
		request.setAttribute("typeId", typeId);
		request.setAttribute("internationalList",
				CommenMethod.internationalList);
		request.setAttribute("inlandList", CommenMethod.inlandList);
		request.setAttribute("enList", CommenMethod.enList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
