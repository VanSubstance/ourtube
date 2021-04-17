package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.basics.GameSearch;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.GameDataForTrendMain;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper mapper;

	@Override
	public List<String> getGameQ() {
		return mapper.getGameQ();
	}

	@Override
	public String getTitleByQ(String q) {
		return mapper.getTitleByQ(q);
	}
	
	@Override
	public List<String> getAllTitle() {
		return mapper.getAllTitle();
	}

	@Override
	public int checkGameStat(String title) {
		return mapper.checkGameStat(title);
	}

	@Override
	public void setGameStat(GameStatistic item) {
		mapper.setGameStat(item);
	}

	@Override
	public List<TopicDto> getTopics() {
		return mapper.getTopics();
	}

	@Override
	public List<TopicDto> getTopicGames() {
		return mapper.getTopicGames();
	}

	@Override
	public List<String> getNounFilter() {
		return mapper.getNounFilter();
	}

	@Override
	public List<TopicChain> getTopicChains() {
		return mapper.getTopicChains();
	}

	@Override
	public List<TopicChain> getTopicChainsByTopic(String topic) {
		return mapper.getTopicChainsByTopic(topic);
	}

	@Override
	public List<TopicStatDto> getGameListForToday() {
		return mapper.getGameListForToday();
	}

	@Override
	public int getIdCompleteById(String id) {
		return mapper.getIdCompleteById(id);
	}

	@Override
	public void setIdComplete(IdComplete item) {
		mapper.setIdComplete(item);
	}
	@Override
	public void setGame(Game item) {
		mapper.setGame(item);
	}
	
	@Override
	public void setGameInGameSearch(String title) {
		mapper.setGameInGameSearch(title);
	}

	@Override
	public void setGameTopic(GameTopic item) {
		mapper.setGameTopic(item);
	}

	@Override
	public void setTopic(String topic) {
		mapper.setTopic(topic);
	}

	@Override
	public List<String> getTopicsByTopic(String topic) {
		return mapper.getTopicsByTopic(topic);
	}

	@Override
	public List<Game> getGamesByTopic(String topic) {
		return mapper.getGamesByTopic(topic);
	}

	@Override
	public List<GameDataForMain> getGameDataForMainByGame(String title) {
		return mapper.getGameDataForMainByGame(title);
	}

	@Override
	public GameDataForMain getGameDateForTrendMainByGame(String title) {
		return mapper.getGameDateForTrendMainByGame(title);
	}

	@Override
	public List<String> getAllGamesByTopic(String topic) {
		return mapper.getAllGamesByTopic(topic);
	}

	@Override
	public void setWeightsGameTodayByGame(GameStatistic item) {
		mapper.setWeightsGameTodayByGame(item);
	}

}
