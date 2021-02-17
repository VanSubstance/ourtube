package com.my.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.YoutubeDto;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping(value = "/youtube")
public class YoutubeController {
	
	private YoutubeService service;

	@Autowired
	public YoutubeController(final YoutubeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/test")
	public YoutubeDto Index() {
		return service.get();
	}
	@RequestMapping(value = "/testChannel")
	public YoutubeDto getChannel() {
		return service.getThumbnail();
	}
	
}
