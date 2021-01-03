package com.po.grupa2.got.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trips")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String name;
	
	private String description;
	
	private boolean isConfirmed;
	
    @ManyToOne
    @JoinColumn(name="localization_id")
	private Localization start;
	
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
	private User user;
    
    @OneToMany(mappedBy = "trip")
    @JsonIgnore
    @OrderBy("order_number")
    private List<TripSegment> segments;
    
	public Trip() {
	}

	public Trip(Date date, String name, String description, boolean isConfirmed, Localization start, User user) {
		this.date = date;
		this.name = name;
		this.description = description;
		this.isConfirmed = isConfirmed;
		this.start = start;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Localization getStart() {
		return start;
	}

	public void setStart(Localization start) {
		this.start = start;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TripSegment> getSegments() {
		return segments;
	}

	public void setSegments(List<TripSegment> segments) {
		this.segments = segments;
	}
}
