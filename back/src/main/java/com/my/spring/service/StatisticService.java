package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.statistics.GameStatistic;

public interface StatisticService {
	public List<String> getGamesByDate();
	
	public GameStatistic getGameStatisticsByGame(String title);
}
