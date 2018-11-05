package com.whos.jsp.util;

import java.util.List;

import com.whos.jsp.dao.NewsInfoDao;
import com.whos.jsp.entity.NewsInfo;

public class CommenMethod {

	public static List<NewsInfo> inlandList=null;
	public static List<NewsInfo> internationalList=null;
	public static List<NewsInfo> enList=null;
	private static NewsInfoDao dao = new NewsInfoDao();
	
	public static void get(){
		initInlandList();
		initInternationalList();
		initEnList();
	}
	
	//刷新
	public static void refresh(){
		inlandList = dao.getNewsList(1);
		internationalList = dao.getNewsList(3);
		enList = dao.getNewsList(11);
	}
	
	//国内
	private static List<NewsInfo> initInlandList(){
		if(inlandList==null){
			inlandList = dao.getNewsList(1);
		}
		return inlandList;
	}
	
	//国际
	private static List<NewsInfo> initInternationalList(){
		if(internationalList==null){
			internationalList = dao.getNewsList(3);
		}
		return internationalList;
	}
	
	//娱乐
	private static List<NewsInfo> initEnList(){
		if(enList==null){
			enList = dao.getNewsList(11);
		}
		return enList;
	}

	
	
}
