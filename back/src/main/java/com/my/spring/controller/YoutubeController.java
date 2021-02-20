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
		System.out.println("카테고리: " + ctgr);
		ArrayList<Object> dataset = service.getChannelsBySearchVal(ctgr);
		List<ChannelDto> channels = (List<ChannelDto>) dataset.get(0);
		List<ChainDto> chains = (List<ChainDto>) dataset.get(1);
		if (channels.size() != 0) {
			System.out.print("카테고리 등록 ->");
			// 카테고리 등록
			if (serviceBasic.checkCtgr(ctgr) == 0) {
				serviceBasic.addCtgr(ctgr);
				System.out.println("완료");
			} else {
				System.out.println("이미 존재");
			}

			// 채널 등록
			System.out.print("채널 등록 -> ");
			for (ChannelDto channel : channels) {
				if (serviceChannel.checkExistence(channel).size() == 0) {
					System.out.print("o");
					serviceChannel.putChannelInfo(channel);
				} else {
					System.out.print("x");
				}
				// 채널 별 ctgr와 체인 등록
				ChainDto newchain = new ChainDto();
				newchain.setId(channel.getId());
				newchain.setCtgr(ctgr);
				serviceChannel.addChainRaw(newchain);
			}
			System.out.println(" 완료");

			// 체인 등록(채널과 기본 카테고리간의 체인)
			System.out.print("체인 등록 -> ");
			Map<String, Object> paramChains = new HashMap<String, Object>();
			paramChains.put("chains", chains);
			serviceChannel.addChain(paramChains);
			System.out.println(" 완료");

			// 카테고리들간의 relation 등록
			System.out.print("ctgrRelation 등록 -> ");
			for (ChainDto chain : chains) {
				if (!chain.getCtgr().equals(ctgr)) {
					System.out.print("o");
					serviceBasic.addCtgrRelation(ctgr, chain.getCtgr());
				} else {
					System.out.print("x");
				}
			}
			System.out.println(" 완료");
			
//			// 기준 미달 데이터 삭제
//			serviceChannel.cleanChain();
//			serviceChannel.cleanChannel();
		}
		System.out.println(ctgr
				+ " 채널 검색 / 등록 종료\n----------------------------------------\n----------------------------------------\n");
		return channels;
	}

	@RequestMapping(value = "/video/{channelId}", method = RequestMethod.GET)
	public List<VideoDto> putVideoInfoByChannelId(@PathVariable String channelId) {
		System.out.println("채널 아이디: " + channelId);
		ArrayList<Object> dataset = service.getVideosByChannelId(channelId);
		List<VideoDto> videos = (List<VideoDto>) dataset.get(0);
		List<ChainDto> chains = (List<ChainDto>) dataset.get(1);
		List<TagDto> tags = (List<TagDto>) dataset.get(2);
		if (videos.size() != 0) {
			System.out.print("비디오 리스트 등록 ->");
			for (VideoDto video : videos) {
				if (serviceVideo.checkExistence(video.getId()) == 0 && serviceVideo.checkExistenceParent(video) != 0) {
					System.out.print("o");
					serviceVideo.addVideo(video);
				} else {
					System.out.print("x");
				}
			}
			System.out.println(" 완료.\n");
			if (chains.size() != 0) {
				Map<String, Object> paramChains = new HashMap<String, Object>();
				paramChains.put("chains", chains);
				System.out.println("체인 등록 -> ");
				serviceVideo.addChain(paramChains);
				System.out.println(" 완료.\n");
			} else {
				System.out.println("체인 없음.");
			}
			if (tags.size() != 0) {
				System.out.println("태그 등록 -> " + tags.size() + "개");
				for (TagDto tag : tags) {
					System.out.print(",");
				}
				System.out.println();
				for (TagDto tag : tags) {
					System.out.print(".");
					serviceVideo.addTagSingle(tag);
				}
				System.out.println(" 완료.\n");
			} else {
				System.out.println("태그 없음.");
			}
//			serviceVideo.cleanChain();
//			serviceVideo.cleanVideo();
		}
		System.out.println(channelId
				+ " 동영상 검색 / 등록 종료\n----------------------------------------\n----------------------------------------\n");

		return videos;
	}
}
