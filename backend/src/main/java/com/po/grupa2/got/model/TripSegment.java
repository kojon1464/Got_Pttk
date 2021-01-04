package com.po.grupa2.got.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class TripSegment {

	@EmbeddedId
	private TripSegmentKey id;
	
	@ManyToOne
	@MapsId("tripId")
	@JoinColumn(name = "trip_id")
	private Trip trip;
	
	@ManyToOne
	@MapsId("routeId")
	@JoinColumn(name = "route_id")
	private Route route;
	
	@Column(name = "order_number")
	private int orderNumber;

	public TripSegment() {
		this.id = new TripSegmentKey();
	}

	public TripSegment(Trip trip, Route route, int orderNumber) {
		this.id = new TripSegmentKey();
		this.trip = trip;
		this.route = route;
		this.orderNumber = orderNumber;
	}

	public TripSegmentKey getId() {
		return id;
	}

	public void setId(TripSegmentKey id) {
		this.id = id;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
}
