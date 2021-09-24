package com.ss.utopia.entity;

public class Airport {
	
	private String iata_id; // must be 3 chars and all uppercase
	private String city;

	public String getIata_id() {
		return iata_id;
	}
	public void setIata_id(String iata_id) {
		this.iata_id = iata_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
