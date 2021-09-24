package com.ss.utopia.entity;

import java.sql.Timestamp;

public class Flight {

	private Integer id;
	private Integer route_id;
	private Integer airplane_id;
	private Timestamp departure_time;
	private Integer reserved_seats;
	private Float seat_price;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
	}
	public Integer getAirplane_id() {
		return airplane_id;
	}
	public void setAirplane_id(Integer airplane_id) {
		this.airplane_id = airplane_id;
	}
	public Timestamp getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Timestamp timestamp) {
		this.departure_time = timestamp;
	}
	public Integer getReserved_seats() {
		return reserved_seats;
	}
	public void setReserved_seats(Integer reserved_seats) {
		this.reserved_seats = reserved_seats;
	}
	public Float getSeat_price() {
		return seat_price;
	}
	public void setSeat_price(Float seat_price) {
		this.seat_price = seat_price;
	}
	
}
