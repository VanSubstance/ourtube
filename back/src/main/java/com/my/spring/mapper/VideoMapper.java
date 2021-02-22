package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;

public interface VideoMapper {
	public List<String> getVideoIdsByChannelId(@Param("ctgr") String ctgr);
	public List<String> getVideoIdsForStatisticsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getVideoIdsForCommentByCtgr(@Param("ctgr") String ctgr);
	public void setVideoInfo(@Param("item") VideoDto item);
	public void setVideoChain(@Param("item") ChainDto item);
	public void setVideoStatistics(@Param("item") VideoStatDto item);
	public void setVideoTag(@Param("item") TagDto item);
	public int checkVideoInfo(@Param("id") String id);
	public int checkVideoChain(@Param("item") ChainDto item);
	public int checkVideoStatistics(@Param("item") VideoStatDto item);
}
