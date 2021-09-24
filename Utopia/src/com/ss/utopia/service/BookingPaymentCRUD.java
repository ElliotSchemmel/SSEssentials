package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.BookingPaymentDAO;
import com.ss.utopia.entity.BookingPayment;

public interface BookingPaymentCRUD {
	
	Util util = new Util();

	default String addBookingPayment(BookingPayment bookingPayment) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
			bpdao.addBookingPayment(bookingPayment);
			
			conn.commit();
			return "BookingPayment added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingPayment could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<BookingPayment> readBookingPayments() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
		List<BookingPayment> bookingPayments = bpdao.readBookingPayments();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingPayments;
	}

	default List<BookingPayment> readBookingPaymentsById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
		List<BookingPayment> bookingPayments = bpdao.readBookingPaymentsById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingPayments;
		
	}

	default String updateBookingPayment(BookingPayment bookingPayment) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
			bpdao.updateBookingPayment(bookingPayment);
			
			conn.commit();
			return "BookingPayment updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingPayment could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookingPayment(BookingPayment bookingPayment) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
			bpdao.deleteBookingPayment(bookingPayment);
			
			conn.commit();
			return "BookingPayment deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingPayment could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
