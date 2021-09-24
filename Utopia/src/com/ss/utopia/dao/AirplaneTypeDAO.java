package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.AirplaneType;

public class AirplaneTypeDAO extends BaseDAO<AirplaneType>{
	
	public AirplaneTypeDAO(Connection conn) { super(conn); }
	
	public void addAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("INSERT INTO airplane_type (max_capacity) VALUES (?)", new Object[] {
				airplaneType.getMax_capacity()}); 
	}

	public void updateAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane_type SET max_capacity = ? WHERE id = ?", new Object[] { 
				airplaneType.getMax_capacity(), airplaneType.getId()});
	}

	public void deleteAirplaneType(AirplaneType airplaneType) throws ClassNotFoundException, SQLException {
		save("DELETE FROM airplane_type WHERE id = ?", new Object[] { airplaneType.getId() });
	}

	public List<AirplaneType> readAirplaneTypes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane_type", null);
	}
	
	public List<AirplaneType> readAirplaneTypesById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane_type WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<AirplaneType> airplaneTypes = new ArrayList<>();
		while (rs.next()) {
			AirplaneType airplaneType = new AirplaneType();
			airplaneType.setId(rs.getInt("id"));
			airplaneType.setMax_capacity(rs.getInt("max_capacity"));
			airplaneTypes.add(airplaneType);
		}
		return airplaneTypes;
	}

}
