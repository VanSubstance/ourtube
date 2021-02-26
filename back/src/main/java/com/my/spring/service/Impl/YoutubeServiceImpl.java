package com.my.spring.service.Impl;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.domain.CommentDto;
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static YouTube youtube;
	private static final String[] apiKeys = { 
			"AIzaSyDmY56rONS-hcNqXGFxYc9vgVeGSk0YeSc",
			"AIzaSyAE-kTRiIreaTxMsNG6Vg6W39YEv-a89x8", 
			"AIzaSyAFdfs807Tl-7PM8tb4ZDOqfC7vKSCSaRg",
			"AIzaSyAQtHVKj5g7XtkJJh_Ipd5WlifxCOCwzsc", 
			"AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74"};
	private static final int api = 3;

	public YoutubeServiceImpl() {
		getConnection();
	}
	
	private void getConnection() {
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	// search.list: 100
	@Override
	public ArrayList<Object> callVideoIdsByTopic(TopicDto topicDto) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<String> videoIdList = new ArrayList();
		List<String> channelIdList = new ArrayList();
		TopicStatDto topicStat = new TopicStatDto();
		try {
			YouTube.Search.List base = youtube.search().list("snippet");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 검색 결과 채널로 한정
			base.setType("video");
			base.setLocation("37.55718,126.99006");
			base.setLocationRadius("325km");
			// 검색 결과 제목으로 정렬
			base.setOrder("relevance");
			// 검색 범위 한국으로 한정
			base.setRegionCode("KR");
			// 토픽 아이디 한정
			base.setTopicId(topicDto.getId());
			base.setVideoCategoryId("20");
			// 조회 상한선
			base.setMaxResults((long) 40);
			
			List<SearchResult> searchResults = base.execute().getItems();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String infoDate = dateFormat.format(Calendar.getInstance().getTime());
			topicStat.setInfoDate(Date.valueOf(infoDate));
			topicStat.setTopic(topicDto.getTopic());
			topicStat.setResultCount(base.execute().getPageInfo().getTotalResults());
			if (base != null) {
				Iterator<SearchResult> data = searchResults.iterator();
				if (!data.hasNext()) {
					System.out.println("No results for your query.");
				}
				while (data.hasNext()) {
					SearchResult item = data.next();
					if (item.getKind().equals("youtube#searchResult")) {
						videoIdList.add(item.getId().getVideoId());
						String channelId = item.getSnippet().getChannelId();
						if (!channelIdList.contains(channelId)) {
							channelIdList.add(channelId);
						}
					}
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(videoIdList);
		result.add(channelIdList);
		result.add(topicStat);
		return result;
	}
	
	// channels.list 1
	@Override
	public ArrayList<Object> callChannelInfosByChannelId(List<String> channelIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<ChannelDto> channelList = new ArrayList<ChannelDto>();
		try {
			YouTube.Channels.List base = youtube.channels().list("snippet");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 국가 한정
			base.setHl("ko_kr");
			base.setMaxResults((long) 40);
			String idList = "";
			for (int i = 1; i <= channelIdList.size(); i++) {
				idList += channelIdList.get(i - 1) + ",";
				if (i % 40 == 0 || i == channelIdList.size()) {
					base.setId(idList);
					List<Channel> searchResult = base.execute().getItems();
					if (searchResult != null) {
						Iterator<Channel> data = searchResult.iterator();
						if (!data.hasNext()) {
							System.out.println("No results for your query.");
						}
						while (data.hasNext()) {
							Channel item = data.next();
							if (item.getKind().equals("youtube#channel")) {
								ChannelDto newChannel = new ChannelDto();
								Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
								String publishedDate = item.getSnippet().getPublishedAt().toString().substring(0, 10);
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								String infoDate = dateFormat.format(Calendar.getInstance().getTime());

								newChannel.setId(item.getId());
								newChannel.setTitle(item.getSnippet().getTitle());
								newChannel.setDescription(item.getSnippet().getDescription());
								newChannel.setThumbnail(thumbnail.getUrl());
								newChannel.setPublishedDate(Date.valueOf(publishedDate));
								channelList.add(newChannel);
							}
						}
					}
					idList = "";
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(channelList);
		return result;
	}

	// channels.list 1
	@Override
	public ArrayList<Object> callChannelStatsByChannelId(List<String> channelIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<ChannelStatDto> channelStatList = new ArrayList<ChannelStatDto>();
		try {
			YouTube.Channels.List base = youtube.channels().list("statistics");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 국가 한정
			base.setHl("ko_kr");
			base.setMaxResults((long) 40);
			String idList = "";
			for (int i = 1; i <= channelIdList.size(); i++) {
				idList += channelIdList.get(i - 1) + ", ";
				if (i % 40 == 0 || i == channelIdList.size()) {
					base.setId(idList);
					List<Channel> searchResult = base.execute().getItems();
					if (searchResult != null) {
						Iterator<Channel> data = searchResult.iterator();
						if (!data.hasNext()) {
							System.out.println("No results for your query.");
						}
						while (data.hasNext()) {
							Channel item = data.next();
							if (item.getKind().equals("youtube#channel")) {
								ChannelStatDto newChannelStat = new ChannelStatDto();
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								String infoDate = dateFormat.format(Calendar.getInstance().getTime());					
								newChannelStat.setId(item.getId());
								newChannelStat.setVideoCount(item.getStatistics().getVideoCount());
								newChannelStat.setViewCount(item.getStatistics().getViewCount());
								newChannelStat.setSubsCount(item.getStatistics().getSubscriberCount());
								newChannelStat.setInfoDate(Date.valueOf(infoDate));
								channelStatList.add(newChannelStat);
							}
						}
					}
					idList = "";
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(channelStatList);
		return result;
	}

	// videos.list 1
	@Override
	public ArrayList<Object> callVideoInfosByVideoId(List<String> videoIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<VideoDto> videoList = new ArrayList<VideoDto>();
		List<TagDto> tagList = new ArrayList<TagDto>();
		try {
			YouTube.Videos.List base = youtube.videos().list("snippet");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 국가 한정
			base.setHl("ko_kr");
			base.setMaxResults((long) 40);
			String idList = "";
			for (int i = 1; i <= videoIdList.size(); i++) {
				idList += videoIdList.get(i - 1) + ",";
				if (i % 40 == 0 || i == videoIdList.size()) {
					base.setId(idList);
					List<Video> searchResult = base.execute().getItems();
					if (searchResult != null) {
						Iterator<Video> data = searchResult.iterator();
						if (!data.hasNext()) {
							System.out.println("\t\t비디오 정보 검색결과 없음.");
						}
						while (data.hasNext()) {
							Video item = data.next();
							if (item.getKind().equals("youtube#video")) {
								VideoDto newVideo = new VideoDto();
								Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
								String publishedDate = item.getSnippet().getPublishedAt().toString().substring(0, 10);
								newVideo.setId(item.getId());
								newVideo.setTitle(item.getSnippet().getTitle());
								newVideo.setDescription(item.getSnippet().getDescription());
								newVideo.setThumbnail(thumbnail.getUrl());
								newVideo.setPublishedDate(Date.valueOf(publishedDate));
								newVideo.setChannelId(item.getSnippet().getChannelId());
								videoList.add(newVideo);
								
								if (item.getSnippet().getTags() != null) {
									List<String> itemTags = item.getSnippet().getTags();
									System.out.print("\t\t태그 개수: " + itemTags.size() + " 개\n");
									for (String tag : itemTags) {
										TagDto newTag = new TagDto();
										newTag.setId(item.getId());
										newTag.setTag(tag);
										tagList.add(newTag);
									}
								}
							}
						}
						idList = "";
					}
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(videoList);
		result.add(tagList);
		return result;
	}

	// videos.list 1
	@Override
	public ArrayList<Object> callVideoStatsByVideoId(List<String> videoIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<VideoStatDto> videoStatList = new ArrayList<VideoStatDto>();
		try {
			YouTube.Videos.List videoDetails = youtube.videos().list("statistics");
			// api 키 입력
			videoDetails.setKey(apiKeys[api]);
			// 국가 한정
			videoDetails.setHl("ko_kr");
			videoDetails.setMaxResults((long) 40);
			String idList = "";
			for (int i = 1; i <= videoIdList.size(); i++) {
				idList += videoIdList.get(i - 1) + ",";
				if (i % 40 == 0 || i == videoIdList.size()) {
					videoDetails.setId(idList);
					List<Video> searchResult = videoDetails.execute().getItems();
					if (searchResult != null) {
						Iterator<Video> data = searchResult.iterator();
						if (!data.hasNext()) {
							System.out.println("비디오 id -> 비디오 정보 검색결과 없음.");
						}
						while (data.hasNext()) {
							Video item = data.next();
							if (item.getKind().equals("youtube#video")) {
								VideoStatDto newVideoStat = new VideoStatDto();
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								String infoDate = dateFormat.format(Calendar.getInstance().getTime());
								
								newVideoStat.setId(item.getId());
								newVideoStat.setViewCount(item.getStatistics().getViewCount());
								newVideoStat.setLikeCount(item.getStatistics().getLikeCount());
								newVideoStat.setDislikeCount(item.getStatistics().getDislikeCount());
								newVideoStat.setFavoriteCount(item.getStatistics().getFavoriteCount());
								newVideoStat.setCommentCount(item.getStatistics().getCommentCount());
								newVideoStat.setInfoDate(Date.valueOf(infoDate));
								videoStatList.add(newVideoStat);
							}
						}
						System.out.println();
					}
					idList = "";
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(videoStatList);
		return result;
	}

	//commentThread.list 1
	@Override
	public ArrayList<Object> callCommentsByVideoId(List<String> videoIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<CommentDto> commentList = new ArrayList<CommentDto>();
		try {
			YouTube.CommentThreads.List commentBasics = youtube.commentThreads().list("snippet");
			// api 키 입력
			commentBasics.setKey(apiKeys[api]);
			// 검색 결과 핫한 순으로 정렬
			commentBasics.setOrder("relevance");
			// 텍스트 -> 플레인 텍스트
			commentBasics.setTextFormat("plainText");
			// 조회 상한선
			commentBasics.setMaxResults((long) 40);
			for (String videoId : videoIdList) {
				// 검색어 지정
				commentBasics.setVideoId(videoId);
				try {
					List<CommentThread> searchResult = commentBasics.execute().getItems();
					if (searchResult != null) {
						Iterator<CommentThread> data = searchResult.iterator();
						if (!data.hasNext()) {
							System.out.println("댓글 유무: x");
						}
						while (data.hasNext()) {
							CommentThread item = data.next();
							if (item.getKind().equals("youtube#commentThread")) {
								CommentDto newComment = new CommentDto();
								newComment.setId(item.getId());
								newComment.setVideoId(item.getSnippet().getVideoId());
								newComment.setName(item.getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
								newComment.setLikeCount(item.getSnippet().getTopLevelComment().getSnippet().getLikeCount().intValue());
								newComment.setPublishedDate(Date.valueOf(item.getSnippet().getTopLevelComment().getSnippet().getPublishedAt().toString().substring(0, 10)));
								newComment.setText(item.getSnippet().getTopLevelComment().getSnippet().getTextDisplay());
								commentList.add(newComment);
							}
						}
					}
				} catch (GoogleJsonResponseException e) {
					System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
				}
			}
			
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(commentList);
		return result;
	}
}
