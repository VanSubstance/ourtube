package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.*;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping(value = "/youtube")
public class YoutubeController {
	
	private YoutubeService service;

	@Autowired
	public YoutubeController(final YoutubeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public List<ChannelDto> Index(@PathVariable String ctgr) {
		System.out.println(ctgr);
		return service.getChannelsByCtgr(ctgr);
	}
	
}
