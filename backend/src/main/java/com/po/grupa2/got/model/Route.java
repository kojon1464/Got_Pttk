package com.po.grupa2.got.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "routes")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String description;
	
    @ManyToOne
    @JoinColumn(name="start_id", nullable=false)
	private Localization start;
	
    @ManyToOne
    @JoinColumn(name="end_id", nullable=false)
	private Localization end;
    
    @JsonIgnore
	@OneToMany(mappedBy = "route")
	private Set<RouteState> routeStates;
    
    public Route() {
    	
    }

	public Route(String description, Localization start, Localization end) {
		this.description = description;
		this.start = start;
		this.end = end;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Localization getStart() {
		return start;
	}

	public void setStart(Localization start) {
		this.start = start;
	}

	public Localization getEnd() {
		return end;
	}

	public void setEnd(Localization end) {
		this.end = end;
	}

	public Set<RouteState> getRouteStates() {
		return routeStates;
	}
}
