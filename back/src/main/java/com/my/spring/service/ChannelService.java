package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;

public interface ChannelService {
	public List<String> getChannelIdsByCtgr(String ctgr);
	public List<String> getChannelIdsForStatisticsByCtgr(String ctgr);
	public void setChannelInfo(ChannelDto item);
	public void setChannelChain(ChainDto item);
	public void setChannelStatistics(ChannelStatDto item);
	public int checkChannelInfo(String id);
	public int checkChannelChain(ChainDto item);
	public int checkChannelStatistics(ChannelStatDto item);
}
