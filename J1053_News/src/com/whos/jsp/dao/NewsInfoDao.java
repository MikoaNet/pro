package com.whos.jsp.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.whos.jsp.entity.NewsInfo;
import com.whos.jsp.util.DBConnection;
import com.whos.jsp.util.DateUtil;

public class NewsInfoDao extends BaseDao{

	//保存新闻
	public boolean saveNews(NewsInfo news){
		boolean success=true;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into news_info values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, news.getTypeId());
			pstmt.setString(2, news.getNewsTitle());
			pstmt.setString(3, news.getNewsAuthor());
			pstmt.setString(4, news.getNewsSummary());
			pstmt.setString(5, news.getNewsContent());
			pstmt.setString(6, news.getNewsPic());
			pstmt.setTimestamp(7, new Timestamp( new Date().getTime() ));
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return success;
	}
	
	//得到新闻列表
	public List<NewsInfo> getNewsList(){
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from news_info";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsInfo news = new NewsInfo();
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				news.setNewsPic(rs.getString(7));
				news.setCreateDate(rs.getTimestamp(8));
				list.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}
	
	//得到新闻列表 分页
	public List<NewsInfo> getNewsList(int pageSize,int currentPage){
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select top "+pageSize+" * from news_info "
					+ "where news_id not in "
					+ "(select top "+(currentPage-1)*pageSize+" news_id from news_info "
					+ "order by news_id ) "
					+ "order by news_id";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsInfo news = new NewsInfo();
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				news.setNewsPic(rs.getString(7));
				news.setCreateDate(rs.getTimestamp(8));
				list.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}
	
	//根据newsId查询新闻信息
	public NewsInfo getNews(int newsId){
		NewsInfo news = new NewsInfo();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from news_info where news_id = "+newsId;
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				news.setNewsPic(rs.getString(7));
				news.setCreateDate(rs.getTimestamp(8));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return news;
	}
	
	//修改新闻
	public boolean updateNews(NewsInfo news){
		boolean success=true;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "update news_info set type_id=?,news_title=?,news_author=?,"
					+ "news_summary=?,news_content=?,news_pic=? where news_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, news.getTypeId());
			pstmt.setString(2, news.getNewsTitle());
			pstmt.setString(3, news.getNewsAuthor());
			pstmt.setString(4, news.getNewsSummary());
			pstmt.setString(5, news.getNewsContent());
			pstmt.setString(6, news.getNewsPic());
			pstmt.setInt(7, news.getNewsId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return success;
	}
	
	//根据id删除新闻
	public boolean delNews(int newsId){
		boolean success=true;
		
		try {
			conn = DBConnection.getConnection();
			
			stmt = conn.createStatement();
			String sql = "delete from news_info where news_id ="+newsId;
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
	
	//根据新闻id得到新闻信息
	public List<NewsInfo> getNewsList(int typeId){
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select top 3 ni.* from news_info ni,news_type nt "
					+ "where ni.type_id = nt.type_id and nt.type_id="+typeId;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsInfo news = new NewsInfo();
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				news.setNewsPic(rs.getString(7));
				news.setCreateDate(rs.getTimestamp(8));
				list.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}
	
	public List<NewsInfo> getNewsList(int typeId,int pageSize,int currentPage){
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select top "+pageSize+" ni.* from news_info ni,news_type nt "
					+ "where ni.type_id = nt.type_id and nt.type_id="+typeId+" "
					+ "and ni.news_id not in"
					+ "(select top "+pageSize*(currentPage-1)+" news_id "
					+ "from news_info where type_id="+typeId+" order by news_id ) "
					+ "order by ni.news_id";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsInfo news = new NewsInfo();
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				news.setNewsPic(rs.getString(7));
				news.setCreateDate(rs.getTimestamp(8));
				list.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}
	
	//查询创建时间最近的4个图片路径
	public List<NewsInfo> getPicList(){
		List<NewsInfo> picList = new ArrayList<NewsInfo>();
		
		try {
			conn = DBConnection.getConnection();
			
			stmt = conn.createStatement();
			String sql = "select top 4 * from news_info "
					+ "where ltrim(news_pic)!= '' "
					+ "order by create_date desc";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				NewsInfo news = new NewsInfo();
				news.setNewsId(rs.getInt(1));
				news.setTypeId(rs.getInt(2));
				news.setNewsTitle(rs.getString(3));
				news.setNewsAuthor(rs.getString(4));
				news.setNewsSummary(rs.getString(5));
				news.setNewsContent(rs.getString(6));
				try {
					news.setNewsPic( URLEncoder.encode(rs.getString(7), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				news.setCreateDate(rs.getTimestamp(8));
				picList.add(news);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return picList;
	}
	
	public int count(int typeId){
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			
			stmt = conn.createStatement();
			String sql = "";
			if(typeId!=0){
				sql = "select count(*) from news_info i,news_type t "
					+ "where i.type_id=t.type_id and t.type_id ="+typeId;
			}else{
				sql = "select count(*) from news_info i,news_type t "
						+ "where i.type_id=t.type_id";
			}
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
}
