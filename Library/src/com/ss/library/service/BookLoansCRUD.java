package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BookLoansDAO;
import com.ss.library.entity.BookLoans;

public interface BookLoansCRUD {

	Util util = new Util();

	default String addBookLoan(BookLoans bookLoan) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.addBookLoans(bookLoan);
			
			conn.commit();
			return "Book Loan added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Loan could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default List<BookLoans> readBookLoans() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookLoansDAO bldao = new BookLoansDAO(conn);
		List<BookLoans> bookLoans = bldao.readAllBookLoans();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookLoans;
		
	}

	default List<BookLoans> readBookLoansById(Integer cardNo) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookLoansDAO bldao = new BookLoansDAO(conn);
		List<BookLoans> bookLoans = bldao.readBookLoansById(cardNo);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookLoans;
		
	}

	default String updateBookLoans(BookLoans bookLoan) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.updateBookLoans(bookLoan);
			
			conn.commit();
			return "Book Loan updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Book Loan could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
