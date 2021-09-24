package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.FlightBookings;

public class FlightBookingsDAO extends BaseDAO<FlightBookings>{
	
	public FlightBookingsDAO(Connection conn) { super(conn); }
	
	public void addFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("INSERT INTO flight_bookings (flight_id, booking_id) VALUES (?, ?)", new Object[] {
				flightBookings.getFlight_id(), flightBookings.getBooking_id()}); 
	}

	public void updateFlightBookingsBooking_id(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("UPDATE flight_bookings SET booking_id = ? WHERE flight_id = ?", new Object[] { 
				flightBookings.getBooking_id(), flightBookings.getFlight_id()});
	}

	public void updateFlightBookingsFlight_id(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("UPDATE flight_bookings SET flight_id = ? WHERE booking_id = ?", new Object[] { 
				flightBookings.getFlight_id(), flightBookings.getBooking_id()});
	}

	public void deleteFlightBookings(FlightBookings flightBookings) throws ClassNotFoundException, SQLException {
		save("DELETE FROM flight_bookings WHERE flight_id = ? AND booking_id = ?", new Object[] { 
				flightBookings.getFlight_id(), flightBookings.getBooking_id() });
	}

	public List<FlightBookings> readFlightBookings() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM flight_bookings", null);
	}
	
	@Override
	public List<FlightBookings> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBookings> flightBookingss = new ArrayList<>();
		while (rs.next()) {
			FlightBookings flightBookings = new FlightBookings();
			flightBookings.setFlight_id(rs.getInt("flight_id"));
			flightBookings.setBooking_id(rs.getInt("booking_id"));
			flightBookingss.add(flightBookings);
		}
		return flightBookingss;
	}

}
