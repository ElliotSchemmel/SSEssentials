package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BookDAO;
import com.ss.library.entity.Book;

public interface BookCRUD {
	
	Util util = new Util();

	default String addBook(Book book) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addBook(book);
			
			conn.commit();
			return "Book added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Book> readBooks() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookDAO bdao = new BookDAO(conn);
		List<Book> books = bdao.readAllBooks();
		
		if (conn != null) {
			conn.close();
		}
		
		return books;
	}

	default List<Book> readBooksById(Integer bookId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookDAO bdao = new BookDAO(conn);
		List<Book> books = bdao.readBooksById(bookId);
		
		if (conn != null) {
			conn.close();
		}
		
		return books;
		
	}
}
