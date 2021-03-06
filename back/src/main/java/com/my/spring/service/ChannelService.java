package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.domain.statistics.MaxAvgMedian;

public interface ChannelService {
	public List<String> getChannelIdsByGame(String game);
	public String getDescriptionByChannelId(String id);
	public void setChannelInfo(ChannelDto item);
	public void setChain(ChainDto item);
	public void setChannelStatistics(ChannelStatDto item);
	public int checkChannelInfo(String id);
	public int checkChain(ChainDto item);
	public List<ChannelDto> getChannelInfoById(List<String> list);
	public List<String> getChannelIdsInComplete();
	public MaxAvgMedian getChannelMaxAvgMedianByTopic(String topic);
}
