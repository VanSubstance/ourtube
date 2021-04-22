package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.statistics.GameStatistic;

public interface StatisticService {
	public List<Integer> getTotalViewcountsByDate();
	public List<String> getGamesByDate();
	public List<Integer> getRecentViewsByGame(String title);
	
	public List<Double> getLikeRatioByDate();
	
	public List<Double> getCommentCountsByDate();
	public List<String> getGamesOrderByComment();
	public List<Integer> getRecentCommentCountsByGame(String title);

	public GameStatistic getGameStatisticsByGame(String title);
}
