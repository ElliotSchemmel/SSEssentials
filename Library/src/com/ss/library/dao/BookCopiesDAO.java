package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> {
	
	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookCopies(BookCopies bookCopies) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_copies values (?, ?, ?)", new Object[] {
				bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies()
		});
	}

	public void updateBookCopies(BookCopies bookCopies) throws ClassNotFoundException, SQLException {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", new Object[] {
				bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId()
		});
	}

	public void deleteBookCopies(BookCopies bookCopies) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?", new Object[] {
				bookCopies.getBookId(), bookCopies.getBranchId()
		});
	}

	public List<BookCopies> readBookCopiesById(BookCopies bookCopies) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies where bookId = ? and branchId = ?", new Object[] {
				bookCopies.getBookId(), bookCopies.getBranchId()
		});
	}

	public List<BookCopies> readBookCopiesFromBranchId(Integer branchId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies where branchId = ?", new Object[] {
				branchId
		});
	}

	public List<BookCopies> readAllBookCopies() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_copies", null);
	}

	public List<BookCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies b = new BookCopies();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopies.add(b);
		}
		return bookCopies;
	}
	

}
