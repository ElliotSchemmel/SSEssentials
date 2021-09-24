package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.entity.BookingAgent;

public class BookingAgentDAO extends BaseDAO<BookingAgent>{
	
	public BookingAgentDAO(Connection conn) { super(conn); }
	
	public void addBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("INSERT INTO booking_agent (booking_id, agent_id) VALUES (?, ?)", new Object[] {
				bookingAgent.getBooking_id(), bookingAgent.getAgent_id()}); 
	}

	public void updateBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("UPDATE booking_agent SET agent_id = ? WHERE booking_id = ?", new Object[] { 
				bookingAgent.getAgent_id(), bookingAgent.getBooking_id()});
	}

	public void deleteBookingAgent(BookingAgent bookingAgent) throws ClassNotFoundException, SQLException {
		save("DELETE FROM booking_agent WHERE booking_id = ? AND agent_id = ?", new Object[] { 
				bookingAgent.getBooking_id(), bookingAgent.getAgent_id() });
	}

	public List<BookingAgent> readBookingAgents() throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_agent", null);
	}

	public List<BookingAgent> readBookingAgentsById(Integer booking_id) throws ClassNotFoundException, SQLException {
		return read("SELECT * FROM booking_agent WHERE booking_id = ?", new Object[] {
				booking_id
		});
	}
	
	@Override
	public List<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingAgent> bookingAgents = new ArrayList<>();
		while (rs.next()) {
			BookingAgent bookingAgent = new BookingAgent();
			bookingAgent.setBooking_id(rs.getInt("booking_id"));
			bookingAgent.setAgent_id(rs.getInt("agent_id"));
			bookingAgents.add(bookingAgent);
		}
		return bookingAgents;
	}

}
