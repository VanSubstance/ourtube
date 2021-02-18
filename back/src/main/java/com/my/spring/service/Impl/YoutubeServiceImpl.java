package com.my.spring.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
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
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
	private static YouTube youtube;
	
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
			// API Ű �Է�
			videos.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
			// ������ id �Է�
			videos.setId("5EXFilTUiko");
			// ��ȸ ���Ѽ�
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
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------\n");
		
		if (!searchResults.hasNext()) {
			System.out.println("No results for your query.");
		}
		
		while (searchResults.hasNext()) {
			SearchResult item = searchResults.next();
			
			if (item.getKind().equals("youtube#searchResult")) {
				System.out.println("Channel Id: " + item.getSnippet().getChannelId());
				channelIdList.add(item.getSnippet().getChannelId());
			}
			
		}
		
	}
	
	private static void printChannelListDetail(Iterator<Channel> results, List<ChannelDto> channelDtoList) {
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------\n");
		
		if (!results.hasNext()) {
			System.out.println("No results for your query.");
		}
		while (results.hasNext()) {
			Channel item = results.next();
			if (item.getKind().equals("youtube#channel")) {
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				System.out.println("Id: " + item.getId());
				System.out.println("Title: " + item.getSnippet().getTitle());
				System.out.println("Thumbnail: " + thumbnail.getUrl());
				System.out.println("Description: " + item.getSnippet().getDescription());
				System.out.println("Published date: " + item.getSnippet().getPublishedAt().toString());
				System.out.println("Statistics:");
				System.out.println("\tViews: " + item.getStatistics().getViewCount());
				System.out.println("\tSubs: " + item.getStatistics().getSubscriberCount());
				System.out.println("\tVideos: " + item.getStatistics().getVideoCount());
				System.out.println("Topics:");
				if (item.getTopicDetails() != null && item.getTopicDetails().getTopicCategories() != null) {
					for (String topic: item.getTopicDetails().getTopicCategories()) {
						System.out.println("\t" + topic);
					}
				}
				System.out.println("\n----------------------------------------------------------------\n");
			}
		}
		
	}
	
	@Override
	public List<ChannelDto> getChannelsByCtgr(String ctgr) {
		List<ChannelDto> channelDtoList = new ArrayList<ChannelDto>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();
			
			YouTube.Search.List channelBasics = youtube.search().list("id, snippet");
			// api Ű �Է�
			channelBasics.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
			// �˻��� ����
			channelBasics.setQ(ctgr);
			// �˻� ��� ä�η� ����
			channelBasics.setType("channel");
			// �˻� ��� �������� ����
			//channelBasics.setOrder("title");
			// �˻� ���� �ѱ����� ����
			channelBasics.setRegionCode("KR");
			// ��ȸ ���Ѽ�
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
			// api Ű �Է�
			channelDetails.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
			// ���� ����
			channelDetails.setHl("ko_kr");
			channelDetails.setId(idList);
			channelDetails.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<Channel> channelListDetail = channelDetails.execute().getItems();
			if (channelListDetail != null) {
				printChannelListDetail(channelListDetail.iterator(), channelDtoList);
			}
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return channelDtoList;
	}
	
}
