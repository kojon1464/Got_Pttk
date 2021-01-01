package com.po.grupa2.got.dto;

import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;

public class RouteDTO {

	private Route route;
	private RouteState currentState;
	
	public RouteDTO(Route route, RouteState currentState) {
		super();
		this.route = route;
		this.currentState = currentState;
	}
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public RouteState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(RouteState currentState) {
		this.currentState = currentState;
	}
}
