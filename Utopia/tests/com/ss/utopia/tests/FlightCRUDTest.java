package com.ss.utopia.tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.service.AdminService;
import com.ss.utopia.service.Util;

public class FlightCRUDTest {
	
	Util util = new Util();
	
	Flight flight = new Flight();
	
	AdminService admin = new AdminService();
	
	@Test
	public void addFlightTest() throws SQLException {
		
		Connection conn = null;
		Flight flight = new Flight();
		
		flight.setId(200);
		flight.setRoute_id(1);
		flight.setAirplane_id(1);
		flight.setDeparture_time(Timestamp.valueOf("2021-10-01 11:30:00"));
		flight.setReserved_seats(2);
		flight.setSeat_price((float) 2.0);
		
		try {
			conn = util.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.addFlight(flight);
			
			assertEquals(flight.getId(), admin.readFlightsById(flight.getId()).get(0).getId());

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				conn.rollback();
				conn.close();
			}
		}
	}
}
