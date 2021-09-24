package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.entity.Airplane;

public interface AirplaneCRUD {
	
	Util util = new Util();

	default String addAirplane(Airplane airplane) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			adao.addAirplane(airplane);
			
			conn.commit();
			return "Airplane added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airplane could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Airplane> readAirplanes() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirplaneDAO adao = new AirplaneDAO(conn);
		List<Airplane> airplanes = adao.readAirplanes();
		
		if (conn != null) {
			conn.close();
		}
		
		return airplanes;
	}

	default List<Airplane> readAirplanesById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirplaneDAO adao = new AirplaneDAO(conn);
		List<Airplane> airplanes = adao.readAirplanesById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return airplanes;
		
	}

	default String updateAirplane(Airplane airplane) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			adao.updateAirplane(airplane);
			
			conn.commit();
			return "Airplane updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airplane could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteAirplane(Airplane airplane) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			adao.deleteAirplane(airplane);
			
			conn.commit();
			return "Airplane deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Airplane could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
