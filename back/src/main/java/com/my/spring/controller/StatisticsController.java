package com.my.spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.statistics.GameDailyStatistic;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.domain.words.NounDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.StatisticService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;

@CrossOrigin("*")
@RestController
@RequestMapping("/data")
public class StatisticsController {

	@Autowired
	private WordService serviceWord;
	@Autowired
	private BasicService serviceBasic;
	@Autowired
	private StatisticService serviceStatistic;
	@Autowired
	private VideoService serviceVideo;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String requestedTime = dateFormat.format(Calendar.getInstance().getTime());
	
	@RequestMapping(value = "/test")
	public List<GameDailyStatistic> test() {
		System.out.println("게임 별 비디오 평균치 반환 : " + requestedTime);
		List<String> titles = serviceStatistic.getGamesByDate();
		return serviceVideo.getAvgNewTodayByTitle(titles);
	}
	
	@RequestMapping(value = "/weight/**", method = RequestMethod.GET)
	public GameStatistic getGameStatisticsByGame(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		String title = requestURL.split("/weight/")[1];
		try {
			title = URLDecoder.decode(title, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(title + " -- 변수 반환 -- " + requestedTime);
		
		GameStatistic gameStat = serviceStatistic.getGameStatisticsByGame(title);
		System.out.println(gameStat.getRank());
		return gameStat;
	}

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

	@RequestMapping(value = "/words/set")
	public void getWordSet() {
		System.out.println("\n----------------------------------------------");
		System.out.println("대표명사 리스트 추출");
		List<String> synonyms = new ArrayList<String>();
		List<String> uniques = new ArrayList<String>();
		HashMap<String, List<NounDto>> dataset = getVideoWords();
		List<NounDto> nouns = new ArrayList<NounDto>();
		// 키워드 후보, 축약어 모음
		HashMap<String, List<String>> resultProcess1 = new HashMap<String, List<String>>();
		HashMap<String, NounDto> resultsVideo = new HashMap<String, NounDto>();
		for (Entry<String, List<NounDto>> nounsVideo : dataset.entrySet()) {
			nouns.addAll(nounsVideo.getValue());
		}
		System.out.println("전체 명사: " + nouns.size() + " 개.");

		/**
		 * 1차 시도: 명사를 포함하는 명사로 분류. 예시: 메이플 스토리 | 메이플, 스토리, 메이플 스토리
		 */

		for (NounDto noun : nouns) {
			Boolean checkAdded = false;
			for (String keyword : resultProcess1.keySet()) {
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
		System.out.println("로직 1 종료. 대표 명사: " + resultProcess1.size() + " 개.");
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
