package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Book;

public class BookDAO extends BaseDAO<Book>{
	
	public BookDAO(Connection conn) {
		super(conn);
	}
	
	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book values (?, ?, ?, ?)", new Object[] {
				book.getBookId(), book.getTitle(), book.getAuthId(), book.getPubId()
		});
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("update tbl_book set title = ? where bookId = ?", new Object[] {
				 book.getTitle(), book.getBookId()
		});
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book where bookId = ?", new Object[] {
				book.getBookId()
		});
	}

	public List<Book> readBooksById(Integer bookId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book where bookId = ?", new Object[] {
				bookId
		});
	}

	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book", null);
	}

	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setAuthId(rs.getInt("authId"));
			b.setPubId(rs.getInt("pubId"));
			books.add(b);
		}
		return books;
	}
	
}
