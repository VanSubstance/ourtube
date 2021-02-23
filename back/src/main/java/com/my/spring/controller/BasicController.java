package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.domain.CommentDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.CommentService;
import com.my.spring.service.VideoService;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping("/patch")
public class BasicController {

	@Autowired
	private BasicService serviceBasic;
	@Autowired
	private ChannelService serviceChannel;
	@Autowired
	private VideoService serviceVideo;
	@Autowired
	private YoutubeService serviceYoutube;
	@Autowired
	private CommentService serviceComment;
	
	public void patchChannelByCtgr(String ctgr) {
		System.out.print("\tYoutube API -> 상위 채널 id 리스트: ");
		List<String> channelIdList = (List<String>) serviceYoutube.callChannelIdsByCtgr(ctgr).get(0);
		System.out.println(channelIdList.size() + " 개");
		
		System.out.print("\t\t신규 채널 유효성 검사 | o -> 신규 등록 | x -> 이미 존재 | ");
		List<String> temp = new ArrayList<String>();
		for (String channelId: channelIdList) {
			if (serviceChannel.checkChannelInfo(channelId) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				temp.add(channelId);
			}
		}
		channelIdList = temp;
		System.out.println(" : 완료.");		
		
		System.out.println("\t\t상위 채널 id 리스트 -> 채널 기본 정보 & 체인 정보");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(channelIdList);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		
		System.out.print("\t\t\t채널 기본 정보 -> Database | o -> 등록 | ");
		for (ChannelDto channelDto : channelDtoList) {
			System.out.print("o");
			serviceChannel.setChannelInfo(channelDto);
		}
		System.out.println(" 완료.");
		
		System.out.print("\t\t\t체인 정보 -> Database | o -> 신규 등록 | x -> 이미 존재 | ");
		List<ChainDto> chainDtoList = (List<ChainDto>) data.get(1);
		System.out.print(chainDtoList.size() + " 개\n\t\t\t");
		for (ChainDto chainDto : chainDtoList) {
			if (serviceChannel.checkChannelChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChannelChain(chainDto);
			}
		}
		System.out.println(" 완료.");
		
		System.out.print("\tDatebase -> 금일 통계 정보 갱신을 위한 채널 id 리스트: ");
		channelIdList = serviceChannel.getChannelIdsForStatisticsByCtgr(ctgr);
		System.out.println(channelIdList.size() + " 개");
		System.out.println("\t\t금일 통계 정보 갱신을 위한 채널 id 리스트 -> Youtube API -> 금일 채널 통계 정보");
		data = serviceYoutube.callChannelStatsByChannelId(channelIdList);
		System.out.println();
		List<ChannelStatDto> channelStatList = (List<ChannelStatDto>) data.get(0);
		System.out.print("\t\t\t금일 채널 통계 정보 -> Database | o -> 등록 | ");
		for (ChannelStatDto channelStatDto : channelStatList) {
			System.out.print("o");
			serviceChannel.setChannelStatistics(channelStatDto);
		}
		System.out.println(" 완료.");
	}
	
	public void patchVideoByCtgr(String ctgr) {
		System.out.print("\tDatebase -> 채널 id 리스트: ");
		List<String> channelIdList = serviceChannel.getChannelIdsByCtgr(ctgr);
		System.out.println(channelIdList.size() + " 개");
		
		System.out.println("\t\t채널 id 리스트 -> Youtube API -> 비디오 id 리스트: ");
		List<String> videoIdList = new ArrayList<String>();
		for (String channelId: channelIdList) {
			videoIdList.addAll((List<String>) serviceYoutube.callVideoIdsByChannelId(channelId).get(0));
			System.out.println("\t\t-> " + videoIdList.size() + " 개");
		}
		System.out.print("\t\t\t신규 비디오 유효성 검사 | o -> 신규 등록 | x -> 이미 존재 | ");
		List<String> temp = new ArrayList<String>();
		for (String videoId : videoIdList) {
			if (serviceVideo.checkVideoInfo(videoId) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				temp.add(videoId);
			}
		}
		videoIdList = temp;
		System.out.println(" 완료.");
		
		System.out.println("\t\t비디오 id 리스트 -> Youtube API -> 비디오 기본 정보 & 체인 정보 & 태그 정보: ");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(videoIdList);
		List<VideoDto> videoDtoList  = (List<VideoDto>) data.get(0);
		System.out.println();
		System.out.print("\t\t\t비디오 기본 정보 -> Database | o -> 등록 | ");
		for (VideoDto videoDto : videoDtoList) {
			System.out.print("o");
			serviceVideo.setVideoInfo(videoDto);
		}
		System.out.println(" 완료.");
		
		System.out.print("\t\t\t체인 정보 -> Database | o -> 신규 등록 | x -> 이미 존재 | ");
		List<ChainDto> chainDtoList = (List<ChainDto>) data.get(1);
		for (ChainDto chainDto : chainDtoList) {
			if (serviceChannel.checkChannelChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChannelChain(chainDto);
			}
		}
		System.out.println(" 완료.");
		
		System.out.print("\t\t\t태그 정보 -> Database | o -> 등록 | ");
		List<TagDto> tagDtoList = (List<TagDto>) data.get(2);
		for (TagDto tagDto : tagDtoList) {
			System.out.print("o");
			serviceVideo.setVideoTag(tagDto);
		}
		System.out.println(" 완료.");
		
		System.out.print("\tDatabase -> 금일 통계 정보 갱신을 위한 비디오 id 리스트: ");
		videoIdList = serviceVideo.getVideoIdsForStatisticsByCtgr(ctgr);
		System.out.println(videoIdList.size() + " 개");
		System.out.println("\t\t금일 통계 정보 갱신을 위한 비디오 id 리스트 -> Youtube API -> 금일 통계 정보");
		data = serviceYoutube.callVideoStatsByVideoId(videoIdList);
		System.out.println();
		List<VideoStatDto> videoStatDtoList = (List<VideoStatDto>) data.get(0);
		System.out.print("\t\t\t금일 통계 정보 -> Database: ");
		for (VideoStatDto videoStatDto : videoStatDtoList) {
			System.out.print("o");
			serviceVideo.setVideoStatistics(videoStatDto);
		}
		System.out.println(" 완료.");
	}
	public void patchCommentsByCtgr(String ctgr) {
		System.out.println("\tDatabase -> 댓글 정보 추가를 위한 비디오 id 리스트");
		List<String> videoIdList = serviceVideo.getVideoIdsForCommentByCtgr(ctgr);
		
		System.out.println("\t\t댓글 정보 추가를 위한 비디오 id 리스트 -> Youtube API -> 댓글 리스트");
		ArrayList<Object> data = serviceYoutube.callCommentsByVideoId(videoIdList);
		List<CommentDto> commentDtoList = (List<CommentDto>) data.get(0);
		System.out.print("\t\t\t댓글 리스트 -> Database | o -> 등록 | ");
		for (CommentDto commentDto : commentDtoList) {
			System.out.print("o");
			serviceComment.setComment(commentDto);
		}
		System.out.println(" 완료.");
	}
	
	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public void patchDataByCtgr(@PathVariable String ctgr) {
		System.out.println("카테고리: " + ctgr);
		patchChannelByCtgr(ctgr);
		patchVideoByCtgr(ctgr);
		patchCommentsByCtgr(ctgr);
		System.out.println("카테고리: " + ctgr + " : 데이터 갱신 완료.");
	}

}
