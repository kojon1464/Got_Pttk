package com.po.grupa2.got.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "badge_ranks")
public class BadgeRank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Rank rank;
	
	private String description;
	
	private int points;
	
	private boolean with_excess;
	
    @ManyToOne
    @JoinColumn(nullable = false, name = "badge_id")
	private Badge badge;

	public BadgeRank() {
	}

	public BadgeRank(Rank rank, String description, int points, boolean with_excess, Badge bagde) {
		super();
		this.rank = rank;
		this.description = description;
		this.points = points;
		this.with_excess = with_excess;
		this.badge = bagde;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isWith_excess() {
		return with_excess;
	}

	public void setWith_excess(boolean with_excess) {
		this.with_excess = with_excess;
	}

	public Badge getBadge() {
		return badge;
	}

	public void setBadge(Badge bagde) {
		this.badge = bagde;
	}
	
	public static enum Rank {
		BRONZE, SILVER, GOLD, POPULAR
	}
}
