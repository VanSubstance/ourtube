package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChannelDto;

public interface ChannelMapper {
	public List<String> getChannelsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getCtgrsByChannelId(@Param("channelId") String channelId);
	public List<String> checkExistence(@Param("channelId") String channelId);
	public List<String> checkExistenceChain(@Param("channelId") String channelId, @Param("ctgr") String ctgr);
	public Boolean putChannelInfo(@Param("data") ChannelDto data);
	public Boolean putChain(@Param("channelId") String channelId, @Param("ctgr") String ctgr);
}
