package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.entity.BookingUser;

public interface BookingUserCRUD {
	
	Util util = new Util();

	default String addBookingUser(BookingUser bookingUser) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingUserDAO budao = new BookingUserDAO(conn);
			budao.addBookingUser(bookingUser);
			
			conn.commit();
			return "BookingUser added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingUser could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<BookingUser> readBookingUsers() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingUserDAO budao = new BookingUserDAO(conn);
		List<BookingUser> bookingUsers = budao.readBookingUsers();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingUsers;
	}

	default List<BookingUser> readBookingUsersById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingUserDAO budao = new BookingUserDAO(conn);
		List<BookingUser> bookingUsers = budao.readBookingUsersById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingUsers;
		
	}

	default String updateBookingUser(BookingUser bookingUser) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingUserDAO budao = new BookingUserDAO(conn);
			budao.updateBookingUser(bookingUser);
			
			conn.commit();
			return "BookingUser updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingUser could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookingUser(BookingUser bookingUser) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingUserDAO budao = new BookingUserDAO(conn);
			budao.deleteBookingUser(bookingUser);
			
			conn.commit();
			return "BookingUser deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingUser could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
