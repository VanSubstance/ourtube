package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.mapper.StatisticMapper;
import com.my.spring.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService{
	
	@Autowired
	public StatisticMapper mapper;

	@Override
	public List<String> getGamesByDate() {
		return mapper.getGamesByDate();
	}

	@Override
	public GameStatistic getGameStatisticsByGame(String title) {
		return mapper.getGameStatisticsByGame(title);
	}
	
}
