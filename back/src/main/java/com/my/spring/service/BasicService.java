package com.my.spring.service;

import java.sql.Date;
import java.util.List;

import com.my.spring.domain.GameProfileChip;
import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.statistics.TopicStatistic;

public interface BasicService {
	// 당일 기준 동영상 게시일이 98일(14주)가 넘어가는 동영상 id들 삭제
	public void deleteOldDatas();
	
	public List<String> getGameQ();
	public String getTitleByQ(String q);
	public List<String> getAllTitle();

	public void setGame(Game item);
	public void setGameInGameSearch(String title);
	public void setGameTopic(GameTopic item);

	public void setOurScoreForGameToday(String title, double score, int rank);
	
	public List<GameDataForMain> getGameDataForMainByGame(String title);
	
	public void setGameStat(GameStatistic item);
	public List<String> getTopics();
	public List<String> getNounFilter();
	public List<TopicStatDto> getGameListForToday();
	public int getIdCompleteById(String id);
	public void setIdComplete(IdComplete item);
	
	public List<String> getTopicsByTopic(String topic);
	public List<Game> getGamesByTopic(String topic);
	
	// 업데이트를 위한 계산 공정 후 데이터
	public TopicStatistic getTopicAvgStatuesByTopicAndDate(String topic);

	// 날짜, 장르 ==> 평균 아울스코어, 검색량, 조회수, 좋아요, 싫어요
	public TopicStatistic getTopicStatisticTodayByTopic(String topic);

	// 게임 제목 ==> ProfileChip에 필요한 데이터
	public GameProfileChip getProfileChipByTitle(String title);
}
