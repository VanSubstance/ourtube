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

	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public List<ChannelDto> putChannelInfoByCtgr(@PathVariable String ctgr) {
		ArrayList<Object> dataset = service.getChannelsBySearchVal(ctgr);
		List<ChannelDto> channels = (List<ChannelDto>) dataset.get(0);
		List<ChainChannelDto> chains = (List<ChainChannelDto>) dataset.get(1);
		// 카테고리를 위한 리스트 생성
		List<String> basicCtgrs = new ArrayList();
		basicCtgrs.add(ctgr);
		
		System.out.println("카테고리 등록: " + ctgr);
		// 카테고리 등록
		if (serviceBasic.checkCtgr(ctgr) == 0) {
			serviceBasic.addCtgr(ctgr);
		}

		System.out.println("-----------------------------------------------------");

		// 채널 등록
		for (ChannelDto channel : channels) {
			System.out.println("채널 등록: " + channel.getTitle() + " ~ " + channel.getId());
			if (serviceChannel.checkExistence(channel.getId()).size() == 0) {
				System.out.println("등록중");
				serviceChannel.putChannelInfo(channel);
			}
			// 채널 별 ctgr와 체인 등록
			if (serviceChannel.checkExistenceChain(channel.getId(), ctgr).size() == 0) {
				serviceChannel.putChain(channel.getId(), ctgr);
			}
		}
		
		System.out.println("-----------------------------------------------------");
		
		
		// 체인 등록(채널과 기본 카테고리간의 체인)
		for (ChainChannelDto chain : chains) {
			String ctgrTranslated = serviceBasic.translateCtgrByTopicId(chain.getCtgr());
			System.out.println("Assigning chain: " + chain.getChannelId() + "\t~\t" + ctgrTranslated + "|" + chain.getCtgr());
			if (serviceChannel.checkExistenceChain(chain.getChannelId(), ctgrTranslated).size() == 0) {
				serviceChannel.putChain(chain.getChannelId(), ctgrTranslated);
			}
			if (!basicCtgrs.contains(ctgrTranslated)) {
				basicCtgrs.add(ctgrTranslated);
			}
		}
		

		System.out.println("-----------------------------------------------------");
		
		System.out.println("basicCtgrs: " + basicCtgrs);
		// 카테고리들간의 relation 등록
		for (String basicCtgr: basicCtgrs) {
			if (!basicCtgr.equals(ctgr)) {
				System.out.println("Assigning relation: \n\tparent: " + basicCtgr + "\n\ttitle: " + ctgr);
				serviceBasic.addCtgrRelation(basicCtgr, ctgr);
			}
		}
		
		System.out.println(ctgr + " 채널 검색 / 등록 종료\n----------------------------------------\n----------------------------------------\n");
		
		return channels;
	}
	
}
