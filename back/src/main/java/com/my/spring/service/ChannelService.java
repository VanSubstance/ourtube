package com.my.spring.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;

public interface ChannelService {
	public List<String> getChannelsByCtgr(String ctgr);
	public List<String> getCtgrsByChannelId(String channelId);
	public List<String> getChannelIdsByDate(Date date);
	public List<String> checkExistence(ChannelDto channel);
	public Boolean putChannelInfo(ChannelDto data);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(ChainDto chain);

	public void cleanChain();
	public void cleanChannel();
	
	public List<String> getChannelIdsByCtgr(String ctgr);
}
