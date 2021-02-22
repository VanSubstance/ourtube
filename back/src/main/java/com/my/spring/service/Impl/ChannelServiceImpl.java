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
	public List<String> getChannelIdsByCtgr(String ctgr) {
		return mapper.getChannelIdsByCtgr(ctgr);
	}

	@Override
	public void setChannelInfo(ChannelDto item) {
		mapper.setChannelInfo(item);
	}

	@Override
	public void setChannelChain(ChainDto item) {
		mapper.setChannelChain(item);
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
	public int checkChannelChain(ChainDto item) {
		return mapper.checkChannelChain(item);
	}

	@Override
	public int checkChannelStatistics(ChannelStatDto item) {
		return mapper.checkChannelStatistics(item);
	}

	@Override
	public List<String> getChannelIdsForStatisticsByCtgr(String ctgr) {
		return mapper.getChannelIdsForStatisticsByCtgr(ctgr);
	}
}
