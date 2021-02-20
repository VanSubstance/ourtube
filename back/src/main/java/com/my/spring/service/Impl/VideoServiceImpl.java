package com.my.spring.service.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.mapper.VideoMapper;
import com.my.spring.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper mapper;

	@Override
	public Boolean addVideo(VideoDto video) {
		return mapper.addVideo(video);
	}

	@Override
	public int checkExistence(String id) {
		return mapper.checkExistence(id);
	}

	@Override
	public int checkExistenceTag(TagDto tag) {
		return mapper.checkExistenceTag(tag);
	}

	@Override
	public Boolean addTag(Map<String, Object> tags) {
		return mapper.addTag(tags);
	}

	@Override
	public Boolean addChain(Map<String, Object> chains) {
		return mapper.addChain(chains);
	}

	@Override
	public Boolean addChainRaw(ChainDto chain) {
		return mapper.addChainRaw(chain);
	}

	@Override
	public void cleanChain() {
		mapper.cleanChain();
	}

	@Override
	public void cleanVideo() {
		mapper.cleanVideo();
	}

	@Override
	public int checkExistenceParent(VideoDto video) {
		return mapper.checkExistenceParent(video);
	}

}
