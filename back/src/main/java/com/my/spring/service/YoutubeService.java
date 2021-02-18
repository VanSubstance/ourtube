package com.my.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.my.spring.domain.*;

public interface YoutubeService {
	public YoutubeDto get();
	
	public ArrayList<Object> getChannelsByCtgr(String ctgr);
	
}
