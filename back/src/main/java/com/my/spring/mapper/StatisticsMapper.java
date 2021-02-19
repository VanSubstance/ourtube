package com.my.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.statistics.ChannelDto;

public interface StatisticsMapper {
	public ChannelDto getAverageChannelByCtgr(@Param("target") ChannelDto target);
}
