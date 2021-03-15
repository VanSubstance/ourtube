package com.my.spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.statistics.GameDataForMain;
import com.my.spring.domain.statistics.MaxAvgMedian;
import com.my.spring.domain.words.NounDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;

@CrossOrigin("*")
@RestController
@RequestMapping("/deploy")
public class ProcessingController {
	@Autowired
	private BasicService serviceBasic;
	@Autowired
	private WordService serviceWord;
	@Autowired
	private VideoService serviceVideo;
	@Autowired
	private ChannelService serviceChannel;

	@RequestMapping(value = "/game/rank/today")
	public List<TopicStatDto> getTopicStatsforToday() {
		System.out.println("금일 게임 전체 랭킹 반환");
		return serviceBasic.getGameListForToday();
	}

	@RequestMapping(value = "/topic/**", method = RequestMethod.GET)
	public List<String> getTopicsByTopic(HttpServletRequest request) {
		System.out.println("관련 토픽 리스트 반환");
	    String requestURL = request.getRequestURL().toString();
	    String topic = requestURL.split("/topic/")[1];
	    try {
			topic = URLDecoder.decode(topic, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(topic);
		return serviceBasic.getTopicsByTopic(topic);
	}

	@RequestMapping(value = "/game/list/**", method = RequestMethod.GET)
	public List<Game> getGamesByTopic(HttpServletRequest request) {
		System.out.println("토픽 산하 키워드 10개 리스트 반환");
	    String requestURL = request.getRequestURL().toString();
	    String topic = requestURL.split("/game/list/")[1];
	    try {
			topic = URLDecoder.decode(topic, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(topic);
		return serviceBasic.getGamesByTopic(topic);
	}
	
	@RequestMapping(value = "/keyword/{keyword}", method = RequestMethod.GET)
	public HashMap<String, Object> getDataForWordcloudByKeyword(@PathVariable String keyword) {
		System.out.println("워드 클라우드 데이터 반환: " + keyword);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<NounDto> words = serviceWord.getWordsByGame(keyword);
		VideoStatDto avg = serviceVideo.getAvgVideoStatisticsByGame(keyword);
		List<Integer> views = serviceVideo.getTotalViewsByGame(keyword);
		result.put("words", words);
		result.put("likeCount", avg.getLikeCount());
		result.put("dislikeCount", avg.getDislikeCount());
		result.put("commentCount", avg.getCommentCount());
		result.put("viewCounts", views);
		return result;
	}
	
	@RequestMapping(value = "/chart/{topic}", method = RequestMethod.GET)
	public HashMap<String, HashMap<String, Object>> getDataForChartViewByTopic(@PathVariable String topic) {
		System.out.println("차트 데이터 반환: " + topic);
		HashMap<String, HashMap<String, Object>> result = new HashMap<String, HashMap<String, Object>>();
		List<String> topics = serviceBasic.getTopicsByTopic(topic);
		for (String topicRel : topics) {
			HashMap<String, Object> value = new HashMap<String, Object>();
			VideoStatDto resultVideo = serviceVideo.getTotalVideoStatisticsByTopic(topicRel);
			MaxAvgMedian maxAvgMedian = serviceChannel.getChannelMaxAvgMedianByTopic(topicRel);
			value.put("viewCount", resultVideo.getViewCount());
			value.put("dislikeCount", resultVideo.getDislikeCount());
			value.put("commentCount", resultVideo.getCommentCount());
			value.put("max", maxAvgMedian.getMax());
			value.put("avg", maxAvgMedian.getAvg());
			value.put("median", maxAvgMedian.getMedian());
			result.put(topicRel, value);
		}
		return result;
	}
	
	@RequestMapping(value = "/game/main/{title}", method = RequestMethod.GET)
	public HashMap<String, GameDataForMain> getDatasForGame(@PathVariable String title) {
		System.out.println("메인 화면 좌측 데이터 반환");
		HashMap<String, GameDataForMain> result = new HashMap<String, GameDataForMain>();
		for (GameDataForMain item : serviceBasic.getGameDataForMainByGame(title)) {
			result.put(item.getInfoDate().toString(), item);
		}
		return result;
	}
}