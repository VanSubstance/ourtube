package com.my.spring.service.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.statistics.DateStatistic;
import com.my.spring.domain.statistics.DateStatisticRelative;
import com.my.spring.mapper.VideoMapper;
import com.my.spring.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper mapper;

	@Override
	public List<String> getVideoIdsByGame(String game) {
		return mapper.getVideoIdsByGame(game);
	}

	@Override
	public void setVideoInfo(VideoDto item) {
		mapper.setVideoInfo(item);
	}

	@Override
	public void setChain(ChainDto item) {
		mapper.setChain(item);
	}

	@Override
	public void setVideoStatistics(VideoStatDto item) {
		mapper.setVideoStatistics(item);
	}

	@Override
	public void setVideoTag(TagDto item) {
		mapper.setVideoTag(item);
	}

	@Override
	public int checkVideoInfo(String id) {
		return mapper.checkVideoInfo(id);
	}

	@Override
	public int checkChain(ChainDto item) {
		return mapper.checkChain(item);
	}
	
	@Override
	public List<TagDto> getVideoTagByVideoId(String id) {
		return mapper.getVideoTagByVideoId(id);
	}

	@Override
	public String getDescriptionByVideoId(String id) {
		return mapper.getDescriptionByVideoId(id);
	}

	@Override
	public List<VideoDto> getVideoInfo() {
		return mapper.getVideoInfo();
	}

	@Override
	public List<VideoDto> getVideoInfoById(List<String> list) {
		return mapper.getVideoInfoById(list);
	}
	
	@Override
	public List<String> getVideoIdsInComplete() {
		return mapper.getVideoIdsInComplete();
	}

	@Override
	public VideoStatDto getAvgVideoStatisticsByGame(String game) {
		return mapper.getAvgVideoStatisticsByGame(game);
	}

	@Override
	public VideoStatDto getTotalVideoStatisticsByTopic(String topic) {
		return mapper.getTotalVideoStatisticsByTopic(topic);
	}

	@Override
	public List<Integer> getTotalViewsByGame(String game) {
		return mapper.getTotalViewsByGame(game);
	}
	
	// 최근 7일 간 각 게임 별 동영상 통계수치 추적 (10개 기본 수치)
	@Override
	public HashMap<String, HashMap<Date, DateStatistic>> getVideoDataByTitleAndDate(List<String> titles) {
		HashMap<String, HashMap<Date, DateStatistic>> result = new HashMap<String, HashMap<Date, DateStatistic>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> dates = new ArrayList<Date>(); 
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		for (int i = 1; i <= 2; i++) {
			dates.add(Date.valueOf(date));
			cal.add(Calendar.DATE, -1);
			date = dateFormat.format(cal.getTime());
		}
		HashMap<Date, DateStatistic> resultForTitle = new HashMap<Date, DateStatistic>();
		for (Date targetDate : dates) {
			resultForTitle.put(targetDate, mapper.getTotalVideoDataByDate(targetDate));
		}
		result.put("All Games", resultForTitle);
		
		for (String title : titles) {
			System.out.println(title);
			resultForTitle = new HashMap<Date, DateStatistic>();
			for (Date targetDate: dates) {
				resultForTitle.put(targetDate, mapper.getVideoDataByTitleAndDate(title, targetDate));
			}
			result.put(title, resultForTitle);
		}
		return result;
	}

	// 최근 7일 간 각 게임 별 동영상 통계수치 추적 (10개 기본 수치) 및 백분위수로 치환
	public HashMap<String, DateStatisticRelative> getVideoRelativeDataByTitleAndDate(List<String> titles) {
		HashMap<String, DateStatisticRelative> result = new HashMap<String, DateStatisticRelative>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
//		cal.add(Calendar.DATE, -1);

		List<Integer> numNewVids = new ArrayList<Integer>();
		List<Integer> avgNewViews = new ArrayList<Integer>();
		List<Integer> avgNewLikes = new ArrayList<Integer>();
		List<Integer> avgNewDislikes = new ArrayList<Integer>();
		List<Integer> avgNewComments = new ArrayList<Integer>();
		List<Integer> numAccuVids = new ArrayList<Integer>();
		List<Integer> avgAccuViews = new ArrayList<Integer>();
		List<Integer> avgAccuLikes = new ArrayList<Integer>();
		List<Integer> avgAccuDislikes = new ArrayList<Integer>();
		List<Integer> avgAccuComments = new ArrayList<Integer>();
		
		System.out.println("데이터 호출");
		for (String title : titles) {
			System.out.println(title);
			DateStatistic dateStatistic =  mapper.getVideoDataByTitleAndDate(title, Date.valueOf(date));
			numNewVids.add(dateStatistic.getNumNewVid());
			avgNewViews.add(dateStatistic.getAvgNewView());
			avgNewLikes.add(dateStatistic.getAvgNewLike());
			avgNewDislikes.add(dateStatistic.getAvgNewDislike());
			avgNewComments.add(dateStatistic.getAvgNewComment());
			numAccuVids.add(dateStatistic.getNumAccuVid());
			avgAccuViews.add(dateStatistic.getAvgAccuView());
			avgAccuLikes.add(dateStatistic.getAvgAccuLike());
			avgAccuDislikes.add(dateStatistic.getAvgAccuDislike());
			avgAccuComments.add(dateStatistic.getAvgAccuComment());
		}
		System.out.println("데이터 호출 종료");

		HashSet<Integer> container = new HashSet<Integer>(numNewVids);
		List<Integer> numNewVidsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewViews);
		List<Integer> avgNewViewsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewLikes);
		List<Integer> avgNewLikesSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewDislikes);
		List<Integer> avgNewDislikesSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewComments);
		List<Integer> avgNewCommentsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(numAccuVids);
		List<Integer> numAccuVidsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuViews);
		List<Integer> avgAccuViewsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuLikes);
		List<Integer> avgAccuLikesSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuDislikes);
		List<Integer> avgAccuDislikesSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuComments);
		List<Integer> avgAccuCommentsSorted = new ArrayList<Integer>(container);
		
		System.out.println("백분위 계산 실행");
		for (String title : titles) {
			System.out.println(title);
			DateStatisticRelative resultForTitle = new DateStatisticRelative();
			resultForTitle.setNumNewVid((double) numNewVidsSorted.indexOf(numNewVids.get(titles.indexOf(title))) / (double) numNewVids.size());
			resultForTitle.setAvgNewView((double) avgNewViewsSorted.indexOf(avgNewViews.get(titles.indexOf(title))) / (double) avgNewViews.size());
			resultForTitle.setAvgNewLike((double) avgNewLikesSorted.indexOf(avgNewLikes.get(titles.indexOf(title))) / (double) avgNewLikes.size());
			resultForTitle.setAvgNewDislike((double) avgNewDislikesSorted.indexOf(avgNewDislikes.get(titles.indexOf(title))) / (double) avgNewDislikes.size());
			resultForTitle.setAvgNewComment((double) avgNewCommentsSorted.indexOf(avgNewComments.get(titles.indexOf(title))) / (double) avgNewComments.size());
			resultForTitle.setNumAccuVid((double) numAccuVidsSorted.indexOf(numAccuVids.get(titles.indexOf(title))) / (double) numAccuVids.size());
			resultForTitle.setAvgAccuView((double) avgAccuViewsSorted.indexOf(avgAccuViews.get(titles.indexOf(title))) / (double) avgAccuViews.size());
			resultForTitle.setAvgAccuLike((double) avgAccuLikesSorted.indexOf(avgAccuLikes.get(titles.indexOf(title))) / (double) avgAccuLikes.size());
			resultForTitle.setAvgAccuDislike((double) avgAccuDislikesSorted.indexOf(avgAccuDislikes.get(titles.indexOf(title))) / (double) avgAccuDislikes.size());
			resultForTitle.setAvgAccuComment((double) avgAccuCommentsSorted.indexOf(avgAccuComments.get(titles.indexOf(title))) / (double) avgAccuComments.size());
			result.put(title, resultForTitle);
		}
		System.out.println("백분위 계산 종료");
		return result;
	}

	@Override
	public List<String> getNotParsedVideoIdsByTitle(String title) {
		return mapper.getNotParsedVideoIdsByTitle(title);
	}

	@Override
	public List<String> getTagNotParsedVideoIdsByTitle(String title) {
		return mapper.getTagNotParsedVideoIdsByTitle(title);
	}
	
}
