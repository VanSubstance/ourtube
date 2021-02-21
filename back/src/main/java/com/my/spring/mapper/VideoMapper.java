package com.my.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;

public interface VideoMapper {
	public Boolean addVideo(@Param("video") VideoDto video);
	public int checkExistence(@Param("id") String id);
	public int checkExistenceTag(@Param("tag") TagDto tag);
	public int checkExistenceParent(@Param("video") VideoDto video);
	public Boolean addTag(Map<String, Object> tags);
	public Boolean addTagSingle(@Param("tag") TagDto tag);
	public Boolean addChain(Map<String, Object> chains);
	public Boolean addChainRaw(@Param("chain") ChainDto chain);

	public void cleanChain();
	public void cleanVideo();
	
	public List<String> getVideoIdsByChannelId(@Param("id") String id);
}
