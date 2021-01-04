package com.po.grupa2.got.dto;

import com.po.grupa2.got.model.BadgeApplication;
import com.po.grupa2.got.model.BadgeRank;

public class BadgeDetailsDTO {

	private BadgeRank rank;
	
	private BadgeApplication badgeApplication;
	
	private int points;
	
	private int pointsExcessive;

	public BadgeDetailsDTO(BadgeRank rank, BadgeApplication badgeApplication, int points, int pointsExcessive) {
		this.rank = rank;
		this.badgeApplication = badgeApplication;
		this.points = points;
		this.pointsExcessive = pointsExcessive;
	}

	public BadgeRank getRank() {
		return rank;
	}

	public void setRank(BadgeRank rank) {
		this.rank = rank;
	}

	public BadgeApplication getBadgeApplication() {
		return badgeApplication;
	}

	public void setBadgeApplication(BadgeApplication badgeApplication) {
		this.badgeApplication = badgeApplication;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPointsExcessive() {
		return pointsExcessive;
	}

	public void setPointsExcessive(int pointsExcessive) {
		this.pointsExcessive = pointsExcessive;
	}
}
