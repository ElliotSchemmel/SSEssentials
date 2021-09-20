package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BookGenresDAO;
import com.ss.library.entity.BookGenres;

public interface BookGenresCRUD {
	
	Util util = new Util();

	default String addBookGenres(BookGenres bookGenres) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookGenresDAO badao = new BookGenresDAO(conn);
			badao.addBookGenres(bookGenres);
			
			conn.commit();
			return "Book Genre added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Genre could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default List<BookGenres> readBookGenres() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookGenresDAO bdao = new BookGenresDAO(conn);
		List<BookGenres> bookGenres = bdao.readAllBookGenres();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookGenres;
	}
	
	default List<BookGenres> readBookByGenreId(Integer genreId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookGenresDAO bdao = new BookGenresDAO(conn);
		List<BookGenres> bookGenres = bdao.readBookByGenreId(genreId);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookGenres;
	}
	
	default List<BookGenres> readGenreByBookId(Integer bookId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookGenresDAO bdao = new BookGenresDAO(conn);
		List<BookGenres> bookGenres = bdao.readGenreByBookId(bookId);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookGenres;
	}

	default String updateGenre_id(BookGenres bookGenres) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookGenresDAO badao = new BookGenresDAO(conn);
			badao.updateGenre_id(bookGenres);
			
			conn.commit();
			return "Book Genre updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Genre could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default String updateBookId(BookGenres bookGenres) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookGenresDAO badao = new BookGenresDAO(conn);
			badao.updateBookId(bookGenres);
			
			conn.commit();
			return "Book Genre updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Genre could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookGenres(BookGenres bookGenres) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookGenresDAO badao = new BookGenresDAO(conn);
			badao.deleteBookGenres(bookGenres);
			
			conn.commit();
			return "Book Genre deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Genre could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}