package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.statistics.GameDailyStatistic;

public interface VideoMapper {
	public List<String> getVideoIdsByGame(@Param("game") String game);
	
	// 게임 별 비디오 설명의 parsing 작업이 이루어지지 않은 비디오 id 반환
	public List<String> getNotParsedVideoIdsByTitle(@Param("title") String title);
	
	// 게임 별 비디오 태그의 parsing 작업이 이루어지지 않은 비디오 id 반환
	public List<String> getTagNotParsedVideoIdsByTitle(@Param("title") String title);
	
	public List<String> getVideoIdsForStatisticsByGame(@Param("game") String game);
	public String getDescriptionByVideoId(@Param("id") String id);
	public List<String> getVideoIdsForCommentByGame(@Param("game") String game);
	public List<TagDto> getVideoTagByVideoId(@Param("id") String id);
	public void setVideoInfo(@Param("item") VideoDto item);
	public void setChain(@Param("item") ChainDto item);
	public void setVideoStatistics(@Param("item") VideoStatDto item);
	public VideoStatDto getAvgVideoStatisticsByGame(@Param("game") String game);
	public VideoStatDto getTotalVideoStatisticsByTopic(@Param("topic") String topic);
	public List<Integer> getTotalViewsByGame(@Param("game") String game);
	public void setVideoTag(@Param("item") TagDto item);
	public int checkVideoInfo(@Param("id") String id);
	public int checkChain(@Param("item") ChainDto item);
	public int checkVideoStatistics(@Param("item") VideoStatDto item);
	public List<VideoDto> getVideoInfo();
	public List<VideoDto> getVideoInfoById(@Param("list") List<String> list);
	public void filterVideoWord();
	public void filterTagWord();
	public List<String> getVideoIdsInComplete();
	public Integer getNumNewVidTodayByTitle(@Param("title") String title);
	public GameDailyStatistic getAvgNewTodayByTitle(@Param("title") String title);
}
