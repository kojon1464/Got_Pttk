package com.po.grupa2.got.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "badge_archivements")
public class BadgeAchievement {

	@EmbeddedId
	private BadgeArchievmentKey id;
	
	@ManyToOne
	@MapsId("badgeRankId")
	@JoinColumn(name = "badge_rank_id")
	private BadgeRank badgeRank;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	public BadgeAchievement() {
		this.id = new BadgeArchievmentKey();
	}
	
	public BadgeAchievement(BadgeRank badgeRank, User user, Date date) {
		super();
		this.id = new BadgeArchievmentKey();
		this.badgeRank = badgeRank;
		this.user = user;
		this.date = date;
	}

	public BadgeArchievmentKey getId() {
		return id;
	}

	public void setId(BadgeArchievmentKey id) {
		this.id = id;
	}

	public BadgeRank getBadgeRank() {
		return badgeRank;
	}

	public void setBadgeRank(BadgeRank badgeRank) {
		this.badgeRank = badgeRank;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
