package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.GenreDAO;
import com.ss.library.entity.Genre;

public interface GenreCRUD {
	
	Util util = new Util();

	default String addGenre(Genre genre) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.addGenre(genre);
			
			conn.commit();
			return "Genre added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Genre could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Genre> readGenres() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		GenreDAO gdao = new GenreDAO(conn);
		List<Genre> genres = gdao.readAllGenres();
		
		if (conn != null) {
			conn.close();
		}
		
		return genres;
	}

	default List<Genre> readGenresById(Integer genre_id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		GenreDAO gdao = new GenreDAO(conn);
		List<Genre> genres = gdao.readGenresById(genre_id);
		
		if (conn != null) {
			conn.close();
		}
		
		return genres;
		
	}

	default String updateGenre(Genre genre) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.updateGenre(genre);
			
			conn.commit();
			return "Genre updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Genre could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteGenre(Genre genre) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
			
			conn.commit();
			return "Genre deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Genre could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}