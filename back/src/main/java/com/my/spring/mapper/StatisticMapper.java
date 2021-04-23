package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.statistics.GameStatistic;

public interface StatisticMapper {
	
	public List<String> getGamesByDate();
	
	public GameStatistic getGameStatisticsByGame(@Param("title") String title);
}
