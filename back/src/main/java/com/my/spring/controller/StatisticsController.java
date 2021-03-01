package com.my.spring.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.WordDto;
import com.my.spring.domain.words.ChannelWordsDto;
import com.my.spring.domain.words.NounDto;
import com.my.spring.domain.words.TagWordsDto;
import com.my.spring.domain.words.VideoWordsDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;

@RestController
@RequestMapping("/data")
public class StatisticsController {

	@Autowired
	private WordService serviceWord;
	@Autowired
	private VideoService serviceVideo;
	@Autowired
	private ChannelService serviceChannel;
	@Autowired
	private BasicService serviceBasic;
	
	@RequestMapping("/words/tag")
	public HashMap<String, List<NounDto>> getTagWords() {
		System.out.println("\n----------------------------------------------");
		System.out.println("토픽 별 비디오 태그 별 명사 리스트 추출");
		HashMap<String, List<NounDto>> result = new HashMap<String, List<NounDto>>();
		List<NounDto> value = new ArrayList<NounDto>();
		List<TopicDto> topicList = serviceBasic.getTopics();
		for (TopicDto topic : topicList) {
			List<NounDto> nounList = serviceWord.getTagWordsByTopic(topic.getTopic());
			for (NounDto noun : nounList) {
				value = new ArrayList<NounDto>();
				if (result.containsKey(noun.getId())) {
					value = result.get(noun.getId());
				}
				value.add(noun);
				result.put(noun.getId(), value);
			}
		}
		System.out.println("토픽 별 비디오 태그 별 명사 리스트 추출 종료.");
		System.out.println("----------------------------------------------\n");
		return result;
	}

	@RequestMapping("/words/video")
	public HashMap<String, List<NounDto>> getVideoWords() {
		System.out.println("\n----------------------------------------------");
		System.out.println("토픽 별 비디오 설명 별 명사 리스트 추출");
		HashMap<String, List<NounDto>> result = new HashMap<String, List<NounDto>>();
		List<NounDto> value = new ArrayList<NounDto>();
		List<TopicDto> topicList = serviceBasic.getTopics();
		for (TopicDto topic : topicList) {
			List<NounDto> nounList = serviceWord.getVideoWordsByTopic(topic.getTopic());
			for (NounDto noun : nounList) {
				value = new ArrayList<NounDto>();
				if (result.containsKey(noun.getId())) {
					value = result.get(noun.getId());
				}
				value.add(noun);
				result.put(noun.getId(), value);
			}
		}
		System.out.println("토픽 별 비디오 설명 별 명사 리스트 추출 종료.");
		System.out.println("----------------------------------------------\n");
		return result;
	}
	
	@RequestMapping("/words/channel")
	public HashMap<String, List<NounDto>> getChannelWords() {
		System.out.println("\n----------------------------------------------");
		System.out.println("토픽 별 채널 설명 별 명사 리스트 추출");
		HashMap<String, List<NounDto>> result = new HashMap<String, List<NounDto>>();
		List<NounDto> value = new ArrayList<NounDto>();
		List<TopicDto> topicList = serviceBasic.getTopics();
		for (TopicDto topic : topicList) {
			List<NounDto> nounList = serviceWord.getChannelWordsByTopic(topic.getTopic());
			for (NounDto noun : nounList) {
				value = new ArrayList<NounDto>();
				if (result.containsKey(noun.getId())) {
					value = result.get(noun.getId());
				}
				value.add(noun);
				result.put(noun.getId(), value);
			}
		}
		System.out.println("토픽 별 채널 설명 별 명사 리스트 추출 종료.");
		System.out.println("----------------------------------------------\n");
		return result;
	}
}
