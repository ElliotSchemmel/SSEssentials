package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookAuthors;

public class BookAuthorsDAO extends BaseDAO<BookAuthors>{
	
	public BookAuthorsDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_authors values (?, ?)", new Object[] {
				bookAuthors.getBookId(), bookAuthors.getAuthorId()
		});
	}

	public void updateBookId(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("update tbl_book_authors set bookId = ? where authorId = ?", new Object[] {
				bookAuthors.getBookId(), bookAuthors.getAuthorId()
		});
	}

	public void updateAuthorId(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("update tbl_book_authors set authorId = ? where bookId = ?", new Object[] {
				bookAuthors.getAuthorId(), bookAuthors.getBookId()
		});
	}

	public void deleteBookAuthors(BookAuthors bookAuthors) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_authors where bookId = ? and authorId = ?", new Object[] {
				bookAuthors.getBookId(), bookAuthors.getAuthorId()
		});
	}

	public List<BookAuthors> readBookIdByAuthorId(Integer authorId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_authors where authorId = ?", new Object[] {
				authorId
		});
	}
	public List<BookAuthors> readAuthorIdByBookId(Integer bookId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_authors where bookId = ?", new Object[] {
				bookId
		});
	}

	public List<BookAuthors> readAllBookAuthors() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_authors", null);
	}

	public List<BookAuthors> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookAuthors> bookAuthors = new ArrayList<>();
		while (rs.next()) {
			BookAuthors ba = new BookAuthors();
			ba.setBookId(rs.getInt("bookId"));
			ba.setAuthorId(rs.getInt("authorId"));
			bookAuthors.add(ba);
		}
		return bookAuthors;
	}
	
}