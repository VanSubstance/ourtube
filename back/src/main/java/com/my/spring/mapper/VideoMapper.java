package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;

public interface VideoMapper {
	public List<String> getVideoIdsByCtgr(@Param("ctgr") String ctgr);
	public List<String> getVideoIdsForStatisticsByTopic(@Param("topic") String topic);
	public List<String> getVideoIdsForCommentByTopic(@Param("topic") String topic);
	public void setVideoInfo(@Param("item") VideoDto item);
	public void setChain(@Param("item") ChainDto item);
	public void setVideoStatistics(@Param("item") VideoStatDto item);
	public void setVideoTag(@Param("item") TagDto item);
	public int checkVideoInfo(@Param("id") String id);
	public int checkChain(@Param("item") ChainDto item);
	public int checkVideoStatistics(@Param("item") VideoStatDto item);
}
