package com.my.spring.service;

import java.util.Map;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;

public interface VideoService {
	public Boolean addVideo(Map<String, Object> videos);
	public int checkExistence(String id);
	public int checkExistenceTag(TagDto tag);
	public Boolean addTag(Map<String, Object> tags);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(ChainDto chain);
}
