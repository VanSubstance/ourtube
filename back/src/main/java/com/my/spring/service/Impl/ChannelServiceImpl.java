package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChannelDto;
import com.my.spring.mapper.ChannelMapper;
import com.my.spring.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	public ChannelMapper mapper;

	@Override
	public List<String> getChannelsByCtgr(String ctgr) {
		return mapper.getChannelsByCtgr(ctgr);
	}

	@Override
	public List<String> getCtgrsByChannelId(String channelId) {
		return mapper.getCtgrsByChannelId(channelId);
	}

	@Override
	public Boolean putChannelInfo(ChannelDto data) {
		return mapper.putChannelInfo(data);
	}

	@Override
	public Boolean putChain(String channelId, String ctgr) {
		return mapper.putChain(channelId, ctgr);
	}

}
