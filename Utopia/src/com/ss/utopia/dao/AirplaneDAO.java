package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airplane;

public class AirplaneDAO extends BaseDAO<Airplane>{
	
	public AirplaneDAO(Connection conn) { super(conn); }
	
	public void addAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("INSERT INTO airplane (type_id) VALUES (?)", new Object[] {
				airplane.getType_id()}); 
	}

	public void updateAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("UPDATE airplane SET type_id = ? WHERE id = ?", new Object[] { 
				airplane.getType_id(), airplane.getId()});
	}

	public void deleteAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("DELETE FROM airplane WHERE id = ?", new Object[] { airplane.getId() });
	}

	public List<Airplane> readAirplanes() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane", null);
	}
	
	public List<Airplane> readAirplanesById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airplane WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> airplanes = new ArrayList<>();
		while (rs.next()) {
			Airplane airplane = new Airplane();
			airplane.setId(rs.getInt("id"));
			airplane.setType_id(rs.getInt("type_id"));
			airplanes.add(airplane);
		}
		return airplanes;
	}

}
