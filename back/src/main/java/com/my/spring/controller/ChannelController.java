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
	
	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public List<String> getChannelsByCtgr(@PathVariable String ctgr) {
		return service.getChannelsByCtgr(ctgr);
	}
}
