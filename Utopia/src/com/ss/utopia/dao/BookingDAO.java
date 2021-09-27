package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Booking;

public class BookingDAO extends BaseDAO<Booking>{
	
	public BookingDAO(Connection conn) { super(conn); }
	
	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking (is_active, confirmation_code) VALUES (?, ?)", new Object[] {
				booking.getIs_active(), booking.getConfirmation_code()}); 
	}

	public Integer addBookingReturnPK(Booking booking) throws ClassNotFoundException, SQLException {
		return saveReturnPK("INSERT INTO booking (is_active, confirmation_code) VALUES (?, ?)", new Object[] {
				booking.getIs_active(), booking.getConfirmation_code()}); 
	}

	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("UPDATE booking SET is_active = ?, confirmation_code = ? WHERE id = ?", new Object[] { 
				booking.getIs_active(), booking.getConfirmation_code(), booking.getId()});
	}

	public void deleteBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("DELETE FROM booking WHERE id = ?", new Object[] { booking.getId() });
	}

	public List<Booking> readBookings() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking", null);
	}
	
	public List<Booking> readBookingsById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> bookings = new ArrayList<>();
		while (rs.next()) {
			Booking booking = new Booking();
			booking.setId(rs.getInt("id"));
			booking.setIs_active(rs.getInt("is_active"));
			booking.setConfirmation_code(rs.getString("confirmation_code"));
			bookings.add(booking);
		}
		return bookings;
	}

}
