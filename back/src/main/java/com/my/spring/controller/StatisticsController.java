package com.my.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.words.NounDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;

@CrossOrigin("*")
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
		System.out.println("���� �� ���� �±� �� ��� ����Ʈ ����");
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
		System.out.println("���� �� ���� �±� �� ��� ����Ʈ ���� ����.");
		System.out.println("----------------------------------------------\n");
		return result;
	}
	
	@RequestMapping(value = "/words/set")
	public void getWordSet() {
		System.out.println("\n----------------------------------------------");
		System.out.println("��ǥ��� ����Ʈ ����");
		List<String> synonyms = new ArrayList<String>();
		List<String> uniques = new ArrayList<String>();
		HashMap<String, List<NounDto>> dataset = getVideoWords();
		List<NounDto> nouns = new ArrayList<NounDto>();
		// Ű���� �ĺ�, ���� ����
		HashMap<String, List<String>> resultProcess1 = new HashMap<String, List<String>>();
		HashMap<String, NounDto> resultsVideo = new HashMap<String, NounDto>();
		for (Entry<String, List<NounDto>> nounsVideo : dataset.entrySet()) {
			nouns.addAll(nounsVideo.getValue());
		}
		System.out.println("��ü ���: " + nouns.size() + " ��.");
		
		/**
		 * 1�� �õ�: ��縦 �����ϴ� ���� �з�.
		 * ����: ������ ���丮 | ������, ���丮, ������ ���丮
		 */

		for (NounDto noun : nouns) {
			Boolean checkAdded = false;
			for (String keyword: resultProcess1.keySet()) {
				if (keyword.contains(noun.getWord())) {
					synonyms = new ArrayList<String>();
					synonyms = resultProcess1.get(keyword);
					synonyms.add(noun.getWord());
					resultProcess1.put(keyword, synonyms);
					checkAdded = true;
				}
			}
			if (!checkAdded) {
				synonyms = new ArrayList<String>();
				synonyms.add(noun.getWord());
				resultProcess1.put(noun.getWord(), synonyms);
			}
		}
		System.out.println("���� 1 ����. ��ǥ ���: " + resultProcess1.size() + " ��.");
		for (Entry<String, List<String>> word : resultProcess1.entrySet()) {
			System.out.println(word.getKey());
			for (String item : word.getValue()) {
				System.out.println("\t" + item);
			}
		}
	}

	@RequestMapping("/words/video")
	public HashMap<String, List<NounDto>> getVideoWords() {
		System.out.println("\n----------------------------------------------");
		System.out.println("���� �� ���� ���� �� ��� ����Ʈ ����");
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
		System.out.println("���� �� ���� ���� �� ��� ����Ʈ ���� ����.");
		System.out.println("----------------------------------------------\n");
		return result;
	}
	
	@RequestMapping("/words/channel")
	public HashMap<String, List<NounDto>> getChannelWords() {
		System.out.println("\n----------------------------------------------");
		System.out.println("���� �� ä�� ���� �� ��� ����Ʈ ����");
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
		System.out.println("���� �� ä�� ���� �� ��� ����Ʈ ���� ����.");
		System.out.println("----------------------------------------------\n");
		return result;
	}
	
}
