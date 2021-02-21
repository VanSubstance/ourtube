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
import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.CommentDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@Service
public class YoutubeServiceImpl implements YoutubeService {

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_CHANNELS_RETURNED = 10;
	private static final long NUMBER_OF_VIDEOS_RETURNED = 50;
	private static final long NUMBER_OF_COMMENTS_RETURNED = 20;
	private static YouTube youtube;
	private static final String[] apiKeys = { "AIzaSyDmY56rONS-hcNqXGFxYc9vgVeGSk0YeSc",
			"AIzaSyAE-kTRiIreaTxMsNG6Vg6W39YEv-a89x8", "AIzaSyAFdfs807Tl-7PM8tb4ZDOqfC7vKSCSaRg",
			"AIzaSyAQtHVKj5g7XtkJJh_Ipd5WlifxCOCwzsc", "AIzaSyCXiMrdsfLrPLtHRqhS5POORUzqrIK5_74",
			"AIzaSyC3AOXf-KO5m0vhucrB0nsI1w1PLw55StM", "AIzaSyDk3PSC2kj8oxTLvgFX57i49jUxljUApMI",
			"AIzaSyB1SWSCbjYnrjSu4eZ4q5IWnjQ3XMxa0L4", "AIzaSyBJfPmakS-n7iOVvCSOSoDRPz6_Eglu1As",
			"AIzaSyD4gF7sd48M26hJ0ci_rf52FgPdf8acA7k", "AIzaSyAC67nCWFmjqtw_LJOGuhHxfzLC-Chsndo",
			"AIzaSyBwzty52oeAL9pLGhC33YPh5eDg8YOL0GU", "AIzaSyBA5i1cy-bawh4fC3QZJCcPlN994RL2prY",
			"AIzaSyADgD5vbc9jfBiK-IGXpmFct6pU2UbYVts", "" };
	private static final int api = 3;

	// 채널 id 리스트 저장
	private static void getChannelList(Iterator<SearchResult> searchResults, List<String> channelIdList) {

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

	// 채널 id 리스트 -> 채널 세부 정보 저장
	private static void getChannelListDetail(Iterator<Channel> results, List<ChannelDto> channelDtoList,
			List<ChainDto> ctgrList) {

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

//				System.out.println("\nId: " + item.getId());
//				System.out.println("\nTitle: " + item.getSnippet().getTitle());
//				System.out.println("\nThumbnail: " + thumbnail.getUrl());
//				System.out.println("\nDescription: " + item.getSnippet().getDescription());
//				System.out.println("\nPublished date: " + publishedDate);
//				System.out.println("\nStatistics:");
//				System.out.println("\tViews: " + item.getStatistics().getViewCount());
//				System.out.println("\tSubs: " + item.getStatistics().getSubscriberCount());
//				System.out.println("\tVideos: " + item.getStatistics().getVideoCount());
//				System.out.println("\ninfo date: " + infoDate);

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

//				System.out.println("\nCtgrs:");
				if (item.getTopicDetails() != null && item.getTopicDetails().getTopicCategories() != null) {
					ChannelTopicDetails itemTopic = item.getTopicDetails();
					for (String ctgr : itemTopic.getTopicIds()) {
						String[] temp = ctgr.split("/");
						ctgr = temp[temp.length - 1];
//						System.out.println("\t" + ctgr);
						// 새 체인 생성
						ChainDto newChain = new ChainDto();
						newChain.setId(item.getId());
						newChain.setCtgr(ctgr);

						ctgrList.add(newChain);
					}
				}
			}
		}

	}

	// Advanced 카테고리 검색 -> 카테고리 + 채널 + 채널 카테고리 ~ Advanced 카테고리 체인 정보 저장
	@Override
	public ArrayList<Object> getChannelsBySearchVal(String ctgr) {
		String apiKeyCurrnet = apiKeys[api];
		List<ChannelDto> channelDtoList = new ArrayList<ChannelDto>();
		List<ChainDto> ctgrList = new ArrayList<ChainDto>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();

			YouTube.Search.List channelBasics = youtube.search().list("id");
			// api 키 입력
			channelBasics.setKey(apiKeyCurrnet);
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
			channelBasics.setMaxResults(NUMBER_OF_CHANNELS_RETURNED);
			List<SearchResult> channelList = channelBasics.execute().getItems();
			List<String> channelIdList = new ArrayList();
			if (channelList != null) {
				getChannelList(channelList.iterator(), channelIdList);
			}

			String idList = "";
			for (String channelId : channelIdList) {
				idList += channelId + ",";
			}

			YouTube.Channels.List channelDetails = youtube.channels().list("snippet, statistics, topicDetails");
			// api 키 입력
			channelDetails.setKey(apiKeyCurrnet);
			// 국가 한정
			channelDetails.setHl("ko_kr");
			channelDetails.setId(idList);
			channelDetails.setMaxResults(NUMBER_OF_CHANNELS_RETURNED);
			List<Channel> channelListDetail = channelDetails.execute().getItems();
			if (channelListDetail != null) {
				getChannelListDetail(channelListDetail.iterator(), channelDtoList, ctgrList);
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

	// 비디오 id 리스트 저장
	private static void getVideoList(Iterator<SearchResult> searchResults, List<String> videoIdList) {

		if (!searchResults.hasNext()) {
			System.out.println("채널 id -> 비디오 id 검색결과 없음.");
		}

		while (searchResults.hasNext()) {
			SearchResult item = searchResults.next();

			if (item.getKind().equals("youtube#searchResult")) {
				videoIdList.add(item.getId().getVideoId());
			}
		}
	}

	// 비디오 id 리스트 -> 비디오 정보 저장
	private static void getVideoListDetail(Iterator<Video> results, List<VideoDto> videoDtoList, List<ChainDto> chains,
			List<TagDto> tags) {

		if (!results.hasNext()) {
			System.out.println("비디오 id -> 비디오 정보 검색결과 없음.");
		}
		while (results.hasNext()) {
			Video item = results.next();
			if (item.getKind().equals("youtube#video")) {
				VideoDto newVideo = new VideoDto();
				Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().get("high");
				String publishedDate = item.getSnippet().getPublishedAt().toString().substring(0, 10);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String infoDate = dateFormat.format(Calendar.getInstance().getTime());

//				System.out.println("\nId: " + item.getId());
//				System.out.println("\nTitle: " + item.getSnippet().getTitle());
//				System.out.println("\nThumbnail: " + thumbnail.getUrl());
//				System.out.println("\nDescription: " + item.getSnippet().getDescription());
//				System.out.println("\nPublished date: " + publishedDate);
//				System.out.println("\nStatistics:");
//				System.out.println("\tViews: " + item.getStatistics().getViewCount());
//				System.out.println("\tLikes: " + item.getStatistics().getLikeCount());
//				System.out.println("\tDislikes: " + item.getStatistics().getDislikeCount());
//				System.out.println("\tFavorites: " + item.getStatistics().getFavoriteCount());
//				System.out.println("\tComments: " + item.getStatistics().getCommentCount());
//				System.out.println("\ninfo date: " + infoDate);
//				System.out.println("\nCategoryId: " + item.getSnippet().getCategoryId());

				// 새 체인 생성
				ChainDto newChain = new ChainDto();
				newChain.setId(item.getId());
				newChain.setCtgr(item.getSnippet().getCategoryId());
				chains.add(newChain);

				newVideo.setId(item.getId());
				newVideo.setTitle(item.getSnippet().getTitle());
				newVideo.setDescription(item.getSnippet().getDescription());
				newVideo.setThumbnail(thumbnail.getUrl());
				newVideo.setPublishedDate(Date.valueOf(publishedDate));
				newVideo.setViewCount(item.getStatistics().getViewCount());
				newVideo.setLikeCount(item.getStatistics().getDislikeCount());
				newVideo.setDislikeCount(item.getStatistics().getDislikeCount());
				newVideo.setFavoriteCount(item.getStatistics().getFavoriteCount());
				newVideo.setCommentCount(item.getStatistics().getCommentCount());
				newVideo.setInfoDate(Date.valueOf(infoDate));
				newVideo.setChannelId(item.getSnippet().getChannelId());
				videoDtoList.add(newVideo);

//				System.out.println("\nTags:");
				if (item.getSnippet().getTags() != null) {
					List<String> itemTags = item.getSnippet().getTags();
					for (String tag : itemTags) {
//						System.out.println("\t" + tag);
						TagDto newTag = new TagDto();
						newTag.setId(item.getId());
						newTag.setTag(tag);
						tags.add(newTag);
					}
				}
			}
		}

	}

	@Override
	public ArrayList<Object> getVideosByChannelId(String channelId) {
		String apiKeyCurrnet = apiKeys[api];
		List<VideoDto> videoDtoList = new ArrayList<VideoDto>();
		List<TagDto> tags = new ArrayList<TagDto>();
		List<ChainDto> chains = new ArrayList<ChainDto>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();

			YouTube.Search.List videoBasics = youtube.search().list("id");
			// api 키 입력
			videoBasics.setKey(apiKeyCurrnet);
			// 검색어 지정
			videoBasics.setChannelId(channelId);
			// 검색 결과 채널로 한정
			videoBasics.setType("video");
			// 검색 결과 제목으로 정렬
			videoBasics.setOrder("rating");
			// 검색 범위 한국으로 한정
			videoBasics.setRegionCode("KR");
			// 조회 상한선
			videoBasics.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			List<SearchResult> videoList = videoBasics.execute().getItems();
			List<String> videoIdList = new ArrayList();
			if (videoList != null) {
				getVideoList(videoList.iterator(), videoIdList);
			}

			String idList = "";
			for (String videoId : videoIdList) {
				idList += videoId + ",";
			}

			YouTube.Videos.List videoDetails = youtube.videos().list("snippet, statistics, topicDetails");
			// api 키 입력
			videoDetails.setKey(apiKeyCurrnet);
			// 국가 한정
			videoDetails.setHl("ko_kr");
//			System.out.println(idList);
			videoDetails.setId(idList);
			List<Video> videoListDetail = videoDetails.execute().getItems();
			if (videoListDetail != null) {
				getVideoListDetail(videoListDetail.iterator(), videoDtoList, chains, tags);
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
		result.add(videoDtoList);
		result.add(chains);
		result.add(tags);
		return result;
	}

	private static void getCommentListDetail(Iterator<CommentThread> results, List<CommentDto> commentDtoList) {

		if (!results.hasNext()) {
			System.out.println("비디오 id -> 댓글 정보 검색결과 없음.");
		}
		while (results.hasNext()) {
			CommentThread item = results.next();
			if (item.getKind().equals("youtube#commentThread")) {
				CommentDto newComment = new CommentDto();
				newComment.setId(item.getId());
				newComment.setVideoId(item.getSnippet().getVideoId());
				newComment.setName(item.getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
				newComment.setLikeCount(item.getSnippet().getTopLevelComment().getSnippet().getLikeCount().intValue());
				newComment.setPublishedDate(Date.valueOf(item.getSnippet().getTopLevelComment().getSnippet().getPublishedAt().toString().substring(0, 10)));
				newComment.setText(item.getSnippet().getTopLevelComment().getSnippet().getTextDisplay());
				commentDtoList.add(newComment);
			}
		}

	}

	@Override
	public ArrayList<Object> getCommentsByVideoId(String videoId) {
		String apiKeyCurrnet = apiKeys[api];
		List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("Youtube-connection").build();

			YouTube.CommentThreads.List commentBasics = youtube.commentThreads().list("snippet");
			// api 키 입력
			commentBasics.setKey(apiKeyCurrnet);
			// 검색어 지정
			commentBasics.setVideoId(videoId);
			// 검색 결과 핫한 순으로 정렬
			commentBasics.setOrder("relevance");
			// 텍스트 -> 플레인 텍스트
			commentBasics.setTextFormat("plainText");
			// 조회 상한선
			commentBasics.setMaxResults(NUMBER_OF_COMMENTS_RETURNED);
			List<CommentThread> commentList = commentBasics.execute().getItems();
			List<String> commentIdList = new ArrayList();
			if (commentList != null) {
				getCommentListDetail(commentList.iterator(), commentDtoList);
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
		result.add(commentDtoList);
		return result;
	}

}
