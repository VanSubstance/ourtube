package com.my.spring.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.GameProfileChip;
import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.statistics.TopicStatistic;

public interface BasicMapper {
	// 당일 기준 동영상 게시일이 98일(14주)가 넘어가는 동영상 id들 삭제
	public void deleteOldDatas();
	
	public List<String> getGameQ();
	public String getTitleByQ(@Param("q") String q);
	public List<String> getAllTitle();
	
	public void setGame(@Param("item") Game item);
	public void setGameInGameSearch(@Param("title") String title);
	public void setGameTopic(@Param("item") GameTopic item);
	
	public void setOurScoreForGameToday(@Param("title") String title, @Param("score") double score, @Param("rank") int rank);
	
	public List<GameDataForMain> getGameDataForMainByGame(@Param("title") String title);
	
	public void setGameStat(@Param("item") GameStatistic item);
	public List<String> getTopics();
	public List<String> getNounFilter();
	public List<TopicStatDto> getGameListForToday();
	public int getIdCompleteById(@Param("id") String id);
	public void setIdComplete(@Param("item") IdComplete item);
	
	public List<String> getTopicsByTopic(@Param("topic") String topic);
	public List<Game> getGamesByTopic(@Param("topic") String topic);

	// 날짜, 장르 ==> 평균 아울스코어, 검색량, 조회수, 좋아요, 싫어요
	public TopicStatistic getTopicAvgStatuesByTopicAndDate(@Param("topic") String topic, @Param("date") Date date);

	// 날짜, 장르 ==> 평균 아울스코어, 검색량, 조회수, 좋아요, 싫어요
	public TopicStatistic getTopicStatisticTodayByTopic(String topic);
	
	// 게임 제목 ==> ProfileChip에 필요한 데이터
	public List<GameProfileChip> getProfileChipByTitle(@Param("title") String title);
}
