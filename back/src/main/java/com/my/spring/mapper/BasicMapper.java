package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameStatistic;

public interface BasicMapper {
	public List<String> getGameNew();
	public List<String> getGameFirst();
	public List<String> getGameTop();
	
	public void setGame(@Param("item") Game item);
	public void setGameTopic(@Param("item") GameTopic item);
	public void setTopic(@Param("topic") String topic);
	
	public int checkGameStat(@Param("title") String title);
	public void setGameStat(@Param("item") GameStatistic item);
	public List<TopicDto> getTopics();
	public List<TopicDto> getTopicGames();
	public List<String> getNounFilter();
	public List<TopicChain> getTopicChains();
	public List<TopicChain> getTopicChainsByTopic(@Param("topic") String topic);
	public List<TopicStatDto> getGameListForToday();
	public int getIdCompleteById(@Param("id") String id);
	public void setIdComplete(@Param("item") IdComplete item);
	
	public List<String> getTopicsByTopic(@Param("topic") String topic);
}
