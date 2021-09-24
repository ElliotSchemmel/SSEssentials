package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.entity.Airport;

public interface AirportCRUD {
	
	Util util = new Util();

	default String addAirport(Airport airport) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.addAirport(airport);
			
			conn.commit();
			return "Airport added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Airport> readAirports() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirportDAO adao = new AirportDAO(conn);
		List<Airport> airports = adao.readAirports();
		
		if (conn != null) {
			conn.close();
		}
		
		return airports;
	}

	default List<Airport> readAirportsById(String iata_id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirportDAO adao = new AirportDAO(conn);
		List<Airport> airports = adao.readAirportsByIata_id(iata_id);
		
		if (conn != null) {
			conn.close();
		}
		
		return airports;
		
	}

	default String updateAirport(Airport airport) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.updateAirport(airport);
			
			conn.commit();
			return "Airport updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteAirport(Airport airport) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.deleteAirport(airport);
			
			conn.commit();
			return "Airport deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airport could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
