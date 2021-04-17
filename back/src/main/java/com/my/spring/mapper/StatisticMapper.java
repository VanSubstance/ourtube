package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.statistics.GameStatistic;

public interface StatisticMapper {
	
	public List<Integer> getTotalViewcountsByDate();
	public List<String> getGamesByDate();
	public List<Integer> getRecentViewsByGame(@Param("title") String title);
	
	public List<Double> getLikeRatioByDate();
	
	public List<Double> getCommentCountsByDate();
	public List<String> getGamesOrderByComment();
	public List<Integer> getRecentCommentCountsByGame(@Param("title") String title);
	
	public GameStatistic getGameStatisticsByGame(@Param("title") String title);
}
