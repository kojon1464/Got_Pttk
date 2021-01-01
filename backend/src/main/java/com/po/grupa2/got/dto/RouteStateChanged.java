package com.po.grupa2.got.dto;

import com.po.grupa2.got.model.RouteState;

public class RouteStateChanged {

	private RouteState state;
	private String message;
	
	public RouteStateChanged(RouteState state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	
	public RouteState getState() {
		return state;
	}
	public void setState(RouteState state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
