package com.my.spring.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.statistics.GameDailyStatistic;
import com.my.spring.mapper.VideoMapper;
import com.my.spring.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper mapper;

	@Override
	public List<String> getVideoIdsByGame(String game) {
		return mapper.getVideoIdsByGame(game);
	}

	@Override
	public void setVideoInfo(VideoDto item) {
		mapper.setVideoInfo(item);
	}

	@Override
	public void setChain(ChainDto item) {
		mapper.setChain(item);
	}

	@Override
	public void setVideoStatistics(VideoStatDto item) {
		mapper.setVideoStatistics(item);
	}

	@Override
	public void setVideoTag(TagDto item) {
		mapper.setVideoTag(item);
	}

	@Override
	public int checkVideoInfo(String id) {
		return mapper.checkVideoInfo(id);
	}

	@Override
	public int checkChain(ChainDto item) {
		return mapper.checkChain(item);
	}

	@Override
	public int checkVideoStatistics(VideoStatDto item) {
		return mapper.checkVideoStatistics(item);
	}

	@Override
	public List<String> getVideoIdsForStatisticsByGame(String game) {
		return mapper.getVideoIdsForStatisticsByGame(game);
	}

	@Override
	public List<String> getVideoIdsForCommentByGame(String game) {
		return mapper.getVideoIdsForCommentByGame(game);
	}

	@Override
	public List<TagDto> getVideoTagByVideoId(String id) {
		return mapper.getVideoTagByVideoId(id);
	}

	@Override
	public String getDescriptionByVideoId(String id) {
		return mapper.getDescriptionByVideoId(id);
	}

	@Override
	public List<VideoDto> getVideoInfo() {
		return mapper.getVideoInfo();
	}

	@Override
	public List<VideoDto> getVideoInfoById(List<String> list) {
		return mapper.getVideoInfoById(list);
	}

	@Override
	public void filterVideoWord() {
		mapper.filterVideoWord();
	}

	@Override
	public void filterTagWord() {
		mapper.filterTagWord();
	}

	@Override
	public List<String> getVideoIdsInComplete() {
		return mapper.getVideoIdsInComplete();
	}

	@Override
	public VideoStatDto getAvgVideoStatisticsByGame(String game) {
		return mapper.getAvgVideoStatisticsByGame(game);
	}

	@Override
	public VideoStatDto getTotalVideoStatisticsByTopic(String topic) {
		return mapper.getTotalVideoStatisticsByTopic(topic);
	}

	@Override
	public List<Integer> getTotalViewsByGame(String game) {
		return mapper.getTotalViewsByGame(game);
	}

	@Override
	public Integer getNumNewVidTodayByTitle(String title) {
		return mapper.getNumNewVidTodayByTitle(title);
	}
	
	@Override
	public List<GameDailyStatistic> getAvgNewTodayByTitle(List<String> titles) {
		List<GameDailyStatistic> result = new ArrayList<GameDailyStatistic>();
		for (String title : titles) result.add(mapper.getAvgNewTodayByTitle(title));
		return result;
	}

	@Override
	public List<String> getNotParsedVideoIdsByTitle(String title) {
		return mapper.getNotParsedVideoIdsByTitle(title);
	}

	@Override
	public List<String> getTagNotParsedVideoIdsByTitle(String title) {
		return mapper.getTagNotParsedVideoIdsByTitle(title);
	}
}
