package com.my.spring.controller;

import java.sql.SQLException;
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
	public List<ChannelDto> searchChannelsBySearchVal(@PathVariable String searchVal) throws SQLException {
		ArrayList<Object> dataset = service.getChannelsBySearchVal(searchVal);
		List<ChannelDto> channels = (List<ChannelDto>) dataset.get(0);
		List<ChainChannelDto> chains = (List<ChainChannelDto>) dataset.get(1);
		System.out.println("Getting Dataset dones.\n");
		System.out.println("# of Channel: " + channels.size());
		
		for (ChannelDto channel : channels) {
			System.out.println(channel.getTitle());
			if (serviceChannel.checkExistence(channel.getId()).size() == 0) {
				serviceChannel.putChannelInfo(channel);
			}
		}
		
		for (ChainChannelDto chain : chains) {
			String ctgrTranslated = serviceBasic.translateCtgrByTopicId(chain.getCtgr());
			if (serviceChannel.checkExistenceChain(chain.getChannelId(), ctgrTranslated).size() == 0) {
				serviceChannel.putChain(chain.getChannelId(), ctgrTranslated);
			}
		}
		return channels;
	}
	
}
