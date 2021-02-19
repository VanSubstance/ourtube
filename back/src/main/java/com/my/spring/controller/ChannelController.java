package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.service.ChannelService;

@RestController
@RequestMapping("/channel")
public class ChannelController {
	
	@Autowired
	private ChannelService service;
	
	@RequestMapping(value = "/{ctgr}", method = RequestMethod.GET)
	public List<String> getChannelsByCtgr(@PathVariable String ctgr) {
		return service.getChannelsByCtgr(ctgr);
	}
	
	@RequestMapping(value = "/id/{channelId}", method = RequestMethod.GET)
	public List<String> getCtgrsByChannelId(@PathVariable String channelId) {
		return service.getCtgrsByChannelId(channelId);
	}
}
