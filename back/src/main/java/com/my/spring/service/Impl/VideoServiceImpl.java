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
	public List<String> getVideoIdsByChannelId(String ctgr) {
		return mapper.getVideoIdsByChannelId(ctgr);
	}

	@Override
	public void setVideoInfo(VideoDto item) {
		mapper.setVideoInfo(item);
	}

	@Override
	public void setVideoChain(ChainDto item) {
		mapper.setVideoChain(item);
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
	public int checkVideoChain(ChainDto item) {
		return mapper.checkVideoChain(item);
	}

	@Override
	public int checkVideoStatistics(VideoStatDto item) {
		return mapper.checkVideoStatistics(item);
	}

	@Override
	public List<String> getVideoIdsForStatisticsByCtgr(String ctgr) {
		return mapper.getVideoIdsForStatisticsByCtgr(ctgr);
	}

	@Override
	public List<String> getVideoIdsForCommentByCtgr(String ctgr) {
		return mapper.getVideoIdsForCommentByCtgr(ctgr);
	}
}
