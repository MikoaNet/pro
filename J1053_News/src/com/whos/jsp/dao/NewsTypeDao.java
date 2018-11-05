package com.whos.jsp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whos.jsp.entity.NewsType;
import com.whos.jsp.util.DBConnection;

public class NewsTypeDao extends BaseDao{

	//添加主题
	public boolean saveTopic(String typeName){
		
		boolean success = true;
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into news_type values('"+typeName+"')";
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return success;
	}
	
	//得到主题列表
	public List<NewsType> getTopicList(){ 
		
		List<NewsType> topicList = new ArrayList<NewsType>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from news_type";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsType type = new NewsType();
				//得到type
				type.setTypeId(rs.getInt(1));
				type.setTypeName(rs.getString(2));
				//添加到list
				topicList.add(type);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return topicList;
	}
	
	//分页列表
	public List<NewsType> getList(int pageSize,int currentPage){ 
		
		List<NewsType> topicList = new ArrayList<NewsType>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			StringBuffer sbf = new StringBuffer();
			sbf.append(" select top "+pageSize+" * from news_type ");
			sbf.append(" where type_id not in ");
			sbf.append(" (select top "+pageSize*(currentPage-1)+" type_id from news_type order by type_id) ");
			sbf.append(" order by type_id ");
			
			rs = stmt.executeQuery(sbf.toString());
			while(rs.next()){
				NewsType type = new NewsType();
				//得到type
				type.setTypeId(rs.getInt(1));
				type.setTypeName(rs.getString(2));
				//添加到list
				topicList.add(type);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return topicList;
	}
	
	//总行数
	public int getCount(){
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select count(*) from news_type";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return count;
	}
	
	//修改主题
	public boolean updateTopic(int typeId,String typeName){
		boolean success = true;
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "update news_type set type_name ='"+typeName+"' where type_id ="+typeId;
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return success;
	}
	
	//根据typeId删除topic
	public boolean delTopic(int typeId){
		boolean success = true;
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from news_type where type_id = "+typeId;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return success;
	}
	
}
