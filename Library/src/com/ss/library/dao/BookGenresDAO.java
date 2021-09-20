package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookGenres;

public class BookGenresDAO extends BaseDAO<BookGenres>{
	
	public BookGenresDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookGenres(BookGenres bookGenres) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_genres values (?, ?)", new Object[] {
				bookGenres.getGenre_id(), bookGenres.getBookId()
		});
	}

	public void updateGenre_id(BookGenres bookGenres) throws ClassNotFoundException, SQLException {
		save("update tbl_book_genres genre_id = ? where bookId = ?", new Object[] {
				bookGenres.getGenre_id(), bookGenres.getBookId()
		});
	}

	public void updateBookId(BookGenres bookGenres) throws ClassNotFoundException, SQLException {
		save("update tbl_book_genres bookId = ? where genre_id = ?", new Object[] {
				bookGenres.getBookId(), bookGenres.getGenre_id()
		});
	}


	public void deleteBookGenres(BookGenres bookGenres) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_genres where genre_id = ? and bookId = ?", new Object[] {
				bookGenres.getGenre_id(), bookGenres.getBookId()
		});
	}

	public List<BookGenres> readAllBookAuthors() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_genres", null);
	}

	public List<BookGenres> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookGenres> bookGenres = new ArrayList<>();
		while (rs.next()) {
			BookGenres bg = new BookGenres();
			bg.setGenre_id(rs.getInt("genre_id"));
			bg.setBookId(rs.getInt("bookId"));
			bookGenres.add(bg);
		}
		return bookGenres;
	}
	
}