package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.Passenger;

public class PassengerDAO extends BaseDAO<Passenger>{
	
	public PassengerDAO(Connection conn) { super(conn); }
	
	public void addPassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("INSERT INTO passenger (booking_id, given_name, family_name, dob, gender, address) "
				+ "VALUES (?, ?, ?, ?, ?, ?)", new Object[] {
				passenger.getBooking_id(), passenger.getGiven_name(), passenger.getFamily_name(), 
				passenger.getDob(), passenger.getGender(), passenger.getAddress()
				}); 
	}

	public void updatePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("UPDATE passenger SET "
				+ "booking_id = ?, given_name = ?, family_name = ?, dob = ?, gender = ?, address = ? "
				+ "WHERE id = ?", new Object[] { 
				passenger.getBooking_id(), passenger.getGiven_name(), passenger.getFamily_name(), 
				passenger.getDob(), passenger.getGender(), passenger.getAddress(), passenger.getId()});
		}

	public void deletePassenger(Passenger passenger) throws ClassNotFoundException, SQLException {
		save("DELETE FROM passenger WHERE id = ?", new Object[] { passenger.getId() });
	}

	public List<Passenger> readPassengers() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM passenger", null);
	}
	
	public List<Passenger> readPassengersById(Integer id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM passenger WHERE id = ?",
						new Object[] { id });
	}

	@Override
	public List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> passengers = new ArrayList<>();
		while (rs.next()) {
			Passenger passenger = new Passenger();
			passenger.setId(rs.getInt("id"));
			passenger.setBooking_id(rs.getInt("booking_id"));
			passenger.setGiven_name(rs.getString("given_name"));
			passenger.setFamily_name(rs.getString("family_name"));
			passenger.setDob(rs.getDate("dob"));
			passenger.setGender(rs.getString("gender"));
			passenger.setAddress(rs.getString("address"));
			passengers.add(passenger);
		}
		return passengers;
	}

}
