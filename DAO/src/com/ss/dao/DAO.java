package com.ss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String username = "User1";
	private static final String password = "User1";
	

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		try {
			
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt = conn.prepareStatement("select * from tbl_book_authors ba "
					+ "right join tbl_book bk on ba.bookId = bk.bookId " 
					+ "right join tbl_author a on ba.authorId = a.authorId ");
//					+ "where a.authorName = ?");
//			pstmt.setString(1, "Stephen King");
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("authorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
