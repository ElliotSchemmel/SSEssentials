package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("insert into tbl_publisher values (?, ?)", new Object[] {
				publisher.getPublisherName(),
				publisher.getPublisherAddress()
		});
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ? where publisherId = ?", new Object[] {
				publisher.getPublisherName(),
				publisher.getPublisherAddress(),
				publisher.getPublisherId()
		});
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		save("delete from tbl_publisher where publisherId = ?", new Object[] {
				publisher.getPublisherId()
		});
	}

	public List<Publisher> readPublishersById(Integer publisherId) throws SQLException, ClassNotFoundException {
		return read("select * from tbl_publisher where publisherId = ?", new Object[] {
				publisherId
		});
	}

	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
		return read("select * from tbl_publisher", null);
	}

	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			publishers.add(p);
		}
		return publishers;
	}
	
}