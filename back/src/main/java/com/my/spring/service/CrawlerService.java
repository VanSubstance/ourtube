package com.my.spring.service;

import java.util.ArrayList;

public interface CrawlerService {
	public ArrayList<Object> crawlGame();
	public ArrayList<Object> crawlGameVidsManual(String gameQ);
}
