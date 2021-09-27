package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingUser;

public class BookingUserDAO extends BaseDAO<BookingUser>{
	
	public BookingUserDAO(Connection conn) { super(conn); }
	
	public void addBookingUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_user (booking_id, user_id) VALUES (?, ?)", new Object[] {
				bookingUser.getBooking_id(), bookingUser.getUser_id()}); 
	}

	public void updateBookingUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_user SET user_id = ? WHERE booking_id = ?", new Object[] { 
				bookingUser.getUser_id(), bookingUser.getBooking_id()});
	}

	public void deleteBookingUser(BookingUser bookingUser) throws ClassNotFoundException, SQLException {
		save("DELETE FROM booking_user WHERE booking_id = ? AND user_id = ?", new Object[] { 
				bookingUser.getBooking_id(), bookingUser.getUser_id() });
	}

	public List<BookingUser> readBookingUsers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_user", null);
	}

	public List<BookingUser> readBookingUsersById(Integer booking_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_user WHERE booking_id = ?", new Object[] {
				booking_id
		});
	}

	public List<BookingUser> readBookingUsersByUserId(Integer user_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_user WHERE user_id = ?", new Object[] {
				user_id
		});
	}
	
	@Override
	public List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingUser> bookingUsers = new ArrayList<>();
		while (rs.next()) {
			BookingUser bookingUser = new BookingUser();
			bookingUser.setBooking_id(rs.getInt("booking_id"));
			bookingUser.setUser_id(rs.getInt("user_id"));
			bookingUsers.add(bookingUser);
		}
		return bookingUsers;
	}

}
