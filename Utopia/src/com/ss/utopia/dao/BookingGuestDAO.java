package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingGuest;

public class BookingGuestDAO extends BaseDAO<BookingGuest>{
	
	public BookingGuestDAO(Connection conn) { super(conn); }
	
	// booking_id given should match booking.getId()
	public void addBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_guest (booking_id, contact_phone, contact_email) VALUES (?, ?, ?)", new Object[] {
				bookingGuest.getBooking_id(), bookingGuest.getContact_phone(), bookingGuest.getContact_email()}); 
	}

	public void updateBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_guest SET contact_phone = ?, contact_email = ? WHERE booking_id = ?", new Object[] { 
				bookingGuest.getContact_phone(), bookingGuest.getContact_email(), bookingGuest.getBooking_id()});
	}

	public void deleteBookingGuest(BookingGuest bookingGuest) throws ClassNotFoundException, SQLException {
		save("DELETE FROM booking_guest WHERE booking_id = ?", new Object[] { bookingGuest.getBooking_id() });
	}

	public List<BookingGuest> readBookingGuests() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_guest", null);
	}
	
	public List<BookingGuest> readBookingGuestsById(Integer booking_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_guest WHERE booking_id = ?",
						new Object[] { booking_id });
	}

	@Override
	public List<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingGuest> bookingGuests = new ArrayList<>();
		while (rs.next()) {
			BookingGuest bookingGuest = new BookingGuest();
			bookingGuest.setBooking_id(rs.getInt("booking_id"));
			bookingGuest.setContact_phone(rs.getString("contact_phone"));
			bookingGuest.setContact_email(rs.getString("contact_email"));
			bookingGuests.add(bookingGuest);
		}
		return bookingGuests;
	}

}
