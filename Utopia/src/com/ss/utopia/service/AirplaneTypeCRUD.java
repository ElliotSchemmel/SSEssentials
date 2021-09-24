package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.entity.AirplaneType;

public interface AirplaneTypeCRUD {
	
	Util util = new Util();

	default String addAirplaneType(AirplaneType airplaneType) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
			atdao.addAirplaneType(airplaneType);
			
			conn.commit();
			return "AirplaneType added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "AirplaneType could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<AirplaneType> readAirplaneTypes() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
		List<AirplaneType> airplaneTypes = atdao.readAirplaneTypes();
		
		if (conn != null) {
			conn.close();
		}
		
		return airplaneTypes;
	}

	default List<AirplaneType> readAirplaneTypesById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
		List<AirplaneType> airplaneTypes = atdao.readAirplaneTypesById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return airplaneTypes;
		
	}

	default String updateAirplaneType(AirplaneType airplaneType) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
			atdao.updateAirplaneType(airplaneType);
			
			conn.commit();
			return "AirplaneType updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "AirplaneType could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteAirplaneType(AirplaneType airplaneType) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			AirplaneTypeDAO atdao = new AirplaneTypeDAO(conn);
			atdao.deleteAirplaneType(airplaneType);
			
			conn.commit();
			return "AirplaneType deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "AirplaneType could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
