package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.statistics.GameDailyStatistic;

public interface VideoService {
	public List<String> getVideoIdsByGame(String game);
	public List<String> getNotParsedVideoIdsByTitle(String title);
	public List<String> getTagNotParsedVideoIdsByTitle(String title);
	public List<String> getVideoIdsForStatisticsByGame(String game);
	public List<String> getVideoIdsForCommentByGame(String game);
	public String getDescriptionByVideoId(String id);
	public List<TagDto> getVideoTagByVideoId(String id);
	public void setVideoInfo(VideoDto item);
	public void setChain(ChainDto item);
	public void setVideoStatistics(VideoStatDto item);
	public VideoStatDto getAvgVideoStatisticsByGame(String game);
	public VideoStatDto getTotalVideoStatisticsByTopic(String topic);
	public List<Integer> getTotalViewsByGame(String game);
	public void setVideoTag(TagDto item);
	public int checkVideoInfo(String id);
	public int checkChain(ChainDto item);
	public int checkVideoStatistics(VideoStatDto item);
	public List<VideoDto> getVideoInfo();
	public List<VideoDto> getVideoInfoById(List<String> list);
	public void filterVideoWord();
	public void filterTagWord();
	public List<String> getVideoIdsInComplete();
	public Integer getNumNewVidTodayByTitle(String title);
	public List<GameDailyStatistic> getAvgNewTodayByTitle(List<String> titles);
}
