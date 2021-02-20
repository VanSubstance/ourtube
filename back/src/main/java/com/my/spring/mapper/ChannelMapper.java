package com.my.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;

public interface ChannelMapper {
	public List<String> getChannelsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getCtgrsByChannelId(@Param("channelId") String channelId);
	public List<String> checkExistence(@Param("channelId") String channelId);
	public Boolean putChannelInfo(@Param("data") ChannelDto data);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(@Param("chain") ChainDto chain);
}
