package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Route;

public class RouteDAO extends BaseDAO<Route>{
	
	public RouteDAO(Connection conn) { super(conn); }
	
	public void addRoute(Route route) throws ClassNotFoundException, SQLException {
		save("INSERT INTO route (origin_id, destination_id) VALUES (?, ?)", new Object[] {
				route.getOrigin_id(), route.getDestination_id()}); 
	}

	public void updateRoute(Route route) throws ClassNotFoundException, SQLException {
		save("UPDATE route SET origin_id = ?, destination_id = ? WHERE id = ?", new Object[] { 
				route.getOrigin_id(), route.getDestination_id(), route.getId()});
	}

	public void deleteRoute(Route route) throws ClassNotFoundException, SQLException {
		save("DELETE FROM route WHERE id = ?", new Object[] { route.getId() });
	}

	public List<Route> readRoutes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route", null);
	}
	
	public List<Route> readRoutesById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM route WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> routes = new ArrayList<>();
		while (rs.next()) {
			Route route = new Route();
			route.setId(rs.getInt("id"));
			route.setOrigin_id(rs.getString("origin_id"));
			route.setDestination_id(rs.getString("destination_id"));
			routes.add(route);
		}
		return routes;
	}

}
