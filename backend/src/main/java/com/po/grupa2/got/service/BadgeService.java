package com.po.grupa2.got.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.BadgeRankRepository;

@Service
public class BadgeService {

	@Autowired
	private BadgeRankRepository badgeRankRepository;
	
	public List<BadgeRank> getBadgesToCollect(User user) {
		List<BadgeRank> ranks = badgeRankRepository.findAll();
		Set<Long> achivements = user.getAchievents().stream().map((a) -> a.getBadgeRank().getId()).collect(Collectors.toSet());
		
		return ranks.stream().filter((r) -> !achivements.contains(r.getId())).collect(Collectors.toList());
	}
}
