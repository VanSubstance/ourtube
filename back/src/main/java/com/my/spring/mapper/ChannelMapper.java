package com.my.spring.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;

public interface ChannelMapper {
	public List<String> getChannelsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getCtgrsByChannelId(@Param("channelId") String channelId);
	public List<String> getChannelIdsByDate(@Param("date") Date date);
	public List<String> checkExistence(@Param("channel") ChannelDto channel);
	public Boolean putChannelInfo(@Param("data") ChannelDto data);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(@Param("chain") ChainDto chain);
	
	public void cleanChain();
	public void cleanChannel();
	
	public List<String> getChannelIdsByCtgr(@Param("ctgr") String ctgr);
}
