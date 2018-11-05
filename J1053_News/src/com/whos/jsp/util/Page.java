package com.whos.jsp.util;

public class Page {
	//当前页
	private int currentPage = 1;
	//每页多少行
	private int pageSize = 3;
	//总共多少行
	private int countSize = 0;
	//总页数
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
