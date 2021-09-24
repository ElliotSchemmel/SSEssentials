package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Airport;

public class AirportDAO extends BaseDAO<Airport>{
	
	public AirportDAO(Connection conn) { super(conn); }
	
	// iata_id values must be 3 letters all uppercase
	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("INSERT INTO airport (iata_id, city) VALUES (?, ?)", new Object[] {
				airport.getIata_id(), airport.getCity()}); 
	}

	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("UPDATE airport SET city = ? WHERE iata_id = ?", new Object[] { 
				airport.getCity(), airport.getIata_id()});
	}

	public void deleteAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("DELETE FROM airport WHERE iata_id = ?", new Object[] { airport.getIata_id() });
	}

	public List<Airport> readAirports() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airport", null);
	}
	
	public List<Airport> readAirportsByIata_id(String iata_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM airport WHERE iata_id = ?",
						new Object[] { iata_id });
	}

	@Override
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> airports = new ArrayList<>();
		while (rs.next()) {
			Airport airport = new Airport();
			airport.setIata_id(rs.getString("iata_id"));
			airport.setCity(rs.getString("city"));
			airports.add(airport);
		}
		return airports;
	}

}
