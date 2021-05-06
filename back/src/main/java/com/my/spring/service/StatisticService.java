package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.statistics.TopicStatistic;

public interface StatisticService {
	public List<String> getGamesByDate();
	
	public GameStatistic getGameStatisticsByGame(String title);
	
	public void setTopicStatistic(TopicStatistic item);
	public void setTopicRankToday(String topic, int rank);
}
