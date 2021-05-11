package com.my.spring.service.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.deploy.DataForLine;
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
	
	// 아울스코어를 위한 그래프 수식: A
	private List<Double> formulaAByDouble(List<Double> Xs, double weight) {
		List<Double> result = new ArrayList<Double>();
		for (Double x : Xs) {
			if (x == null) x = 0.0;
			result.add((1.0 - (1.0 / ((100.0 * x) + 1.0))) * weight);
		}
		return result;
	}
	
	// 아울스코어를 위한 그래프 수식: A
	private List<Double> formulaAByInteger(List<Integer> Xs, double weight) {
		List<Double> result = new ArrayList<Double>();
		for (Integer x : Xs) {
			if (x == null) x = 0;
			result.add((1.0 - (1.0 / ((double)x + 1.0))) * weight);
		}
		return result;
	}
	
	// 아울스코어 그래프 수식: B
	private List<Double> formulaBByDouble(List<Double> Xs, double avg, double weight) {
		List<Double> result = new ArrayList<Double>();
		for (Double x : Xs) {
			if (x == null) x = 0.0;
			if (x == avg) result.add(weight / 2.0);
			else if (x > avg) result.add(((1.0 / Math.pow(2.0, 0.5) * Math.pow(x - avg, 0.5)) + 0.5) * weight);
			else result.add(((-1.0 / Math.pow(2.0, 0.5) * Math.pow(avg - x, 0.5)) + 0.5) * weight);
		}
		return result;
	}
	// 아울스코어 그래프 수식: B
	private List<Double> formulaBByInteger(List<Integer> Xs, double avg, double weight) {
		List<Double> result = new ArrayList<Double>();
		for (Integer x : Xs) {
			if (x == null) x = 0;
			if (x >= avg) result.add((1.0 - (1.0/(2.0*((double)x + 1.0 - avg)))) * weight);
			else result.add((-1.0/(2.0*(x - 1.0 - avg))) * weight);
		}
		return result;
	}
	
	// 아울스코어 그래프 수식: C
	private List<Double> formulaCByDouble(List<Double> Xs, double weight) {
		List<Double> result = new ArrayList<Double>();
		for (Double x : Xs) {
			if (x == null) x = 0.0;
			if (1000.0 > x) result.add(1.0 * weight);
			else if (x >= 0) result.add(((Math.pow(x / 1000.0, 2.0) / 2.0) + (1.0/2.0)) * weight);
			else if (x < -1000) result.add(-1.0 * weight);
			else result.add(((-1.0 * (Math.pow(x / -1000.0, 2.0) / 2.0)) + (1.0/2.0)) * weight);
		}
		return result;
	}
	
	// 최근 7일 간 각 게임 별 동영상 통계수치 추적 (10개 기본 수치)
	@Override
	public HashMap<String, HashMap<String, DateStatistic>> getVideoDataByTitleAndDate(List<String> titles) {
		HashMap<String, HashMap<String, DateStatistic>> result = new HashMap<String, HashMap<String, DateStatistic>>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> dates = new ArrayList<Date>(); 
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		for (int i = 0; i < 4; i++) {
			dates.add(Date.valueOf(date));
			cal.add(Calendar.DATE, -1);
			date = dateFormat.format(cal.getTime());
		}
		HashMap<String, DateStatistic> resultForTitle = new HashMap<String, DateStatistic>();
		for (Date targetDate : dates) {
			resultForTitle.put(targetDate.toString(), mapper.getTotalVideoDataByDate(targetDate));
		}
		result.put("All Games", resultForTitle);
		
		for (String title : titles) {
			System.out.println(title);
			resultForTitle = new HashMap<String, DateStatistic>();
			for (Date targetDate: dates) {
				resultForTitle.put(targetDate.toString(), mapper.getVideoDataByTitleAndDate(title, targetDate));
			}
			result.put(title, resultForTitle);
		}
		return result;
	}

	// 당일 게임 동영상 통계수치 추적 (10개 기본 수치)
	@Override
	public DateStatistic getVideoDataTodayByTitle (String title) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		return mapper.getVideoDataByTitleAndDate(title, Date.valueOf(date));
	}
	
	// 최근 7일 간 각 게임 별 동영상 통계수치 추적 (10개 기본 수치) -> 메인 페이지 차트뷰를 위한 형식
	@Override
	public HashMap<String, Object> getChartDataForMainPageChart(String title) {
		List<String> variables = Arrays.asList(new String[] {
				"rank",
				"numNewVid", "avgNewView", "avgNewLike", "avgNewDislike", "avgNewComment",
				"numAccuVid", "avgAccuView", "avgAccuLike", "avgAccuDislike", "avgAccuComment"
				});
		// 1. 기본 결과 객체 생성
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<DataForLine> dataForAvgNewView = new ArrayList<DataForLine>();
		List<DataForLine> dataForNumNewVid = new ArrayList<DataForLine>();
		List<DataForLine> dataForRank = new ArrayList<DataForLine>();
		Integer dataForAvgAccuComment = 0;
		List<Integer> dataForBar = new ArrayList<Integer>();
		// 2. 각 날짜 별 데이터 호출
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> dates = new ArrayList<Date>(); 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -6);
		String date = dateFormat.format(cal.getTime());
		for (int i = 0; i < 7; i++) {
			dates.add(Date.valueOf(date));
			cal.add(Calendar.DATE, +1);
			date = dateFormat.format(cal.getTime());
		}
		for (Date targetDate : dates) {
			// 3. 해당 날짜 + 게임 데이터 결과 객체에 추가
			DateStatistic dataTemp = mapper.getVideoDataByTitleAndDate(title, targetDate);
			if (dataTemp != null) {
				// AvgNumView
				DataForLine dataForLine = new DataForLine();
				dataForLine.setX(targetDate.toString().substring(5).replace("-", "/"));
				if (dataTemp.getAvgNewView() != null) {
					dataForLine.setY(dataTemp.getAvgNewView());
					dataForBar.add(dataTemp.getAvgNewView());
				} else {
					dataForLine.setY(0);
					dataForBar.add(0);
				}
				dataForAvgNewView.add(dataForLine);
				// NumNewVid
				dataForLine = new DataForLine();
				dataForLine.setX(targetDate.toString().substring(5).replace("-", "/"));
				if (dataTemp.getNumNewVid() != null) {
					dataForLine.setY(dataTemp.getNumNewVid());
				} else {
					dataForLine.setY(0);
				}
				dataForNumNewVid.add(dataForLine);
				// rank
				dataForLine = new DataForLine();
				dataForLine.setX(targetDate.toString().substring(5).replace("-", "/"));
				if (dataTemp.getRank() != null) {
					dataForLine.setY(dataTemp.getRank());
				} else {
					dataForLine.setY(0);
				}
				dataForRank.add(dataForLine);
				dataForAvgAccuComment = dataTemp.getAvgAccuComment();
			}
		}
		result.put("avgNewView", dataForAvgNewView);
		result.put("numNewVid", dataForNumNewVid);
		result.put("rank", dataForRank);
		result.put("avgAccuComment", dataForAvgAccuComment);
		result.put("avgNewViewForBar", dataForBar);
		return result;
	}

	// 최근 며칠간의 데이터 반환: 선형계수 측정에 사용
	@Override
	public List<DateStatistic> getVideoDataForRegressionByTitle(String title) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> dates = new ArrayList<Date>(); 
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		for (int i = 0; i < 7; i++) {
			dates.add(Date.valueOf(date));
			cal.add(Calendar.DATE, -1);
			date = dateFormat.format(cal.getTime());
		}
		List<DateStatistic> result = new ArrayList<DateStatistic>();
		for (Date targetDate: dates) {
			result.add(mapper.getVideoDataByTitleAndDate(title, targetDate));
		}
		return result;
		
	}
	
	// 게임 별 실 측정값 데이터 아워스코어로 변환
	@Override
	public HashMap<String, Double> calcOurScoreFromVideoData(List<String> titles) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		List<Integer> numNewVids = new ArrayList<Integer>();
		List<Integer> avgNewViews = new ArrayList<Integer>();
		List<Integer> avgNewComments = new ArrayList<Integer>();
		List<Integer> numAccuVids = new ArrayList<Integer>();
		List<Integer> avgAccuViews = new ArrayList<Integer>();
		List<Integer> avgAccuComments = new ArrayList<Integer>();

		List<Double> result = new ArrayList<Double>();
		
		System.out.print("실 측정값 계산 시작 ------------- ");
		for (String title : titles) {
			DateStatistic dateStatistic =  mapper.getVideoDataByTitleAndDate(title, Date.valueOf(date));
			numNewVids.add(dateStatistic.getNumNewVid());
			avgNewViews.add(dateStatistic.getAvgNewView());
			avgNewComments.add(dateStatistic.getAvgNewComment());
			numAccuVids.add(dateStatistic.getNumAccuVid());
			avgAccuViews.add(dateStatistic.getAvgAccuView());
			avgAccuComments.add(dateStatistic.getAvgAccuComment());
		}
		DateStatistic avg = mapper.getTotalVideoDataByDate(Date.valueOf(date));
		
		List<Double> scoreNumNewVid = formulaBByInteger(numNewVids, avg.getNumNewVid(), 0.03);
		List<Double> scoreAvgNewView = formulaBByInteger(avgNewViews, avg.getAvgNewView(), 0.0225);
		List<Double> scoreAvgNewComment = formulaBByInteger(avgNewComments, avg.getAvgNewComment(), 0.0225);
		List<Double> scoreNumAccuVid = formulaAByInteger(numAccuVids, 0.03);
		List<Double> scoreAvgAccuView = formulaAByInteger(avgAccuViews, 0.0225);
		List<Double> scoreAvgAccuComment = formulaAByInteger(avgAccuComments, 0.0225);
		for (int i = 0; i < titles.size(); i++) {
			result.add(
					scoreNumNewVid.get(i) + scoreAvgNewView.get(i) + scoreAvgNewComment.get(i) +
					scoreNumAccuVid.get(i) + scoreAvgAccuView.get(i) + scoreAvgAccuComment.get(i)
					);
		}
		System.out.println("완료");
		
		System.out.print("실 측정값 백분위수 계산 시작 ------------- ");
		// 백분위수 데이터 호출
		HashMap<String, DateStatisticRelative> dataPercentile = new HashMap<String, DateStatisticRelative>();
		HashSet<Integer> container = new HashSet<Integer>(numNewVids);
		List<Integer> numNewVidsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewViews);
		List<Integer> avgNewViewsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgNewComments);
		List<Integer> avgNewCommentsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(numAccuVids);
		List<Integer> numAccuVidsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuViews);
		List<Integer> avgAccuViewsSorted = new ArrayList<Integer>(container);
		container = new HashSet<Integer>(avgAccuComments);
		List<Integer> avgAccuCommentsSorted = new ArrayList<Integer>(container);
		
		for (String title : titles) {
			DateStatisticRelative resultForTitle = new DateStatisticRelative();
			resultForTitle.setNumNewVid((double) numNewVidsSorted.indexOf(numNewVids.get(titles.indexOf(title))) / (double) numNewVids.size());
			resultForTitle.setAvgNewView((double) avgNewViewsSorted.indexOf(avgNewViews.get(titles.indexOf(title))) / (double) avgNewViews.size());
			resultForTitle.setAvgNewComment((double) avgNewCommentsSorted.indexOf(avgNewComments.get(titles.indexOf(title))) / (double) avgNewComments.size());
			resultForTitle.setNumAccuVid((double) numAccuVidsSorted.indexOf(numAccuVids.get(titles.indexOf(title))) / (double) numAccuVids.size());
			resultForTitle.setAvgAccuView((double) avgAccuViewsSorted.indexOf(avgAccuViews.get(titles.indexOf(title))) / (double) avgAccuViews.size());
			resultForTitle.setAvgAccuComment((double) avgAccuCommentsSorted.indexOf(avgAccuComments.get(titles.indexOf(title))) / (double) avgAccuComments.size());
			dataPercentile.put(title, resultForTitle);
		}
		List<Double> numNewVidsD = new ArrayList<Double>();
		List<Double> avgNewViewsD = new ArrayList<Double>();
		List<Double> avgNewCommentsD = new ArrayList<Double>();
		List<Double> numAccuVidsD = new ArrayList<Double>();
		List<Double> avgAccuViewsD = new ArrayList<Double>();
		List<Double> avgAccuCommentsD = new ArrayList<Double>();
		for (String title : titles) {
			DateStatisticRelative dataTemp = dataPercentile.get(title);
			numNewVidsD.add(dataTemp.getNumNewVid());
			avgNewViewsD.add(dataTemp.getAvgNewView());
			avgNewCommentsD.add(dataTemp.getAvgNewComment());
			numAccuVidsD.add(dataTemp.getNumAccuVid());
			avgAccuViewsD.add(dataTemp.getAvgAccuView());
			avgAccuCommentsD.add(dataTemp.getAvgAccuComment());
		}
		scoreNumNewVid = formulaBByDouble(numNewVidsD, 0.5, 0.04);
		scoreAvgNewView = formulaBByDouble(avgNewViewsD, 0.5, 0.03);
		scoreAvgNewComment = formulaBByDouble(avgNewCommentsD, 0.5, 0.03);
		scoreNumAccuVid = formulaAByDouble(numNewVidsD, 0.04);
		scoreAvgAccuView = formulaAByDouble(avgAccuViewsD, 0.03);
		scoreAvgAccuComment = formulaAByDouble(avgAccuCommentsD, 0.03);
		for (int i = 0; i < titles.size(); i++) {
			result.set(i, 
					result.get(i) + 
					scoreNumNewVid.get(i) + scoreAvgNewView.get(i) + scoreAvgNewComment.get(i) +
					scoreNumAccuVid.get(i) + scoreAvgAccuView.get(i) + scoreAvgAccuComment.get(i)
					);
		}
		System.out.println("완료");

		System.out.print("선형계수 계산 시작 ------------- ");
		HashMap<String, DateStatisticRelative> dataRegression = new HashMap<String, DateStatisticRelative>();
		SimpleRegression regression;
		double[][] dataForRegression;
		
		for (String title : titles) {
			DateStatisticRelative resultForTitle = new DateStatisticRelative();
			// datas: {오늘, 1일 전, 2일 전, ...}
			List<DateStatistic> datas = getVideoDataForRegressionByTitle(title);
			numNewVids = new ArrayList<Integer>();
			avgNewViews = new ArrayList<Integer>();
			avgNewComments = new ArrayList<Integer>();
			for (int i = datas.size() - 1; i >= 0; i--) {
				DateStatistic data = datas.get(i);
				int count = 1;
				while (data == null) {
					data = datas.get(i - count);
					count ++;
				}
				numNewVids.add(data.getNumNewVid());
				avgNewViews.add(data.getAvgNewView());
				avgNewComments.add(data.getAvgNewComment());
			}
			dataForRegression = new double[numNewVids.size()][2];
			for (int i = 0; i < numNewVids.size(); i++) {
				dataForRegression[i][0] = i;
				dataForRegression[i][1] = numNewVids.get(i);
			}
			regression = new SimpleRegression();
			regression.addData(dataForRegression);
			resultForTitle.setNumNewVid(regression.getSlope());
			dataForRegression = new double[avgNewViews.size()][2];
			for (int i = 0; i < avgNewViews.size(); i++) {
				dataForRegression[i][0] = i;
				Integer val = avgNewViews.get(i);
				if (val != null) {
					val = avgNewViews.get(i);
				}
				int count = 1;
				while (val == null) {
					if (i != 0) {
						if (i + count == avgNewViews.size()) val = 0;
						else {
							val = avgNewViews.get(i + count);
							if (val == null && (i - count) >= 0) {
								val = avgNewViews.get(i - count);
							}
							count ++;
						}
					} else {
						val = 0;
					}
				}
				dataForRegression[i][1] = val;
			}
			regression = new SimpleRegression();
			regression.addData(dataForRegression);
			resultForTitle.setAvgNewView(regression.getSlope());
			dataForRegression = new double[avgNewComments.size()][2];
			for (int i = 0; i < avgNewComments.size(); i++) {
				dataForRegression[i][0] = i;
				Integer val = avgNewComments.get(i);
				if (val != null) {
					val = avgNewComments.get(i);
				}
				int count = 1;
				while (val == null) {
					if (i != 0) {
						if (i + count == avgNewComments.size()) val = 0;
						else {
							val = avgNewComments.get(i + count);
							if (val == null && (i - count) >= 0) {
								val = avgNewComments.get(i - count);
							}
							count ++;
						}
					} else {
						val = 0;
					}
				}
				dataForRegression[i][1] = val;
			}
			regression = new SimpleRegression();
			regression.addData(dataForRegression);
			resultForTitle.setAvgNewComment(regression.getSlope());
			dataRegression.put(title, resultForTitle);
		}
		numNewVidsD = new ArrayList<Double>();
		avgNewViewsD = new ArrayList<Double>();
		avgNewCommentsD = new ArrayList<Double>();
		for (String title : titles) {
			DateStatisticRelative dataTemp = dataRegression.get(title);
			numNewVidsD.add(dataTemp.getNumNewVid());
			avgNewViewsD.add(dataTemp.getAvgNewView());
			avgNewCommentsD.add(dataTemp.getAvgNewComment());
		}
		scoreNumNewVid = formulaCByDouble(numNewVidsD, 0.1);
		scoreAvgNewView = formulaCByDouble(avgNewViewsD, 0.075);
		scoreAvgNewComment = formulaCByDouble(avgNewCommentsD, 0.075);
		for (int i = 0; i < titles.size(); i++) {
			result.set(i, 
					result.get(i) + 
					scoreNumNewVid.get(i) + scoreAvgNewView.get(i) + scoreAvgNewComment.get(i)
					);
		}
		System.out.println("완료");

		System.out.print("선형계수 백분위수 계산 시작 ------------- ");
		HashMap<String, DateStatisticRelative> dataRegressionPercentile = new HashMap<String, DateStatisticRelative>();
		DateStatisticRelative data;

		numNewVidsD = new ArrayList<Double>();
		avgNewViewsD = new ArrayList<Double>();
		avgNewCommentsD = new ArrayList<Double>();
		
		for (String title : titles) {
			data = dataRegression.get(title);
			numNewVidsD.add(data.getNumNewVid());
			avgNewViewsD.add(data.getAvgNewView());
			avgNewCommentsD.add(data.getAvgNewComment());
		}

		HashSet<Double> containerD = new HashSet<Double>(numNewVidsD);
		List<Double> numNewVidsSortedD = new ArrayList<Double>(containerD);
		containerD = new HashSet<Double>(avgNewViewsD);
		List<Double> avgNewViewsSortedD = new ArrayList<Double>(containerD);
		containerD = new HashSet<Double>(avgNewCommentsD);
		List<Double> avgNewCommentsSortedD = new ArrayList<Double>(containerD);
		
		for (String title : titles) {
			DateStatisticRelative resultForTitle = new DateStatisticRelative();
			resultForTitle.setNumNewVid((double) numNewVidsSortedD.indexOf(numNewVidsD.get(titles.indexOf(title))) / (double) numNewVidsD.size());
			resultForTitle.setAvgNewView((double) avgNewViewsSortedD.indexOf(avgNewViewsD.get(titles.indexOf(title))) / (double) avgNewViewsD.size());
			resultForTitle.setAvgNewComment((double) avgNewCommentsSortedD.indexOf(avgNewCommentsD.get(titles.indexOf(title))) / (double) avgNewCommentsD.size());
			dataRegressionPercentile.put(title, resultForTitle);
		}
		numNewVidsD = new ArrayList<Double>();
		avgNewViewsD = new ArrayList<Double>();
		avgNewCommentsD = new ArrayList<Double>();
		for (String title : titles) {
			DateStatisticRelative dataTemp = dataRegressionPercentile.get(title);
			numNewVidsD.add(dataTemp.getNumNewVid());
			avgNewViewsD.add(dataTemp.getAvgNewView());
			avgNewCommentsD.add(dataTemp.getAvgNewComment());
		}
		scoreNumNewVid = formulaBByDouble(numNewVidsD, 0.5, 0.12);
		scoreAvgNewView = formulaBByDouble(avgNewViewsD, 0.5, 0.09);
		scoreAvgNewComment = formulaBByDouble(avgNewCommentsD, 0.5, 0.09);
		for (int i = 0; i < titles.size(); i++) {
			result.set(i, 
					result.get(i) + 
					scoreNumNewVid.get(i) + scoreAvgNewView.get(i) + scoreAvgNewComment.get(i)
					);
		}
		System.out.println("완료");
		HashMap<String, Double> resultTotal = new HashMap<String, Double>();
		for (int i = 0; i < titles.size(); i++ ) {
			resultTotal.put(titles.get(i), result.get(i));
		}
		return resultTotal;
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
		for (String title : titles) {
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
		
		for (String title : titles) {
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
	
	@Override
	public List<String> getTitlesRelavantByTitle(String title) {
		return mapper.getTitlesRelavantByTitle(title);
	}
	
}
