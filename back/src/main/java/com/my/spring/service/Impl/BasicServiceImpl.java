package com.my.spring.service.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.statistics.TopicStatistic;
import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper mapper;
	
	@Override
	public void deleteOldDatas() {
		mapper.deleteOldDatas();
	}

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
	public void setGameStat(GameStatistic item) {
		mapper.setGameStat(item);
	}

	@Override
	public List<TopicDto> getTopics() {
		return mapper.getTopics();
	}

	@Override
	public List<String> getNounFilter() {
		return mapper.getNounFilter();
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
	public void setOurScoreForGameToday(String title, double score, int rank) {
		mapper.setOurScoreForGameToday(title, score, rank);
	}

	@Override
	public TopicStatistic getTopicAvgStatuesByTopicAndDate(String topic) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		return mapper.getTopicAvgStatuesByTopicAndDate(topic, Date.valueOf(date));
	}

}
