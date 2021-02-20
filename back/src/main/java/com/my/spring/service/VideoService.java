package com.my.spring.service;

import java.util.Map;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;

public interface VideoService {
	public Boolean addVideo(VideoDto video);
	public int checkExistence(String id);
	public int checkExistenceTag(TagDto tag);
	public int checkExistenceParent(VideoDto video);
	public Boolean addTag(Map<String, Object> tags);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(ChainDto chain);

	public void cleanChain();
	public void cleanVideo();
}
