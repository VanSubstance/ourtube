package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.statistics.TopicStatistic;

public interface StatisticMapper {
	
	public List<String> getGamesByDate();
	
	public GameStatistic getGameStatisticsByGame(@Param("title") String title);
	
	// ���� �帣 ��� ��ġ ����
	public void setTopicStatistic(@Param("item") TopicStatistic item);
	public void setTopicRankToday(@Param("topic") String topic, @Param("rank") int rank);
}
