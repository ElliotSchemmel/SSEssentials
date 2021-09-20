package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{
	
	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("insert into tbl_borrower values (?, ?, ?)", new Object[] {
				borrower.getName(), borrower.getAddress(), borrower.getPhone()
		});
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", new Object[] {
				 borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()
		});
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] {
				borrower.getCardNo()
		});
	}

	public List<Borrower> readBorrowersById(Integer cardNo) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_borrower where cardNo = ?", new Object[] {
				cardNo
		});
	}

	public List<Borrower> readAllBorrowers() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_borrower", null);
	}

	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			borrowers.add(b);
		}
		return borrowers;
	}
	
}