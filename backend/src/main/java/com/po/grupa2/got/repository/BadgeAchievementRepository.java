package com.po.grupa2.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.po.grupa2.got.model.BadgeAchievement;
import com.po.grupa2.got.model.BadgeArchievmentKey;
import com.po.grupa2.got.model.User;

@Repository
public interface BadgeAchievementRepository extends JpaRepository<BadgeAchievement, BadgeArchievmentKey> {
	
	public List<BadgeAchievement> findByUserOrderByDate(User user);
}
