package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.CommentDto;
import com.my.spring.domain.TestDto;
import com.my.spring.domain.TextVO;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.CommentService;
import com.my.spring.service.VideoService;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping("/basic")
public class BasicController {

	@Autowired
	private BasicService service;
	@Autowired
	private ChannelService serviceChannel;
	@Autowired
	private VideoService serviceVideo;
	@Autowired
	private YoutubeService serviceYoutube;
	@Autowired
	private CommentService serviceComment;

	@RequestMapping(value = "/checkCtgr/{ctgr}", method = RequestMethod.GET)
	public int checkCtgr(@PathVariable String ctgr) {
		return service.checkCtgr(ctgr);
	}

	@RequestMapping(value = "/checkCtgrRelation/{parent}/{title}", method = RequestMethod.GET)
	public int checkCtgrRelation(@PathVariable String parent, @PathVariable String title) {
		return service.checkCtgrRelation(parent, title);
	}

	@RequestMapping(value = "/searchCtgrsByParent/{parent}", method = RequestMethod.GET)
	public List<TestDto> searchCtgrsByParent(@PathVariable String parent) {
		return service.getCtgrBySearch(parent);
	}

	public List<String> getVideoIdsByCtgr(@PathVariable String ctgr) {
		System.out.println("카테고리: "+ ctgr);
		List<String> result = new ArrayList<String>();
		for (String channelId : serviceChannel.getChannelIdsByCtgr(ctgr)) {
			result.addAll(serviceVideo.getVideoIdsByChannelId(channelId));
		}
		return result;
	}
	
	public void getCommentsByVideoId(String videoId) {
		ArrayList<Object> result = serviceYoutube.getCommentsByVideoId(videoId);		
		System.out.print("\t\t\t댓글 등록 -> ");
		for (CommentDto comment : (List<CommentDto>) result.get(0)) {
			if (serviceComment.checkExistence(comment.getId()) == 0) {
				System.out.print("|");
				serviceComment.addCommentSingle(comment);
			}
		}
		System.out.println(" -> 완료.");
	}
	
	@RequestMapping(value = "/addCommentsByCtgr/{ctgr}", method = RequestMethod.GET)
	public void addCommentsByCtgr(@PathVariable String ctgr) {
		System.out.println("카테고리: "+ ctgr);
		List<String> videoIds = new ArrayList<String>();
		for (String channelId : serviceChannel.getChannelIdsByCtgr(ctgr)) {
			System.out.println("\t채널 id: " + channelId);
			for (String videoId : serviceVideo.getVideoIdsByChannelId(channelId)) {
				System.out.println("\t\t비디오 id: " + videoId);
				getCommentsByVideoId(videoId);
				System.out.println("\t\t비디오 id: " + videoId + " :완료.");
			}
			System.out.println("\t채널 id: " + channelId + " :완료.");
		}
		System.out.println("카테고리: "+ ctgr + " :완료.");
	}
	
	@RequestMapping(value = "/getCommentsTextByCtgr/{ctgr}", method = RequestMethod.GET)
	public List<TextVO> getCommentTextsByCtgr(@PathVariable String ctgr) {
		System.out.println("카테고리: "+ ctgr);
		List<TextVO> texts = new ArrayList<TextVO>();
		for (String channelId : serviceChannel.getChannelIdsByCtgr(ctgr)) {
			System.out.println("\t채널 id: " + channelId);
			for (String videoId : serviceVideo.getVideoIdsByChannelId(channelId)) {
				System.out.println("\t\t비디오 id: " + videoId);
				for (String text: serviceComment.getCommentTextsByVideoId(videoId)) {
					TextVO newText = new TextVO();
					newText.setCtgr(ctgr);
					newText.setText(text);
					texts.add(newText);
				}
			}
		}
		return texts;
	}

}
