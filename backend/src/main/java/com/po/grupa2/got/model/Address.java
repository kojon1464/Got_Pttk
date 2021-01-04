package com.po.grupa2.got.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String town;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	private String street;
	
	@Column(name = "bulding_number")
	private String buildingNumber;
	
	@Column(name = "flat_number")
	private String flatNumber;

	public Address() {
	}

	public Address(String town, String postalCode, String street, String buildingNumber, String flatNumber) {
		this.town = town;
		this.postalCode = postalCode;
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.flatNumber = flatNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
}
