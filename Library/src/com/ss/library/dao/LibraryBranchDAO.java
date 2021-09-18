package com.ss.library.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Branch;

public class LibraryBranchDAO extends BaseDAO<Branch>{

	public LibraryBranchDAO(Connection conn) {
		super(conn);
	}
	
	public void addLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("insert into tbl_library_branch values (?, ?, ?)", new Object[] {
				branch.getBranchId(), branch.getBranchName(), branch.getBranchAddress()
		});
	}

	public void updateLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("update tbl_library_branch set branchName = ? and branchAddress ? where branchId = ?", new Object[] {
				 branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()
		});
	}

	public void deleteLibraryBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("delete from tbl_library_branch where branchId = ?", new Object[] {
				branch.getBranchId()
		});
	}

	public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_library_branch", null);
	}

	public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Branch> branches = new ArrayList<>();
		while (rs.next()) {
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			branches.add(b);
		}
		return branches;
	}
	
}