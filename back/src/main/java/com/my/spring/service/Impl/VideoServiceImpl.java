package com.my.spring.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.mapper.VideoMapper;
import com.my.spring.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper mapper;

	@Override
	public List<String> getVideoIdsByTopic(String topic) {
		return mapper.getVideoIdsByTopic(topic);
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
	public List<String> getVideoIdsForStatisticsByTopic(String ctgr) {
		return mapper.getVideoIdsForStatisticsByTopic(ctgr);
	}

	@Override
	public List<String> getVideoIdsForCommentByTopic(String ctgr) {
		return mapper.getVideoIdsForCommentByTopic(ctgr);
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
}
