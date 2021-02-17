package com.my.spring.service;

import com.my.spring.domain.YoutubeDto;

public interface YoutubeService {
	public YoutubeDto get();
	
	public YoutubeDto getThumbnail();
}
