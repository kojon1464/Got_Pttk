package com.po.grupa2.got.dto;

import java.util.List;

import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.TripSegment;

public class TripDTO {

	private Trip trip;
	private List<TripSegment> segments;
	
	public TripDTO() {
		super();
	}

	public TripDTO(Trip trip, List<TripSegment> segments) {
		this.trip = trip;
		this.segments = segments;
	}
	
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public List<TripSegment> getSegments() {
		return segments;
	}
	public void setSegments(List<TripSegment> segments) {
		this.segments = segments;
	}
}
