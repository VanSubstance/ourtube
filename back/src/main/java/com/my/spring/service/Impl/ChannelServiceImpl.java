package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.domain.statistics.MaxAvgMedian;
import com.my.spring.mapper.ChannelMapper;
import com.my.spring.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	public ChannelMapper mapper;

	@Override
	public List<String> getChannelIdsByGame(String game) {
		return mapper.getChannelIdsByGame(game);
	}

	@Override
	public void setChannelInfo(ChannelDto item) {
		mapper.setChannelInfo(item);
	}

	@Override
	public void setChain(ChainDto item) {
		mapper.setChain(item);
	}

	@Override
	public void setChannelStatistics(ChannelStatDto item) {
		mapper.setChannelStatistics(item);
	}

	@Override
	public int checkChannelInfo(String id) {
		return mapper.checkChannelInfo(id);
	}

	@Override
	public int checkChain(ChainDto item) {
		return mapper.checkChain(item);
	}
	
	@Override
	public String getDescriptionByChannelId(String id) {
		return mapper.getDescriptionByChannelId(id);
	}

	@Override
	public List<ChannelDto> getChannelInfoById(List<String> list) {
		return mapper.getChannelInfoById(list);
	}

	@Override
	public List<String> getChannelIdsInComplete() {
		return mapper.getChannelIdsInComplete();
	}

	@Override
	public MaxAvgMedian getChannelMaxAvgMedianByTopic(String topic) {
		return mapper.getChannelMaxAvgMedianByTopic(topic);
	}
}
