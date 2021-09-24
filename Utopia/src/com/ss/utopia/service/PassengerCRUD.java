package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.entity.Passenger;

public interface PassengerCRUD {
	
	Util util = new Util();

	default String addPassenger(Passenger passenger) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.addPassenger(passenger);
			
			conn.commit();
			return "Passenger added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Passenger could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Passenger> readPassengers() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		PassengerDAO pdao = new PassengerDAO(conn);
		List<Passenger> passengers = pdao.readPassengers();
		
		if (conn != null) {
			conn.close();
		}
		
		return passengers;
	}

	default List<Passenger> readPassengersById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		PassengerDAO pdao = new PassengerDAO(conn);
		List<Passenger> passengers = pdao.readPassengersById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return passengers;
		
	}

	default String updatePassenger(Passenger passenger) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.updatePassenger(passenger);
			
			conn.commit();
			return "Passenger updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Passenger could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deletePassenger(Passenger passenger) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.deletePassenger(passenger);
			
			conn.commit();
			return "Passenger deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Passenger could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
