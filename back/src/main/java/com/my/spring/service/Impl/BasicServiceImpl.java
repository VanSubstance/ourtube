package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper mapper;

	@Override
	public int checkTopicStat(String topic) {
		return mapper.checkTopicStat(topic);
	}

	@Override
	public void setTopicStat(TopicStatDto item) {
		mapper.setTopicStat(item);
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
	public List<TopicStatDto> getTopicListForToday() {
		return mapper.getTopicListForToday();
	}

	@Override
	public int getIdCompleteById(String id) {
		return mapper.getIdCompleteById(id);
	}

	@Override
	public void setIdComplete(IdComplete item) {
		mapper.setIdComplete(item);
	}
}
