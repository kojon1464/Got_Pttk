package com.po.grupa2.got.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "route_states")
public class RouteState {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date start;
	
	@Temporal(TemporalType.DATE)
	private Date end;
	
	private boolean open;
	
	@Column(name = "points_there")
	private int pointsThere;
	
	@Column(name = "points_back")
	private int pointsBack;
	
    @ManyToOne
    @JoinColumn(name="route_id", nullable=false)
	private Route route;
    
    public RouteState() {
    	
    }

	public RouteState(Date start, Date end, boolean open, int pointsThere, int pointsBack, Route route) {
		this.start = start;
		this.end = end;
		this.open = open;
		this.pointsThere = pointsThere;
		this.pointsBack = pointsBack;
		this.route = route;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getPointsThere() {
		return pointsThere;
	}

	public void setPointsThere(int pointsThere) {
		this.pointsThere = pointsThere;
	}

	public int getPointsBack() {
		return pointsBack;
	}

	public void setPointsBack(int pointsBack) {
		this.pointsBack = pointsBack;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
