package com.my.spring.service;

import com.my.spring.domain.statistics.ChannelDto;

public interface StatisticsService {
	public ChannelDto getAverageChannelByCtgr(ChannelDto target);

}
