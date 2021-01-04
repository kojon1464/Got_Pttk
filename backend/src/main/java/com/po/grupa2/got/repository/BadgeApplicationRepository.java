package com.po.grupa2.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.BadgeApplication;
import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.User;

@Repository
public interface BadgeApplicationRepository extends JpaRepository<BadgeApplication, Long> {
	
	public BadgeApplication findTopByBadgeRankAndUserOrderByCreationDateDesc(BadgeRank rank, User user);
	public List<BadgeApplication> findByUserOrderByCreationDate(User user);
}
