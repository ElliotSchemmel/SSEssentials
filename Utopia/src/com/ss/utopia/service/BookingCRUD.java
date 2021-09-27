package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.entity.Booking;

public interface BookingCRUD {
	
	Util util = new Util();

	default String addBooking(Booking booking) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.addBooking(booking);
			
			conn.commit();
			return "Booking added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Booking could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default Integer addBookingReturnPK(Booking booking) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			Integer pk = bdao.addBookingReturnPK(booking);
			
			conn.commit();
			return pk;

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return 0;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Booking> readBookings() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingDAO bdao = new BookingDAO(conn);
		List<Booking> bookings = bdao.readBookings();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookings;
	}

	default List<Booking> readBookingsById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingDAO bdao = new BookingDAO(conn);
		List<Booking> bookings = bdao.readBookingsById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookings;
		
	}

	default String updateBooking(Booking booking) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.updateBooking(booking);
			
			conn.commit();
			return "Booking updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Booking could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBooking(Booking booking) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.deleteBooking(booking);
			
			conn.commit();
			return "Booking deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Booking could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
