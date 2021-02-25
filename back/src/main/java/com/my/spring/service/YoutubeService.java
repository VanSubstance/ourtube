package com.my.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.TopicStatDto;

public interface YoutubeService {
	

	/**
	 * ���� -> �ش��� ���� �˻���� ��ġ
	 * TopicStatDto
	 */
	public ArrayList<Object> callTopicStatByTopic(TopicDto topicDto);
	
	/**
	 * ī�װ� -> ���� ä�� id ����Ʈ
	 * List<String>
	 * TopicStatDto
	 */
	public ArrayList<Object> callChannelIdsByTopic(TopicDto topicDto);
	
	public ArrayList<Object> callVideoIdsByTopic(TopicDto topicDto);
	
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
