package com.whos.jsp.util;

public class Page {
	//��ǰҳ
	private int currentPage = 1;
	//ÿҳ������
	private int pageSize = 3;
	//�ܹ�������
	private int countSize = 0;
	//��ҳ��
	private int countPage = 0;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCountSize() {
		return countSize;
	}
	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}
	public int getCountPage() {
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
}
