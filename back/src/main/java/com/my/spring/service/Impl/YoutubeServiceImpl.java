package com.my.spring.service.Impl;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.my.spring.domain.ChainChannelDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
	private static YouTube youtube;
	private static final String[] apiKeys = {"AIzaSyAE-kTRiIreaTxMsNG6Vg6W39YEv-a89x8", "AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74"}; 
	
	private static void prettyPrint (Iterator<Video> iteratorSearchResults, YoutubeDto youtubeDto) {
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------\n");
		
		if (!iteratorSearchResults.hasNext()) {
			System.out.println("No results for your query.");
		}
		
		while (iteratorSearchResults.hasNext()) {
			Video item = iteratorSearchResults.next();
			
			if (item.getKind().equals("youtube#video")) {
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				
				System.out.println("Video Id: " + item.getId());
				System.out.println("Category Id: " + item.getSnippet().getCategoryId());
				System.out.println("Title: " + item.getSnippet().getTitle());
				System.out.println("contentDetails Duration: " + item.getContentDetails().getDuration());
				System.out.println("Thumbnail: " + thumbnail.getUrl());
				System.out.println("\n----------------------------------------------------------------\n");
				
				youtubeDto.setThumbnailPath(thumbnail.getUrl());
				youtubeDto.setTitle(item.getSnippet().getTitle());
				youtubeDto.setVideoId(item.getId());
			}
		}
	}

	@Override
	public YoutubeDto get() {
		
		YoutubeDto youtubeDto = new YoutubeDto();
		
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() { 
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-video-duration-get").build();
			
			YouTube.Videos.List videos = youtube.videos().list("id,snippet,contentDetails");
			// API 키 입력
			videos.setKey(apiKeys[0]);
			//videos.setKey(apiKeys[1]);
			// 동영상 id 입력
			videos.setId("5EXFilTUiko");
			// 조회 상한선
			videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<Video> videoList = videos.execute().getItems();
			
			if (videoList != null) {
				prettyPrint(videoList.iterator(), youtubeDto);
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return youtubeDto;
	}


	private static void printChannelList(Iterator<SearchResult> searchResults, List<String> channelIdList) {
		
		if (!searchResults.hasNext()) {
			System.out.println("No results for your query.");
		}
		
		while (searchResults.hasNext()) {
			SearchResult item = searchResults.next();
			
			if (item.getKind().equals("youtube#searchResult")) {
				System.out.println("Channel Id: " + item.getId().getChannelId());
				channelIdList.add(item.getId().getChannelId());
			}
			
		}
		
	}
	
	private static void printChannelListDetail(Iterator<Channel> results, List<ChannelDto> channelDtoList, List<ChainChannelDto> ctgrList) {
		
		if (!results.hasNext()) {
			System.out.println("No results for your query.");
		}
		while (results.hasNext()) {
			Channel item = results.next();
			if (item.getKind().equals("youtube#channel")) {
				ChannelDto newChannel = new ChannelDto();
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				String publishedDate = item.getSnippet().getPublishedAt().toString().substring(0, 10);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String infoDate = dateFormat.format(Calendar.getInstance().getTime());
				
				System.out.println("\nId: " + item.getId());
				System.out.println("\nTitle: " + item.getSnippet().getTitle());
				System.out.println("\nThumbnail: " + thumbnail.getUrl());
				System.out.println("\nDescription: " + item.getSnippet().getDescription());
				System.out.println("\nPublished date: " + publishedDate);
				System.out.println("\nStatistics:");
				System.out.println("\tViews: " + item.getStatistics().getViewCount());
				System.out.println("\tSubs: " + item.getStatistics().getSubscriberCount());
				System.out.println("\tVideos: " + item.getStatistics().getVideoCount());
				System.out.println("\ninfo date: " + infoDate);
				
				
				newChannel.setId(item.getId());
				newChannel.setTitle(item.getSnippet().getTitle());
				newChannel.setDescription(item.getSnippet().getDescription());
				newChannel.setThumbnail(thumbnail.getUrl());
				newChannel.setPublishedDate(Date.valueOf(publishedDate));
				newChannel.setVideoCount(item.getStatistics().getVideoCount());
				newChannel.setViewCount(item.getStatistics().getViewCount());
				newChannel.setSubsCount(item.getStatistics().getSubscriberCount());
				newChannel.setInfoDate(Date.valueOf(infoDate));
				channelDtoList.add(newChannel);
				
				System.out.println("\nCtgrs:");
				if (item.getTopicDetails() != null && item.getTopicDetails().getTopicCategories() != null) {
					ChannelTopicDetails itemTopic = item.getTopicDetails();
					for (String ctgr : itemTopic.getTopicIds()) {
						String[] temp = ctgr.split("/");
						ctgr = temp[temp.length - 1];
						System.out.println("\t" + ctgr);
						// 새 체인 생성
						ChainChannelDto newChain = new ChainChannelDto();
						newChain.setChannelId(item.getId());
						newChain.setCtgr(ctgr);
						
						ctgrList.add(newChain);
					}
				}
			}
		}
		
	}
	
	@Override
	public ArrayList<Object> getChannelsBySearchVal(String ctgr) {
		List<ChannelDto> channelDtoList = new ArrayList<ChannelDto>();
		List<ChainChannelDto> ctgrList = new ArrayList<ChainChannelDto>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();
			
			YouTube.Search.List channelBasics = youtube.search().list("id");
			// api 키 입력
			channelBasics.setKey(apiKeys[0]);
			//channelBasics.setKey(apiKeys[1]);
			// 검색어 지정
			channelBasics.setQ(ctgr);
			// 검색 결과 채널로 한정
			channelBasics.setType("channel");
			// 검색 결과 제목으로 정렬
			channelBasics.setOrder("viewCount");
			// 검색 범위 한국으로 한정
			channelBasics.setRegionCode("KR");
			// 토픽 아이디 한정
			channelBasics.setTopicId("/m/0bzvm2");
			// 조회 상한선
			channelBasics.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<SearchResult> channelList = channelBasics.execute().getItems();
			List<String> channelIdList = new ArrayList();
			if (channelList != null) {
				printChannelList(channelList.iterator(), channelIdList);
			}
			
			String idList = "";
			for (String channelId : channelIdList) {
				idList += channelId + ",";
			}
			
			YouTube.Channels.List channelDetails = youtube.channels().list("snippet, statistics, topicDetails");
			// api 키 입력
			channelDetails.setKey(apiKeys[0]);
			//channelDetails.setKey(apiKeys[1]);
			// 국가 한정
			channelDetails.setHl("ko_kr");
			channelDetails.setId(idList);
			channelDetails.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<Channel> channelListDetail = channelDetails.execute().getItems();
			if (channelListDetail != null) {
				printChannelListDetail(channelListDetail.iterator(), channelDtoList, ctgrList);
				System.out.println("API works done.\n\n");
			}
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		ArrayList<Object> result = new ArrayList<Object>();
		result.add(channelDtoList);
		result.add(ctgrList);
		return result;
	}
	
}
