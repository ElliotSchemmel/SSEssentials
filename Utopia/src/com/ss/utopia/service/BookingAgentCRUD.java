package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.BookingAgentDAO;
import com.ss.utopia.entity.BookingAgent;

public interface BookingAgentCRUD {
	
	Util util = new Util();

	default String addBookingAgent(BookingAgent bookingAgent) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingAgentDAO badao = new BookingAgentDAO(conn);
			badao.addBookingAgent(bookingAgent);
			
			conn.commit();
			return "BookingAgent added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingAgent could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<BookingAgent> readBookingAgents() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingAgentDAO badao = new BookingAgentDAO(conn);
		List<BookingAgent> bookingAgents = badao.readBookingAgents();
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingAgents;
	}

	default List<BookingAgent> readBookingAgentsById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		BookingAgentDAO badao = new BookingAgentDAO(conn);
		List<BookingAgent> bookingAgents = badao.readBookingAgentsById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return bookingAgents;
		
	}

	default String updateBookingAgent(BookingAgent bookingAgent) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingAgentDAO badao = new BookingAgentDAO(conn);
			badao.updateBookingAgent(bookingAgent);
			
			conn.commit();
			return "BookingAgent updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingAgent could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteBookingAgent(BookingAgent bookingAgent) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			BookingAgentDAO badao = new BookingAgentDAO(conn);
			badao.deleteBookingAgent(bookingAgent);
			
			conn.commit();
			return "BookingAgent deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "BookingAgent could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
