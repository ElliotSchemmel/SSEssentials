package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BookCopiesDAO;
import com.ss.library.entity.BookCopies;

public interface BookCopiesCRUD {
	
	Util util = new Util();

	default List<BookCopies> readBookCopies() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		List<BookCopies> copies = bcdao.readAllBookCopies();
		
		if (conn != null) {
			conn.close();
		}
		
		return copies;
	}

	default List<BookCopies> readBookCopiesFromBranchId(Integer branchId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		List<BookCopies> copies = bcdao.readBookCopiesFromBranchId(branchId);
		
		if (conn != null) {
			conn.close();
		}
		
		return copies;
	}
	
	default Integer readNoOfCopiesById(BookCopies bookCopies) throws ClassNotFoundException, SQLException {

		Connection conn = null;
		
		conn = util.getConnection();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		List<BookCopies> copies = bcdao.readBookCopiesById(bookCopies);
		
		if (conn != null) {
			conn.close();
		}
		
		return copies.get(0).getNoOfCopies();
		
	}

	default String updateBookCopies(BookCopies bookCopies) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.updateBookCopies(bookCopies);
			
			conn.commit();
			return "Number of copies updated successfully to: " + bookCopies.getNoOfCopies();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Number of copies could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
