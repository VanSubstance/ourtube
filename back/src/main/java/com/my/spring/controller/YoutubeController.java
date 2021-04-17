package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.service.BasicService;
import com.my.spring.service.CrawlerService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/test")
public class YoutubeController {
	@Autowired
	CrawlerService service;
	@Autowired
	BasicService serviceB;
	
	@RequestMapping(value = "/1")
	public void crawlManually() {
		System.out.println("ũ�ѷ� �׽�Ʈ");
		List<String> qs = serviceB.getGameQ();
		for (String q : qs) {
			System.out.println(q);
			service.crawlGameVidsManual(q);
		}
	}
}
