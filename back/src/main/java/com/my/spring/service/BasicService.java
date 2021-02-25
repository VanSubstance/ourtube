package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;

public interface BasicService {
	public int checkTopicStat(String topic);
	public void setTopicStat(TopicStatDto item);
	public List<TopicDto> getTopics();
	public List<TopicDto> getTopicsForPatch();
}
