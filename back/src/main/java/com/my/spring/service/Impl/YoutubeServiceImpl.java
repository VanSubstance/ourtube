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
import com.my.spring.domain.CtgrDto;
import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 3;
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
			videos.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
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


	private static void printCtgr(Iterator<SearchResult> searchResults, List<ChannelDto> channelDtoList) {
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------\n");
		
		if (!searchResults.hasNext()) {
			System.out.println("No results for your query.");
		}
		
		while (searchResults.hasNext()) {
			SearchResult item = searchResults.next();
			System.out.println(item);
			
			if (item.getKind().equals("youtube#searchResult")) {
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				System.out.println("Channel Id: " + item.getSnippet().getChannelId());
				System.out.println("Thumbnail: " + thumbnail.getUrl());
				System.out.println("Title: " + item.getSnippet().getChannelTitle());
				System.out.println("Description: " + item.getSnippet().getDescription());
				System.out.println("Published date: " + item.getSnippet().getPublishedAt().toString());
				System.out.println("\n----------------------------------------------------------------\n");
				
				ChannelDto newItem = new ChannelDto();
				newItem.setId(item.getSnippet().getChannelId());
				newItem.setTitle(item.getSnippet().getChannelTitle());
				newItem.setDescription(item.getSnippet().getDescription());
				newItem.setThumbnail(thumbnail.getUrl());
				newItem.setPublishedDate(item.getSnippet().getPublishedAt());
				channelDtoList.add(newItem);
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
			
			YouTube.Search.List channels = youtube.search().list("id, snippet");
			// api 키 입력
			channels.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
			// 검색어 지정
			channels.setQ(ctgr);
			// 검색 결과 채널로 한정
			channels.setType("channel");
			// 검색 결과 제목으로 정렬
			channels.setOrder("title");
			// 검색 범위 한국으로 한정
			channels.setRegionCode("KR");
			// 조회 상한선
			channels.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<SearchResult> channelList = channels.execute().getItems();
			if (channelList != null) {
				printCtgr(channelList.iterator(), channelDtoList);
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
