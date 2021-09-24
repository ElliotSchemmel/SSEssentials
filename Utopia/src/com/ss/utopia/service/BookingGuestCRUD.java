package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.BookingGuestDAO;
import com.ss.utopia.entity.BookingGuest;

public interface BookingGuestCRUD {
	
	Util util = new Util();

	default String addBookingGuest(BookingGuest bookingGuest) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingGuestDAO bgdao = new BookingGuestDAO(conn);
			bgdao.addBookingGuest(bookingGuest);
			
			conn.commit();
			return "BookingGuest added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingGuest could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<BookingGuest> readBookingGuests() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingGuestDAO bgdao = new BookingGuestDAO(conn);
		List<BookingGuest> bookingGuests = bgdao.readBookingGuests();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingGuests;
	}

	default List<BookingGuest> readBookingGuestsById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingGuestDAO bgdao = new BookingGuestDAO(conn);
		List<BookingGuest> bookingGuests = bgdao.readBookingGuestsById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingGuests;
		
	}

	default String updateBookingGuest(BookingGuest bookingGuest) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingGuestDAO bgdao = new BookingGuestDAO(conn);
			bgdao.updateBookingGuest(bookingGuest);
			
			conn.commit();
			return "BookingGuest updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingGuest could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookingGuest(BookingGuest bookingGuest) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingGuestDAO bgdao = new BookingGuestDAO(conn);
			bgdao.deleteBookingGuest(bookingGuest);
			
			conn.commit();
			return "BookingGuest deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingGuest could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
