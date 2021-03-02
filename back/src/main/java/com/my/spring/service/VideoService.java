package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;

public interface VideoService {
	public List<String> getVideoIdsByTopic(String topic);
	public List<String> getVideoIdsForStatisticsByTopic(String topic);
	public List<String> getVideoIdsForCommentByTopic(String topic);
	public String getDescriptionByVideoId(String id);
	public List<TagDto> getVideoTagByVideoId(String id);
	public void setVideoInfo(VideoDto item);
	public void setChain(ChainDto item);
	public void setVideoStatistics(VideoStatDto item);
	public void setVideoTag(TagDto item);
	public int checkVideoInfo(String id);
	public int checkChain(ChainDto item);
	public int checkVideoStatistics(VideoStatDto item);
	public List<VideoDto> getVideoInfo();
	public List<VideoDto> getVideoInfoById(List<String> list);
	public void filterVideoWord();
	public void filterTagWord();
}
