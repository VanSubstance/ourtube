package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;

public interface VideoService {
	public List<String> getVideoIdsByCtgr(String ctgr);
	public List<String> getVideoIdsForStatisticsByCtgr(String ctgr);
	public List<String> getVideoIdsForCommentByCtgr(String ctgr);
	public void setVideoInfo(VideoDto item);
	public void setVideoChain(ChainDto item);
	public void setVideoStatistics(VideoStatDto item);
	public void setVideoTag(TagDto item);
	public int checkVideoInfo(String id);
	public int checkVideoChain(ChainDto item);
	public int checkVideoStatistics(VideoStatDto item);
}
