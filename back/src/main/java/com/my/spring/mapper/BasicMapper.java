package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.chains.TopicChain;

public interface BasicMapper {
	public int checkTopicStat(@Param("topic") String topic);
	public void setTopicStat(@Param("item") TopicStatDto item);
	public List<TopicDto> getTopics();
	public List<TopicDto> getTopicGames();
	public List<TopicDto> getTopicsForPatch();
	public List<String> getNounFilter();
	public List<TopicChain> getTopicChains();
}
