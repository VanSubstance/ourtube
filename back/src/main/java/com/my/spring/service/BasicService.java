package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameStatistic;

public interface BasicService {
	public List<String> getGameFirst();
	public List<String> getGameTop();
	
	public int checkGameStat(String title);
	public void setGameStat(GameStatistic item);
	public List<TopicDto> getTopics();
	public List<TopicDto> getTopicGames();
	public List<String> getNounFilter();
	public List<TopicChain> getTopicChains();
	public List<TopicChain> getTopicChainsByTopic(String topic);
	public List<TopicStatDto> getGameListForToday();
	public int getIdCompleteById(String id);
	public void setIdComplete(IdComplete item);
}
