package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BookAuthorsDAO;
import com.ss.library.entity.BookAuthors;

public interface BookAuthorsCRUD {
	
	Util util = new Util();

	default String addBookAuthors(BookAuthors bookAuthors) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			badao.addBookAuthors(bookAuthors);
			
			conn.commit();
			return "Book Author added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Author could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default List<BookAuthors> readBookAuthors() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookAuthorsDAO bdao = new BookAuthorsDAO(conn);
		List<BookAuthors> bookAuthors = bdao.readAllBookAuthors();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookAuthors;
	}
	
	default List<BookAuthors> readAuthorIdByBookId(Integer bookId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookAuthorsDAO bdao = new BookAuthorsDAO(conn);
		List<BookAuthors> bookAuthors = bdao.readAuthorIdByBookId(bookId);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookAuthors;
	}
	
	default List<BookAuthors> readBookIdByAuthorId(Integer authorId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookAuthorsDAO bdao = new BookAuthorsDAO(conn);
		List<BookAuthors> bookAuthors = bdao.readBookIdByAuthorId(authorId);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookAuthors;
	}

	default String updateBookId(BookAuthors bookAuthors) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			badao.updateBookId(bookAuthors);
			
			conn.commit();
			return "Book Author updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Author could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String updateAuthorId(BookAuthors bookAuthors) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			badao.updateAuthorId(bookAuthors);
			
			conn.commit();
			return "Book Author updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Author could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookAuthors(BookAuthors bookAuthors) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			badao.deleteBookAuthors(bookAuthors);
			
			conn.commit();
			return "Book Author deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Author could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
