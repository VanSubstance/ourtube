package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;

public interface ChannelMapper {
	public List<String> getChannelIdsByGame(@Param("game") String game);
	public List<String> getChannelIdsForStatisticsByGame(@Param("game") String game);
	public String getDescriptionByChannelId(@Param("id") String id);
	public void setChannelInfo(@Param("item") ChannelDto item);
	public void setChain(@Param("item") ChainDto item);
	public void setChannelStatistics(@Param("item") ChannelStatDto item);
	public int checkChannelInfo(@Param("id") String id);
	public int checkChain(@Param("item") ChainDto item);
	public int checkChannelStatistics(@Param("item") ChannelStatDto item);
	public List<ChannelDto> getChannelInfoById(@Param("list") List<String> list);
	public void filterChannelWord();
	public List<String> getChannelIdsInComplete();
}
