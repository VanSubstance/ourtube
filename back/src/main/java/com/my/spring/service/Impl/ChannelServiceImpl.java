package com.my.spring.service.Impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.mapper.ChannelMapper;
import com.my.spring.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	public ChannelMapper mapper;

	@Override
	public List<String> getChannelIdsByTopic(String topic) {
		return mapper.getChannelIdsByTopic(topic);
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
	public int checkChannelStatistics(ChannelStatDto item) {
		return mapper.checkChannelStatistics(item);
	}

	@Override
	public List<String> getChannelIdsForStatisticsByTopic(String topic) {
		return mapper.getChannelIdsForStatisticsByTopic(topic);
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
	public void filterChannelWord() {
		mapper.filterChannelWord();
	}
}
