package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{
	
	public GenreDAO(Connection conn) {
		super(conn);
	}
	
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("insert into tbl_genre values (?, ?)", new Object[] {
				genre.getGenre_id(), genre.getGenre_name()
		});
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[] {
				genre.getGenre_name(), genre.getGenre_id()
		});
	}

	public void deleteGerne(Genre genre) throws ClassNotFoundException, SQLException {
		save("delete from tbl_genre where genre_id = ?", new Object[] {
				genre.getGenre_id()
		});
	}

	public List<Genre> readAllGenres() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_genre", null);
	}

	public List<Genre> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}
	
}