package com.po.grupa2.got.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.po.grupa2.got.model.BadgeAchievement;
import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.BadgeRankRepository;


@RunWith(SpringRunner.class)
public class BadgeServiceTests {
	
    @TestConfiguration
    static class BadgeServiceTestContextConfiguration {
 
        @Bean
        public BadgeService badgeService() {
            return new BadgeService();
        }
    }
	
	@Autowired
	private BadgeService badgeService;
	
	@MockBean
	private BadgeRankRepository badgeRankRepository;
	
	@Mock
	User user;
	
    @Test
    public void getBadgesToCollect_empty_archievemnts() {     
    	Mockito.when(badgeRankRepository.findAll()).thenReturn(createBadgeRankList(new int[] {1, 2, 3, 4}));
        Mockito.when(user.getAchievents()).thenReturn(createBadgeAchievementsSet(new int[] {}));

        List<BadgeRank> result = badgeService.getBadgesToCollect(user);
        
        Assert.assertEquals(createBadgeRankList(new int[]{1,2,3,4}), result);
    }
    
    @Test
    public void getBadgesToCollect_all_badges_collected() {    
    	Mockito.when(badgeRankRepository.findAll()).thenReturn(createBadgeRankList(new int[] {1, 2, 3, 4}));
        Mockito.when(user.getAchievents()).thenReturn(createBadgeAchievementsSet(new int[] {1, 2, 3, 4}));

        List<BadgeRank> result = badgeService.getBadgesToCollect(user);
        
        Assert.assertEquals(createBadgeRankList(new int[]{}), result);
    }
    
    @Test
    public void getBadgesToCollect_more_collected() {    
    	Mockito.when(badgeRankRepository.findAll()).thenReturn(createBadgeRankList(new int[] {1, 2, 3, 4}));
        Mockito.when(user.getAchievents()).thenReturn(createBadgeAchievementsSet(new int[] {1, 2, 3, 4, 6 ,7}));

        List<BadgeRank> result = badgeService.getBadgesToCollect(user);
        
        Assert.assertEquals(createBadgeRankList(new int[]{}), result);
    }
    
    @Test
    public void getBadgesToCollect_empty_repository() {    
    	Mockito.when(badgeRankRepository.findAll()).thenReturn(createBadgeRankList(new int[] {}));
        Mockito.when(user.getAchievents()).thenReturn(createBadgeAchievementsSet(new int[] {1, 2, 3, 4}));

        List<BadgeRank> result = badgeService.getBadgesToCollect(user);
        
        Assert.assertEquals(createBadgeRankList(new int[]{}), result);
    }
    
    @Test
    public void getBadgesToCollect_some_badges_collected() {    
    	Mockito.when(badgeRankRepository.findAll()).thenReturn(createBadgeRankList(new int[] {1, 2, 3, 4}));
        Mockito.when(user.getAchievents()).thenReturn(createBadgeAchievementsSet(new int[] {1, 3, 6}));

        List<BadgeRank> result = badgeService.getBadgesToCollect(user);
        
        Assert.assertEquals(createBadgeRankList(new int[]{2, 4}), result);
    }
    
    private List<BadgeRank> createBadgeRankList(int[] ids) {
    	List<BadgeRank> list = new LinkedList<>();
    	
    	for(int i = 0; i < ids.length; i++) {
    		BadgeRank rank = new BadgeRank();
    		rank.setId(ids[i]);
    		list.add(rank);
    	}
    	
    	return list;
    }
    
    private Set<BadgeAchievement> createBadgeAchievementsSet(int[] ids) {
    	Set<BadgeAchievement> set = new HashSet<>();
    	
    	for(int i = 0; i < ids.length; i++) {
    		BadgeRank rank = new BadgeRank();
    		rank.setId(ids[i]);
    		BadgeAchievement achievement = new BadgeAchievement();
    		achievement.setBadgeRank(rank);
    		set.add(achievement);
    	}
    	
    	return set;
    }

}
