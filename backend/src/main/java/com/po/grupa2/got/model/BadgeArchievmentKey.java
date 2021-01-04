package com.po.grupa2.got.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BadgeArchievmentKey implements Serializable {

	private static final long serialVersionUID = -4554980705294986191L;

	@Column(name = "badge_rank_id")
	private Long badgeRankId;
	
	@Column(name = "user_id")	
	private Long userId;

	public BadgeArchievmentKey() {
	}

	public BadgeArchievmentKey(Long badgeRankId, Long userId) {
		this.badgeRankId = badgeRankId;
		this.userId = userId;
	}

	public Long getBadgeRankId() {
		return badgeRankId;
	}

	public void setBadgeRankId(Long badgeRankId) {
		this.badgeRankId = badgeRankId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badgeRankId == null) ? 0 : badgeRankId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadgeArchievmentKey other = (BadgeArchievmentKey) obj;
		if (badgeRankId == null) {
			if (other.badgeRankId != null)
				return false;
		} else if (!badgeRankId.equals(other.badgeRankId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
