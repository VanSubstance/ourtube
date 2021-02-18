package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.*;

public interface YoutubeService {
	public YoutubeDto get();
	
	public List<ChannelDto> getChannelsByCtgr(String ctgr);
}
