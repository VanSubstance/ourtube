package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.chains.TopicChain;
import com.my.spring.service.BasicService;

@CrossOrigin("*")
@RestController
@RequestMapping("/process")
public class ProcessingController {
	@Autowired
	private BasicService serviceBasic;
	
	@RequestMapping(value = "/topic/chain/all")
	public List<TopicChain> getTopicChains() {
		System.out.println("���� ü�� ��ȸ");
		List<TopicChain> result = serviceBasic.getTopicChains();
		return result;
	}
	
	@RequestMapping(value = "/topic/chain/{topic}", method = RequestMethod.GET)
	public List<TopicChain> getTopicChainsByTopic(@PathVariable String topic) {
		System.out.println("���� ü�� ��ȸ: " + topic);
		return serviceBasic.getTopicChainsByTopic(topic);
	}
	
}
