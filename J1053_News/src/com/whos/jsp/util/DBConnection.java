package com.whos.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static Connection conn = null;
	
	//得到数据库连接
	public static Connection getConnection(){
		
		if(conn == null){
			init();
		}
		
		try {
			if(conn.isClosed()){
				init();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private static void init(){
		//加载驱动类
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//得到数据库链接
		try {
			String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName = j1053";
			
			conn = DriverManager.getConnection(url, "sa", "whos2018");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn,Statement stmt,PreparedStatement pstmt,ResultSet rs){
		try {
			
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
