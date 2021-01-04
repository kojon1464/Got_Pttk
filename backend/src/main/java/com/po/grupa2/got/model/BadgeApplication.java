package com.po.grupa2.got.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BadgeApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "check_date")
	private Date checkDate;
	
	private boolean checked;
	
	private boolean positive;
	
    @OneToOne
    @JoinColumn(nullable = false, name = "badge_rank_id")
	private BadgeRank badgeRank;
    
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
	private User user;
    
    @OneToOne
    @JoinColumn(name = "address_id")
	private Address address;

	public BadgeApplication() {
	}

	public BadgeApplication(Date creationDate, boolean checked, boolean positive, BadgeRank bagdeRank,
			User user) {
		super();
		this.creationDate = creationDate;
		this.checked = checked;
		this.positive = positive;
		this.badgeRank = bagdeRank;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

	public BadgeRank getBadgeRank() {
		return badgeRank;
	}

	public void setBadgeRank(BadgeRank bagdeRank) {
		this.badgeRank = bagdeRank;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
