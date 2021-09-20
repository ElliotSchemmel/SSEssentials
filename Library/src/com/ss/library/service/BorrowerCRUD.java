package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BorrowerDAO;
import com.ss.library.entity.Borrower;

public interface BorrowerCRUD {

	Util util = new Util();
	
	default String addBorrower(Borrower borrower) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.addBorrower(borrower);
			
			conn.commit();
			return "borrower added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "borrower could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		List<Borrower> borrowers = bdao.readAllBorrowers();
		
		if (conn != null) {
			conn.close();
		}
		
		return borrowers;
	}
	
	default List<Borrower> readBorrowersById(Integer cardNo) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BorrowerDAO bdao = new BorrowerDAO(conn);
		List<Borrower> borrowers = bdao.readBorrowersById(cardNo);
		
		if (conn != null) {
			conn.close();
		}
		
		return borrowers;
		
	}

	default String updateBorrower(Borrower borrower) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.updateBorrower(borrower);
			
			conn.commit();
			return "borrower updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "borrower could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBorrower(Borrower borrower) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			
			conn.commit();
			return "borrower added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "borrower could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
