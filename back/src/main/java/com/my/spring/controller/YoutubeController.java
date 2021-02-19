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
		// ī�װ��� ���� ����Ʈ ����
		List<String> basicCtgrs = new ArrayList();
		basicCtgrs.add(ctgr);
		
		System.out.println("ī�װ� ���: " + ctgr);
		// ī�װ� ���
		if (serviceBasic.checkCtgr(ctgr) == 0) {
			serviceBasic.addCtgr(ctgr);
		}

		System.out.println("-----------------------------------------------------");

		// ä�� ���
		for (ChannelDto channel : channels) {
			System.out.println("ä�� ���: " + channel.getTitle() + " ~ " + channel.getId());
			if (serviceChannel.checkExistence(channel.getId()).size() == 0) {
				System.out.println("�����");
				serviceChannel.putChannelInfo(channel);
			}
			// ä�� �� ctgr�� ü�� ���
			if (serviceChannel.checkExistenceChain(channel.getId(), ctgr).size() == 0) {
				serviceChannel.putChain(channel.getId(), ctgr);
			}
		}
		
		System.out.println("-----------------------------------------------------");
		
		
		// ü�� ���(ä�ΰ� �⺻ ī�װ����� ü��)
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
		// ī�װ��鰣�� relation ���
		for (String basicCtgr: basicCtgrs) {
			if (!basicCtgr.equals(ctgr)) {
				System.out.println("Assigning relation: \n\tparent: " + basicCtgr + "\n\ttitle: " + ctgr);
				serviceBasic.addCtgrRelation(basicCtgr, ctgr);
			}
		}
		
		System.out.println(ctgr + " ä�� �˻� / ��� ����\n----------------------------------------\n----------------------------------------\n");
		
		return channels;
	}
	
}
