package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.BorrowerDAO;
import com.ss.library.entity.Borrower;

public interface BorrowerCRUD {

	Util util = new Util();
	
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
}
