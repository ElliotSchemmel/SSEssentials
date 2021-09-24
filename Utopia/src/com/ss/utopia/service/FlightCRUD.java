package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Flight;

public interface FlightCRUD {
	
	Util util = new Util();

	default String addFlight(Flight flight) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.addFlight(flight);
			
			conn.commit();
			return "Flight added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Flight could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Flight> readFlights() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		FlightDAO fdao = new FlightDAO(conn);
		List<Flight> flights = fdao.readFlights();
		
		if (conn != null) {
			conn.close();
		}
		
		return flights;
	}

	default List<Flight> readFlightsById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		FlightDAO fdao = new FlightDAO(conn);
		List<Flight> flights = fdao.readFlightsById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return flights;
		
	}

	default String updateFlight(Flight flight) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			
			conn.commit();
			return "Flight updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Flight could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteFlight(Flight flight) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.deleteFlight(flight);
			
			conn.commit();
			return "Flight deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Flight could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
