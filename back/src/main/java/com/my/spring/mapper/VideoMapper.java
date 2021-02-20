package com.my.spring.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;

public interface VideoMapper {
	public Boolean addVideo(Map<String, Object> videos);
	public int checkExistence(@Param("id") String id);
	public int checkExistenceTag(@Param("tag") TagDto tag);
	public Boolean addTag(Map<String, Object> tags);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(@Param("chain") ChainDto chain);
}
