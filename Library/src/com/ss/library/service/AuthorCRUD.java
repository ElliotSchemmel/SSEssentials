package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.entity.Author;

public interface AuthorCRUD {
	
	Util util = new Util();

	default String addAuthor(Author author) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			
			conn.commit();
			return "author added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "author could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Author> readAuthors() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		List<Author> authors = adao.readAllAuthors();
		
		if (conn != null) {
			conn.close();
		}
		
		return authors;
	}

	default List<Author> readAuthorsById(Integer authorId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		List<Author> authors = adao.readAuthorsById(authorId);
		
		if (conn != null) {
			conn.close();
		}
		
		return authors;
		
	}

	default String updateAuthor(Author author) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			
			conn.commit();
			return "Author updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Author could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteAuthor(Author author) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			
			conn.commit();
			return "Author deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Author could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}