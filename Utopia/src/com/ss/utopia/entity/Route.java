package com.ss.utopia.entity;

public class Route {

	private Integer id;
	private String origin_id; // must be 3 chars and upper 
	private String destination_id; // must be 3 chars and upper

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrigin_id() {
		return origin_id;
	}
	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}
	public String getDestination_id() {
		return destination_id;
	}
	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}
}
