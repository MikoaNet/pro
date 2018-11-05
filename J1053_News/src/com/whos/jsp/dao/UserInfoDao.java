package com.whos.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.whos.jsp.entity.UserInfo;
import com.whos.jsp.util.DBConnection;

public class UserInfoDao extends BaseDao{

	
	//根据用户名查询用户信息
	public UserInfo getUser(String username){
		UserInfo user = null;
		
		conn = DBConnection.getConnection();
		try {
			stmt = conn.createStatement();
			String sql = "select * from user_info where username = '"+username+"' ";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				user = new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
}
