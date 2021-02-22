package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;

public interface ChannelMapper {
	public List<String> getChannelIdsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getChannelIdsForStatisticsByCtgr(@Param("ctgr") String ctgr);
	public void setChannelInfo(@Param("item") ChannelDto item);
	public void setChannelChain(@Param("item") ChainDto item);
	public void setChannelStatistics(@Param("item") ChannelStatDto item);
	public int checkChannelInfo(@Param("id") String id);
	public int checkChannelChain(@Param("item") ChainDto item);
	public int checkChannelStatistics(@Param("item") ChannelStatDto item);
	
}
