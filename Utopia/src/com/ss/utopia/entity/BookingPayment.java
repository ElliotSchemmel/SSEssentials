package com.ss.utopia.entity;

public class BookingPayment {
	
	private Integer booking_id;
	private String stripe_id;
	private Integer refunded; // sql TINYINT 0-255

	public Integer getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}
	public String getStripe_id() {
		return stripe_id;
	}
	public void setStripe_id(String stripe_id) {
		this.stripe_id = stripe_id;
	}
	public Integer getRefunded() {
		return refunded;
	}
	public void setRefunded(Integer refunded) {
		this.refunded = refunded;
	}
}
