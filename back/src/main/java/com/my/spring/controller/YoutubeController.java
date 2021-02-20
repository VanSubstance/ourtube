package com.my.spring.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.*;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.VideoService;
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
	private VideoService serviceVideo;

	@Autowired
	public YoutubeController(final YoutubeService service) {
		this.service = service;
	}	

	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public List<ChannelDto> putChannelInfoByCtgr(@PathVariable String ctgr) {
		System.out.println("ī�װ�: " + ctgr);
		ArrayList<Object> dataset = service.getChannelsBySearchVal(ctgr);
		List<ChannelDto> channels = (List<ChannelDto>) dataset.get(0);
		List<ChainDto> chains = (List<ChainDto>) dataset.get(1);
		if (channels.size() != 0) {
			System.out.print("ī�װ� ��� ->");
			// ī�װ� ���
			if (serviceBasic.checkCtgr(ctgr) == 0) {
				serviceBasic.addCtgr(ctgr);
				System.out.println("�Ϸ�");
			} else {
				System.out.println("�̹� ����");
			}

			// ä�� ���
			System.out.print("ä�� ��� -> ");
			for (ChannelDto channel : channels) {
				if (serviceChannel.checkExistence(channel).size() == 0) {
					System.out.print("o");
					serviceChannel.putChannelInfo(channel);
				} else {
					System.out.print("x");
				}
				// ä�� �� ctgr�� ü�� ���
				ChainDto newchain = new ChainDto();
				newchain.setId(channel.getId());
				newchain.setCtgr(ctgr);
				serviceChannel.addChainRaw(newchain);
			}
			System.out.println(" �Ϸ�");

			// ü�� ���(ä�ΰ� �⺻ ī�װ����� ü��)
			System.out.print("ü�� ��� -> ");
			Map<String, Object> paramChains = new HashMap<String, Object>();
			paramChains.put("chains", chains);
			serviceChannel.addChain(paramChains);
			System.out.println(" �Ϸ�");

			// ī�װ��鰣�� relation ���
			System.out.print("ctgrRelation ��� -> ");
			for (ChainDto chain : chains) {
				if (!chain.getCtgr().equals(ctgr)) {
					System.out.print("o");
					serviceBasic.addCtgrRelation(ctgr, chain.getCtgr());
				} else {
					System.out.print("x");
				}
			}
			System.out.println(" �Ϸ�");
			
//			// ���� �̴� ������ ����
//			serviceChannel.cleanChain();
//			serviceChannel.cleanChannel();
		}
		System.out.println(ctgr
				+ " ä�� �˻� / ��� ����\n----------------------------------------\n----------------------------------------\n");
		return channels;
	}

	@RequestMapping(value = "/video/{channelId}", method = RequestMethod.GET)
	public List<VideoDto> putVideoInfoByChannelId(@PathVariable String channelId) {
		System.out.println("ä�� ���̵�: " + channelId);
		ArrayList<Object> dataset = service.getVideosByChannelId(channelId);
		List<VideoDto> videos = (List<VideoDto>) dataset.get(0);
		List<ChainDto> chains = (List<ChainDto>) dataset.get(1);
		List<TagDto> tags = (List<TagDto>) dataset.get(2);
		if (videos.size() != 0) {
			System.out.print("���� ����Ʈ ��� ->");
			for (VideoDto video : videos) {
				if (serviceVideo.checkExistence(video.getId()) == 0 && serviceVideo.checkExistenceParent(video) != 0) {
					System.out.print("o");
					serviceVideo.addVideo(video);
				} else {
					System.out.print("x");
				}
			}
			System.out.println(" �Ϸ�.\n");
			if (chains.size() != 0) {
				Map<String, Object> paramChains = new HashMap<String, Object>();
				paramChains.put("chains", chains);
				System.out.println("ü�� ��� -> ");
				serviceVideo.addChain(paramChains);
				System.out.println(" �Ϸ�.\n");
			} else {
				System.out.println("ü�� ����.");
			}
			if (tags.size() != 0) {
				System.out.println("�±� ��� -> " + tags.size() + "��");
				for (TagDto tag : tags) {
					System.out.print(",");
				}
				System.out.println();
				for (TagDto tag : tags) {
					System.out.print(".");
					serviceVideo.addTagSingle(tag);
				}
				System.out.println(" �Ϸ�.\n");
			} else {
				System.out.println("�±� ����.");
			}
//			serviceVideo.cleanChain();
//			serviceVideo.cleanVideo();
		}
		System.out.println(channelId
				+ " ������ �˻� / ��� ����\n----------------------------------------\n----------------------------------------\n");

		return videos;
	}
}
