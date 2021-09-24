package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Flight;

public class FlightDAO extends BaseDAO<Flight>{
	
	public FlightDAO(Connection conn) { super(conn); }
	
	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("INSERT INTO flight (id, route_id, airplane_id, departure_time, reserved_seats, seat_price) "
				+ "VALUES (?, ?, ?, ?, ?, ?)", new Object[] {
				flight.getId(), flight.getRoute_id(), flight.getAirplane_id(), 
				flight.getDeparture_time(), flight.getReserved_seats(), flight.getSeat_price()
				}); 
	}

	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("UPDATE flight SET "
				+ "route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? "
				+ "WHERE id = ?", new Object[] { 
				flight.getRoute_id(), flight.getAirplane_id(), flight.getDeparture_time(), 
				flight.getReserved_seats(), flight.getSeat_price(), flight.getId()});
		}

	public void deleteFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("DELETE FROM flight WHERE id = ?", new Object[] { flight.getId() });
	}

	public List<Flight> readFlights() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight", null);
	}
	
	public List<Flight> readFlightsById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> flights = new ArrayList<>();
		while (rs.next()) {
			Flight flight = new Flight();
			flight.setId(rs.getInt("id"));
			flight.setRoute_id(rs.getInt("route_id"));
			flight.setAirplane_id(rs.getInt("airplane_id"));
			flight.setDeparture_time(rs.getTimestamp("departure_time"));
			flight.setReserved_seats(rs.getInt("reserved_seats"));
			flight.setSeat_price(rs.getFloat("seat_price"));
			flights.add(flight);
		}
		return flights;
	}

}
