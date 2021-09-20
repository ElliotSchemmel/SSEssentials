package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.entity.Branch;

public interface LibraryBranchCRUD {
	
	Util util = new Util();

	default List<Branch> readLibraryBranches() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
		List<Branch> branches = lbdao.readAllBranches();
		
		if (conn != null) {
			conn.close();
		}
		
		return branches;
	}
	
	default String updateLibraryBranch(Branch branch) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			lbdao.updateLibraryBranch(branch);
			
			conn.commit();
			return "Branch updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Branch could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
