package com.my.spring.service.Impl;

import java.io.IOException;
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
import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
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
	
	private static void prettyPrintChannel (Iterator<Channel> iteratorSearchResults, YoutubeDto youtubeDto) {
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------\n");
		
		if (!iteratorSearchResults.hasNext()) {
			System.out.println("No results for your query.");
		}
		
		while (iteratorSearchResults.hasNext()) {
			Channel item = iteratorSearchResults.next();
			
			if (item.getKind().equals("youtube#channel")) {
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				
				System.out.println("Channel Id: " + item.getId());
				System.out.println("Title: " + item.getSnippet().getTitle());
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

	@Override
	public YoutubeDto getThumbnail() {
		YoutubeDto youtubeDto = new YoutubeDto();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() { 
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-video-duration-get").build();
			
			YouTube.Channels.List videos = youtube.channels().list("id,snippet,contentDetails");
			// API 키 입력
			videos.setKey("AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74");
			// 동영상 id 입력
			videos.setId("UCRpdlPk671uOMiBtf5HtB3Q");
			// 조회 상한선
			videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<Channel> videoList = videos.execute().getItems();
			
			if (videoList != null) {
				prettyPrintChannel(videoList.iterator(), youtubeDto);
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
	
}
