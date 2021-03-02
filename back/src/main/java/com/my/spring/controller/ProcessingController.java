package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TopicDto;
import com.my.spring.domain.chains.TopicChain;
import com.my.spring.service.BasicService;

@RestController
@RequestMapping("/process")
public class ProcessingController {
	@Autowired
	private BasicService serviceBasic;
	
	@RequestMapping(value = "/topic/chain/all")
	public List<TopicChain> getTopicChains() {
		System.out.println("토픽 체인 조회");
		List<TopicChain> result = serviceBasic.getTopicChains();
		return result;
	}
	
}
