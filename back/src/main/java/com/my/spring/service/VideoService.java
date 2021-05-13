package com.my.spring.service;

import java.util.HashMap;
import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.statistics.DateStatistic;
import com.my.spring.domain.statistics.DateStatisticRelative;
import com.my.spring.domain.statistics.GameStatisticResultBar;

public interface VideoService {
	public List<String> getVideoIdsByGame(String game);
	public List<String> getNotParsedVideoIdsByTitle(String title);
	public List<String> getTagNotParsedVideoIdsByTitle(String title);
	public String getDescriptionByVideoId(String id);
	public List<TagDto> getVideoTagByVideoId(String id);
	public void setVideoInfo(VideoDto item);
	public void setChain(ChainDto item);
	public void setVideoStatistics(VideoStatDto item);
	public VideoStatDto getAvgVideoStatisticsByGame(String game);
	public VideoStatDto getTotalVideoStatisticsByTopic(String topic);
	public List<Integer> getTotalViewsByGame(String game);
	public void setVideoTag(TagDto item);
	public int checkVideoInfo(String id);
	public int checkChain(ChainDto item);
	public List<VideoDto> getVideoInfo();
	public List<VideoDto> getVideoInfoById(List<String> list);
	public List<String> getVideoIdsInComplete();
	
	public HashMap<String, HashMap<String, DateStatistic>> getVideoDataByTitleAndDate(List<String> titles);
	public HashMap<String, DateStatisticRelative> getVideoRelativeDataByTitleAndDate(List<String> titles);
	public List<DateStatistic> getVideoDataForRegressionByTitle(String title);
	
	public HashMap<String, Double> calcOurScoreFromVideoData(List<String> titles);

	// �ֱ� 7�� �� �� ���� �� ������ ����ġ ���� (10�� �⺻ ��ġ) -> ���� ������ ��Ʈ�並 ���� ����
	public HashMap<String, Object> getChartDataForMainPageChart(String title);
	
	// ���� ���� ������ ����ġ ���� (10�� �⺻ ��ġ)
	public DateStatistic getVideoDataTodayByTitle (String title);

	// ���� ���� -> ���� ���� ����Ʈ ���
	public List<String> getTitlesRelavantByTitle(String title);
	
	// �帣 �� ��� ���ƿ�, �Ⱦ��, ä�� ��, ������ ��
	public GameStatisticResultBar getDataForResultBarByTopic(String topic);
	
	// �帣 �� ��� ���ƿ�, �Ⱦ��, ä�� ��, ������ ��
	public GameStatisticResultBar getDataForResultBarByGame(String title);
}
