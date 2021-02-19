package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.*;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping(value = "/ytb")
public class YoutubeController {
	
	private YoutubeService service;

	@Autowired
	private BasicService serviceBasic;
	
	@Autowired
	private ChannelService serviceChannel;
	
	@Autowired
	public YoutubeController(final YoutubeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/channel/{searchVal}", method = RequestMethod.GET)
	public List<ChannelDto> searchChannelsBySearchVal(@PathVariable String searchVal) {
		ArrayList<Object> dataset = service.getChannelsBySearchVal(searchVal);
		List<ChannelDto> channels = (List<ChannelDto>) dataset.get(0);
		List<ChainChannelDto> chains = (List<ChainChannelDto>) dataset.get(1);
		System.out.println("Getting Dataset dones.\n");
		System.out.println("# of Channel: " + channels.size());
		
		for (ChannelDto channel : channels) {
			System.out.println(channel.getTitle());
			if (!serviceChannel.putChannelInfo(channel)) {
				System.out.println("Already existed channel.");
			}
		}
		
		for (ChainChannelDto chain : chains) {
			String ctgrTranslated = serviceBasic.translateCtgrByTopicId(chain.getCtgr());
			if (!serviceChannel.putChain(chain.getChannelId(), ctgrTranslated)) {
				System.out.println("Already existed chain.");
			}
		}
		return channels;
	}
	
}
