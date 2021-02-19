package com.my.spring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.statistics.ChannelDto;
import com.my.spring.mapper.StatisticsMapper;
import com.my.spring.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsMapper mapper;
	
	@Override
	public ChannelDto getAverageChannelByCtgr(ChannelDto target) {
		return mapper.getAverageChannelByCtgr(target);
	}
}
