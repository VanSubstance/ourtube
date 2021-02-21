package com.my.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.my.spring.domain.*;

public interface YoutubeService {
	
	public ArrayList<Object> getChannelsBySearchVal(String ctgr);
	
	public ArrayList<Object> getVideosByChannelId(String channelId);
	
	public ArrayList<Object> getCommentsByVideoId(String videoId);
}
