package com.whos.jsp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whos.jsp.entity.CommentInfo;
import com.whos.jsp.util.DBConnection;

public class CommentInfoDao extends BaseDao{

	//根据newsId查询评论
	public List<CommentInfo> getComment(int newsId){
		List<CommentInfo> commentList = new ArrayList<CommentInfo>();
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "select * from comment_info where news_id =" +newsId +"order by comment_id desc";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				CommentInfo comment = new CommentInfo();
				comment.setCommentId(rs.getInt(1));
				comment.setUsername(rs.getString(2));
				comment.setIp(rs.getString(3));
				comment.setContent(rs.getString(4));
				comment.setNewsId(rs.getInt(5));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBConnection.close(conn, stmt, pstmt, rs);
		}
		
		return commentList;
	}
	
	public boolean saveComment(CommentInfo comment){
		boolean success = true;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into comment_info values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getUsername());
			pstmt.setString(2, comment.getIp());
			pstmt.setString(3, comment.getContent());
			pstmt.setInt(4, comment.getNewsId());
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
	
	
	//根据评论id删除评论
	public boolean delComment(int commentId){
		boolean success = true;
		
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from comment_info where comment_id = "+commentId;
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
