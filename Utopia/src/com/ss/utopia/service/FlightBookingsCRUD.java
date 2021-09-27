package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.FlightBookingsDAO;
import com.ss.utopia.entity.FlightBookings;

public interface FlightBookingsCRUD {
	
	Util util = new Util();

	default String addFlightBookings(FlightBookings flightBookings) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
			fbdao.addFlightBookings(flightBookings);
			
			conn.commit();
			return "FlightBookings added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "FlightBookings could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<FlightBookings> readFlightBookings() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
		List<FlightBookings> flightBookingss = fbdao.readFlightBookings();
		
		if (conn != null) {
			conn.close();
		}
		
		return flightBookingss;
	}

	default List<FlightBookings> readFlightBookingsByBookingId(Integer booking_id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
		List<FlightBookings> flightBookingss = fbdao.readFlightBookingsByBookingId(booking_id);
		
		if (conn != null) {
			conn.close();
		}
		
		return flightBookingss;
	}

	default String updateFlightBookingsFlight_id(FlightBookings flightBookings) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
			fbdao.updateFlightBookingsFlight_id(flightBookings);
			
			conn.commit();
			return "FlightBookings flight_id updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "FlightBookings flight_id could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String updateFlightBookingsBooking_id(FlightBookings flightBookings) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
			fbdao.updateFlightBookingsBooking_id(flightBookings);
			
			conn.commit();
			return "FlightBookings booking_id updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "FlightBookings booking_id could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deleteFlightBookings(FlightBookings flightBookings) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			FlightBookingsDAO fbdao = new FlightBookingsDAO(conn);
			fbdao.deleteFlightBookings(flightBookings);
			
			conn.commit();
			return "FlightBookings deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "FlightBookings could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}
