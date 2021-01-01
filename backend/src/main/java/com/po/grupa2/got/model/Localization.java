package com.po.grupa2.got.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "localizations")
public class Localization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "start")
	private Set<Route> routesStart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "end")
	private Set<Route> routesEnd;
	
	public Localization() {
		
	}
	
	public Localization(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Route> getRoutesStart() {
		return routesStart;
	}

	public Set<Route> getRoutesEnd() {
		return routesEnd;
	}
}
