package com.ss.library.entity;

import java.sql.Date;

public class BookLoans {
	
	private Integer bookId;
	private Integer branchId;
	private Integer cardNo;
	private	Date dateOut;
	private	Date dateDue;
	private	Date dateIn;

	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date date) {
		this.dateOut = date;
	}
	public Date getDateDue() {
		return dateDue;
	}
	public void setDateDue(Date date) {
		this.dateDue = date;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date date) {
		this.dateIn = date;
	}
}
