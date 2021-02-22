package com.my.spring.service;

import java.util.ArrayList;
import java.util.List;

public interface YoutubeService {
	
	/**
	 * ī�װ� -> ���� ä�� id ����Ʈ
	 * List<String>
	 */
	public ArrayList<Object> callChannelIdsByCtgr(String ctgr);
	
	/**
	 * ä�� id ����Ʈ -> ä�� �⺻ ���� & ü�� ����
	 * List<ChannelDto>
	 * List<ChainDto>
	 */
	public ArrayList<Object> callChannelInfosByChannelId(List<String> channelIdList);
	
	/**
	 * ä�� id ����Ʈ -> �ش��� ä�� ��� ����
	 * List<ChannelStatDto>
	 */
	public ArrayList<Object> callChannelStatsByChannelId(List<String> channelIdList);
	
	/**
	 * ä�� id -> ���� ���� id ����Ʈ
	 * List<String>
	 */
	public ArrayList<Object> callVideoIdsByChannelId(String channelId);
	
	/**
	 * ���� id ����Ʈ -> ���� �⺻ ���� & ü�� ���� & �±� ����
	 * List<VideoDto>
	 * List<ChainDto>
	 * List<TagDto>
	 */
	public ArrayList<Object> callVideoInfosByVideoId(List<String> videoIdList);
	
	/**
	 * ���� id ����Ʈ -> �ش��� ä�� ��� ����
	 * List<VideoStatDto>
	 */
	public ArrayList<Object> callVideoStatsByVideoId(List<String> videoIdList);
	
	/**
	 * ���� id ����Ʈ -> ��� ����
	 * List<CommentDto>
	 */
	public ArrayList<Object> callCommentsByVideoId(List<String> videoIdList);
	
}
