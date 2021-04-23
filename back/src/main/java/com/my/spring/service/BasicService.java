package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.GameStatistic;

public interface BasicService {
	// 당일 기준 동영상 게시일이 98일(14주)가 넘어가는 동영상 id들 삭제
	public void deleteOldDatas();
	
	public List<String> getGameQ();
	public String getTitleByQ(String q);
	public List<String> getAllTitle();

	public void setGame(Game item);
	public void setGameInGameSearch(String title);
	public void setGameTopic(GameTopic item);
	
	public List<GameDataForMain> getGameDataForMainByGame(String title);
	public GameDataForMain getGameDateForTrendMainByGame(String title);
	
	public void setGameStat(GameStatistic item);
	public List<TopicDto> getTopics();
	public List<String> getNounFilter();
	public List<TopicStatDto> getGameListForToday();
	public int getIdCompleteById(String id);
	public void setIdComplete(IdComplete item);
	
	public List<String> getTopicsByTopic(String topic);
	public List<Game> getGamesByTopic(String topic);
	public List<String> getAllGamesByTopic(String topic);
}
