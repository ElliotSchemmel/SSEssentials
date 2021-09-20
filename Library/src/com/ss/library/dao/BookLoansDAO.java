package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans>{
	
	public BookLoansDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookLoans(BookLoans bookLoans) throws ClassNotFoundException, SQLException {
		save("insert into tbl_book_loans values (?, ?, ?, ?, ?, ?)", new Object[] {
				bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo(),
				bookLoans.getDateOut(), bookLoans.getDateDue(), bookLoans.getDateIn()
		});
	}

	public void updateBookLoans(BookLoans bookLoans) throws ClassNotFoundException, SQLException {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ?"
				+ "where bookId = ? and branchId = ? and cardNo = ?", new Object[] {
				bookLoans.getDateOut(), bookLoans.getDateDue(), bookLoans.getDateIn(),
				bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo()
		});
	}

	public void deleteBookLoans(BookLoans bookLoans) throws ClassNotFoundException, SQLException {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", new Object[] {
				bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo()
		});
	}

	public List<BookLoans> readBookLoansById(Integer cardNo) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_loans where cardNo = ?", new Object[] {
				cardNo
		});
	}

	public List<BookLoans> readAllBookLoans() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_book_loans", null);
	}

	public List<BookLoans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<BookLoans> loans = new ArrayList<>();
		while (rs.next()) {
			BookLoans l = new BookLoans();
			l.setBookId(rs.getInt("bookId"));
			l.setBranchId(rs.getInt("branchId"));
			l.setCardNo(rs.getInt("cardNo"));
			l.setDateOut(rs.getDate("dateOut"));
			l.setDateDue(rs.getDate("dueDate"));
			l.setDateIn(rs.getDate("dateIn"));
			loans.add(l);
		}
		return loans;
	}
	
}