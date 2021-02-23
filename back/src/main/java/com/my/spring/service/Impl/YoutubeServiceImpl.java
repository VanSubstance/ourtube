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
import com.my.spring.domain.ResultCtgr;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_CHANNELS_RETURNED = 3;
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	private static final long NUMBER_OF_COMMENTS_RETURNED = 10;
	private static YouTube youtube;
	private static final String[] apiKeys = { 
			"AIzaSyDmY56rONS-hcNqXGFxYc9vgVeGSk0YeSc",
			"AIzaSyAE-kTRiIreaTxMsNG6Vg6W39YEv-a89x8", 
			"AIzaSyAFdfs807Tl-7PM8tb4ZDOqfC7vKSCSaRg",
			"AIzaSyAQtHVKj5g7XtkJJh_Ipd5WlifxCOCwzsc", 
			"AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74"};
	private static final int api = 0;

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

	@Override
	public ArrayList<Object> callResultCtgrBtCtgr(String ctgr) {
		ArrayList<Object> result = new ArrayList<Object>();
		ResultCtgr resultCtgr = new ResultCtgr();
		try {
			YouTube.Search.List base = youtube.search().list("id");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 검색어 지정
			base.setQ(ctgr);
			// 검색 결과 채널로 한정
			base.setType("channel");
			// 검색 결과 제목으로 정렬
			base.setOrder("viewCount");
			// 검색 범위 한국으로 한정
			base.setRegionCode("KR");
			// 토픽 아이디 한정
			base.setTopicId("/m/0bzvm2");
			// 조회 상한선
			base.setMaxResults((long) 0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String infoDate = dateFormat.format(Calendar.getInstance().getTime());
			resultCtgr.setInfoDate(Date.valueOf(infoDate));
			resultCtgr.setCtgr(ctgr);
			resultCtgr.setResultCount(base.execute().getPageInfo().getTotalResults());
		} catch (GoogleJsonResponseException e) {
			System.err.println("SERVICE ERROR: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("IO ERROR: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		result.add(resultCtgr);
		return result;
	}

	@Override
	public ArrayList<Object> callChannelIdsByCtgr(String ctgr) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<String> channelIdList = new ArrayList();
		ResultCtgr resultCtgr = new ResultCtgr();
		try {
			YouTube.Search.List base = youtube.search().list("id");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 검색어 지정
			base.setQ(ctgr);
			// 검색 결과 채널로 한정
			base.setType("channel");
			// 검색 결과 제목으로 정렬
			base.setOrder("viewCount");
			// 검색 범위 한국으로 한정
			base.setRegionCode("KR");
			// 토픽 아이디 한정
			base.setTopicId("/m/0bzvm2");
			// 조회 상한선
			base.setMaxResults(NUMBER_OF_CHANNELS_RETURNED);
			
			List<SearchResult> searchResults = base.execute().getItems();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String infoDate = dateFormat.format(Calendar.getInstance().getTime());
			resultCtgr.setInfoDate(Date.valueOf(infoDate));
			resultCtgr.setCtgr(ctgr);
			resultCtgr.setResultCount(base.execute().getPageInfo().getTotalResults());
			if (base != null) {
				Iterator<SearchResult> data = searchResults.iterator();
				if (!data.hasNext()) {
					System.out.println("No results for your query.");
				}
				while (data.hasNext()) {
					SearchResult item = data.next();
					if (item.getKind().equals("youtube#searchResult")) {
						channelIdList.add(item.getId().getChannelId());
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
		result.add(channelIdList);
		result.add(resultCtgr);
		return result;
	}

	@Override
	public ArrayList<Object> callChannelInfosByChannelId(List<String> channelIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<ChannelDto> channelList = new ArrayList<ChannelDto>();
		List<ChainDto> chainList = new ArrayList<ChainDto>();
		try {
			YouTube.Channels.List base = youtube.channels().list("snippet, topicDetails");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 국가 한정
			base.setHl("ko_kr");
			String idList = "";
			for (String id : channelIdList) {
				idList += id + ",";
			}
			base.setId(idList);
			base.setMaxResults(NUMBER_OF_CHANNELS_RETURNED);
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
						
						if (item.getTopicDetails() != null && item.getTopicDetails().getTopicCategories() != null) {
							ChannelTopicDetails itemTopic = item.getTopicDetails();
							for (String ctgr : itemTopic.getTopicIds()) {
								String[] temp = ctgr.split("/");
								ctgr = temp[temp.length - 1];
								ChainDto newChain = new ChainDto();
								newChain.setId(item.getId());
								newChain.setCtgr(ctgr);
								chainList.add(newChain);
							}
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
		result.add(channelList);
		result.add(chainList);
		return result;
	}

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
			for (String id : channelIdList) {
				base.setId(id);
				base.setMaxResults(NUMBER_OF_CHANNELS_RETURNED);
				List<Channel> searchResult = base.execute().getItems();
				if (searchResult != null) {
					Iterator<Channel> data = searchResult.iterator();
					if (!data.hasNext()) {
						System.out.println("No results for your query.");
					}
					while (data.hasNext()) {
						Channel item = data.next();
						if (item.getKind().equals("youtube#channel")) {
							System.out.print("|");
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

	@Override
	public ArrayList<Object> callVideoIdsByChannelId(String channelId) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<String> videoIdList = new ArrayList<String>();
		try {
			YouTube.Search.List base = youtube.search().list("id");
			// api 키 입력
			base.setKey(apiKeys[api]);
			// 검색어 지정
			base.setChannelId(channelId);
			// 검색 결과 채널로 한정
			base.setType("video");
			// 검색 결과 제목으로 정렬
			base.setOrder("rating");
			// 검색 범위 한국으로 한정
			base.setRegionCode("KR");
			// 조회 상한선
			base.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<SearchResult> searchResult = base.execute().getItems();
			if (searchResult != null) {
				Iterator<SearchResult> data = searchResult.iterator();
				if (!data.hasNext()) {
					System.out.println("채널 id -> " + channelId + " -> " + "비디오 id 검색결과 없음.");
				}
				while (data.hasNext()) {
					SearchResult item = data.next();
					if (item.getKind().equals("youtube#searchResult")) {
						videoIdList.add(item.getId().getVideoId());
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
		return result;
	}

	@Override
	public ArrayList<Object> callVideoInfosByVideoId(List<String> videoIdList) {
		ArrayList<Object> result = new ArrayList<Object>();
		List<VideoDto> videoList = new ArrayList<VideoDto>();
		List<ChainDto> chainList = new ArrayList<ChainDto>();
		List<TagDto> tagList = new ArrayList<TagDto>();
		try {
			YouTube.Videos.List videoDetails = youtube.videos().list("snippet, topicDetails");
			// api 키 입력
			videoDetails.setKey(apiKeys[api]);
			// 국가 한정
			videoDetails.setHl("ko_kr");
			for (String videoId : videoIdList) {
				videoDetails.setId(videoId);
				List<Video> searchResult = videoDetails.execute().getItems();
				if (searchResult != null) {
					Iterator<Video> data = searchResult.iterator();
					if (!data.hasNext()) {
						System.out.println("비디오 id -> 비디오 정보 검색결과 없음.");
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

							if (item.getTopicDetails() != null && item.getTopicDetails().getTopicIds() != null) {
								for (String topicId : item.getTopicDetails().getTopicIds()) {
									ChainDto newChain = new ChainDto();
									newChain.setId(item.getId());
									newChain.setCtgr(topicId);
									chainList.add(newChain);
								}
							}
							
							if (item.getSnippet().getTags() != null) {
								List<String> itemTags = item.getSnippet().getTags();
								for (String tag : itemTags) {
									TagDto newTag = new TagDto();
									newTag.setId(item.getId());
									newTag.setTag(tag);
									tagList.add(newTag);
								}
							}
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
		result.add(videoList);
		result.add(chainList);
		result.add(tagList);
		return result;
	}

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
			String idList = "";
			for (String videoId : videoIdList) {
				idList += videoId + ",";
			}
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
						System.out.print("|");
						VideoStatDto newVideoStat = new VideoStatDto();
						Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
						String publishedDate = item.getSnippet().getPublishedAt().toString().substring(0, 10);
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String infoDate = dateFormat.format(Calendar.getInstance().getTime());
						
						newVideoStat.setId(item.getId());
						newVideoStat.setViewCount(item.getStatistics().getViewCount());
						newVideoStat.setLikeCount(item.getStatistics().getDislikeCount());
						newVideoStat.setDislikeCount(item.getStatistics().getDislikeCount());
						newVideoStat.setFavoriteCount(item.getStatistics().getFavoriteCount());
						newVideoStat.setCommentCount(item.getStatistics().getCommentCount());
						newVideoStat.setInfoDate(Date.valueOf(infoDate));
						videoStatList.add(newVideoStat);
					}
				}
				System.out.println();
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
		return result;
	}

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
			commentBasics.setMaxResults(NUMBER_OF_COMMENTS_RETURNED);
			for (String videoId : videoIdList) {
				// 검색어 지정
				System.out.print("비디오 id -> " + videoId + " -> ");
				commentBasics.setVideoId(videoId);
				List<CommentThread> searchResult = commentBasics.execute().getItems();
				if (searchResult != null) {
					Iterator<CommentThread> data = searchResult.iterator();
					if (!data.hasNext()) {
						System.out.println("댓글 유무: x");
					}
					System.out.println("댓글 유무: o");
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
