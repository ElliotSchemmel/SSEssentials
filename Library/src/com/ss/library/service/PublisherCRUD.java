package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.PublisherDAO;
import com.ss.library.entity.Publisher;

public interface PublisherCRUD {
	
	Util util = new Util();

	default String addPublisher(Publisher publisher) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.addPublisher(publisher);
			
			conn.commit();
			return "Publisher added successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Publisher could not be added";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	default List<Publisher> readPublishers() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		List<Publisher> publishers = pdao.readAllPublishers();
		
		if (conn != null) {
			conn.close();
		}
		
		return publishers;
	}

	default List<Publisher> readPublishersById(Integer pubId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		
		conn = util.getConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		List<Publisher> publisher = pdao.readPublishersById(pubId);
		
		if (conn != null) {
			conn.close();
		}
		
		return publisher;
		
	}

	default String updatePublisher(Publisher publisher) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.updatePublisher(publisher);
			
			conn.commit();
			return "Publisher updated successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Publisher could not be updated";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	default String deletePublisher(Publisher publisher) throws SQLException {
		
		Connection conn = null;
		
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
			
			conn.commit();
			return "Publisher deleted successfully";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return "Publisher could not be deleted";

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}