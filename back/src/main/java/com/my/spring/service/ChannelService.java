package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChannelDto;

public interface ChannelService {
	public List<String> getChannelsByCtgr(String ctgr);
	public List<String> getCtgrsByChannelId(String channelId);
	public Boolean putChannelInfo(ChannelDto data);
	public Boolean putChain(String channelId, String ctgr);

}
