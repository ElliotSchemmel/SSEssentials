package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Route;

public interface RouteCRUD {
	
	Util util = new Util();

	default String addRoute(Route route) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			rdao.addRoute(route);
			
			conn.commit();
			return "Route added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Route could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Route> readRoutes() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		RouteDAO rdao = new RouteDAO(conn);
		List<Route> routes = rdao.readRoutes();
		
		if (conn != null) {
			conn.close();
		}
		
		return routes;
	}

	default List<Route> readRoutesById(Integer id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		RouteDAO rdao = new RouteDAO(conn);
		List<Route> routes = rdao.readRoutesById(id);
		
		if (conn != null) {
			conn.close();
		}
		
		return routes;
		
	}

	default String updateRoute(Route route) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			rdao.updateRoute(route);
			
			conn.commit();
			return "Route updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Route could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteRoute(Route route) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			rdao.deleteRoute(route);
			
			conn.commit();
			return "Route deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Route could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
