package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingPayment;

public class BookingPaymentDAO extends BaseDAO<BookingPayment>{
	
	public BookingPaymentDAO(Connection conn) { super(conn); }
	
	// booking_id given should match booking.getId()
	public void addBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_payment (booking_id, refunded, stripe_id) VALUES (?, ?, ?)", new Object[] {
				bookingPayment.getBooking_id(), bookingPayment.getRefunded(), bookingPayment.getStripe_id()}); 
	}

	public void updateBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_payment SET refunded = ?, stripe_id = ? WHERE booking_id = ?", new Object[] { 
				bookingPayment.getRefunded(), bookingPayment.getStripe_id(), bookingPayment.getBooking_id()});
	}

	public void deleteBookingPayment(BookingPayment bookingPayment) throws ClassNotFoundException, SQLException {
		save("DELETE FROM booking_payment WHERE booking_id = ?", new Object[] { bookingPayment.getBooking_id() });
	}

	public List<BookingPayment> readBookingPayments() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_payment", null);
	}
	
	public List<BookingPayment> readBookingPaymentsById(Integer booking_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_payment WHERE booking_id = ?",
						new Object[] { booking_id });
	}

	@Override
	public List<BookingPayment> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingPayment> bookingPayments = new ArrayList<>();
		while (rs.next()) {
			BookingPayment bookingPayment = new BookingPayment();
			bookingPayment.setBooking_id(rs.getInt("booking_id"));
			bookingPayment.setRefunded(rs.getInt("refunded"));
			bookingPayment.setStripe_id(rs.getString("stripe_id"));
			bookingPayments.add(bookingPayment);
		}
		return bookingPayments;
	}

}
