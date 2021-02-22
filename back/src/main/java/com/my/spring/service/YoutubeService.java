package com.my.spring.service;

import java.util.ArrayList;
import java.util.List;

public interface YoutubeService {
	
	/**
	 * 카테고리 -> 상위 채널 id 리스트
	 * List<String>
	 */
	public ArrayList<Object> callChannelIdsByCtgr(String ctgr);
	
	/**
	 * 채널 id 리스트 -> 채널 기본 정보 & 체인 정보
	 * List<ChannelDto>
	 * List<ChainDto>
	 */
	public ArrayList<Object> callChannelInfosByChannelId(List<String> channelIdList);
	
	/**
	 * 채널 id 리스트 -> 해당일 채널 통계 정보
	 * List<ChannelStatDto>
	 */
	public ArrayList<Object> callChannelStatsByChannelId(List<String> channelIdList);
	
	/**
	 * 채널 id -> 상위 비디오 id 리스트
	 * List<String>
	 */
	public ArrayList<Object> callVideoIdsByChannelId(String channelId);
	
	/**
	 * 비디오 id 리스트 -> 비디오 기본 정보 & 체인 정보 & 태그 정보
	 * List<VideoDto>
	 * List<ChainDto>
	 * List<TagDto>
	 */
	public ArrayList<Object> callVideoInfosByVideoId(List<String> videoIdList);
	
	/**
	 * 비디오 id 리스트 -> 해당일 채널 통계 정보
	 * List<VideoStatDto>
	 */
	public ArrayList<Object> callVideoStatsByVideoId(List<String> videoIdList);
	
	/**
	 * 비디오 id 리스트 -> 댓글 정보
	 * List<CommentDto>
	 */
	public ArrayList<Object> callCommentsByVideoId(List<String> videoIdList);
	
}
