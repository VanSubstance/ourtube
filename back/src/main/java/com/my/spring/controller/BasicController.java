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
import com.my.spring.domain.ResultCtgr;
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
		System.out.print("\tYoutube API -> ���� ä�� id ����Ʈ: ");
		List<String> channelIdList = (List<String>) serviceYoutube.callChannelIdsByCtgr(ctgr).get(0);
		System.out.println(channelIdList.size() + " ��");
		
		System.out.print("\t\t�ű� ä�� ��ȿ�� �˻� | o -> �ű� ��� | x -> �̹� ���� | ");
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
		System.out.println(" : �Ϸ�.");		
		
		System.out.println("\t\t���� ä�� id ����Ʈ -> ä�� �⺻ ���� & ü�� ����");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(channelIdList);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		
		System.out.print("\t\t\tä�� �⺻ ���� -> Database | o -> ��� | ");
		for (ChannelDto channelDto : channelDtoList) {
			System.out.print("o");
			serviceChannel.setChannelInfo(channelDto);
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\t\t\tü�� ���� -> Database | o -> �ű� ��� | x -> �̹� ���� | ");
		List<ChainDto> chainDtoList = (List<ChainDto>) data.get(1);
		System.out.print(chainDtoList.size() + " ��\n\t\t\t");
		for (ChainDto chainDto : chainDtoList) {
			if (serviceChannel.checkChannelChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChannelChain(chainDto);
			}
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\tDatebase -> ���� ��� ���� ������ ���� ä�� id ����Ʈ: ");
		channelIdList = serviceChannel.getChannelIdsForStatisticsByCtgr(ctgr);
		System.out.println(channelIdList.size() + " ��");
		System.out.println("\t\t���� ��� ���� ������ ���� ä�� id ����Ʈ -> Youtube API -> ���� ä�� ��� ����");
		data = serviceYoutube.callChannelStatsByChannelId(channelIdList);
		System.out.println();
		List<ChannelStatDto> channelStatList = (List<ChannelStatDto>) data.get(0);
		System.out.print("\t\t\t���� ä�� ��� ���� -> Database | o -> ��� | ");
		for (ChannelStatDto channelStatDto : channelStatList) {
			System.out.print("o");
			serviceChannel.setChannelStatistics(channelStatDto);
		}
		System.out.println(" �Ϸ�.");
	}

	public void patchVideoByCtgr(String ctgr) {
		System.out.print("\tDatebase -> ä�� id ����Ʈ: ");
		List<String> channelIdList = serviceChannel.getChannelIdsByCtgr(ctgr);
		System.out.println(channelIdList.size() + " ��");
		
		System.out.println("\t\tä�� id ����Ʈ -> Youtube API -> ���� id ����Ʈ: ");
		List<String> videoIdList = new ArrayList<String>();
		for (String channelId: channelIdList) {
			videoIdList.addAll((List<String>) serviceYoutube.callVideoIdsByChannelId(channelId).get(0));
			System.out.println("\t\t-> " + videoIdList.size() + " ��");
		}
		System.out.print("\t\t\t�ű� ���� ��ȿ�� �˻� | o -> �ű� ��� | x -> �̹� ���� | ");
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
		System.out.println(" �Ϸ�.");
		
		System.out.println("\t\t���� id ����Ʈ -> Youtube API -> ���� �⺻ ���� & ü�� ���� & �±� ����: ");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(videoIdList);
		List<VideoDto> videoDtoList  = (List<VideoDto>) data.get(0);
		System.out.println();
		System.out.print("\t\t\t���� �⺻ ���� -> Database | o -> ��� | ");
		for (VideoDto videoDto : videoDtoList) {
			System.out.print("o");
			serviceVideo.setVideoInfo(videoDto);
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\t\t\tü�� ���� -> Database | o -> �ű� ��� | x -> �̹� ���� | ");
		List<ChainDto> chainDtoList = (List<ChainDto>) data.get(1);
		for (ChainDto chainDto : chainDtoList) {
			if (serviceChannel.checkChannelChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChannelChain(chainDto);
			}
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\t\t\t�±� ���� -> Database | o -> ��� | ");
		List<TagDto> tagDtoList = (List<TagDto>) data.get(2);
		for (TagDto tagDto : tagDtoList) {
			System.out.print("o");
			serviceVideo.setVideoTag(tagDto);
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\tDatabase -> ���� ��� ���� ������ ���� ���� id ����Ʈ: ");
		videoIdList = serviceVideo.getVideoIdsForStatisticsByCtgr(ctgr);
		System.out.println(videoIdList.size() + " ��");
		System.out.println("\t\t���� ��� ���� ������ ���� ���� id ����Ʈ -> Youtube API -> ���� ��� ����");
		data = serviceYoutube.callVideoStatsByVideoId(videoIdList);
		System.out.println();
		List<VideoStatDto> videoStatDtoList = (List<VideoStatDto>) data.get(0);
		System.out.print("\t\t\t���� ��� ���� -> Database: ");
		for (VideoStatDto videoStatDto : videoStatDtoList) {
			System.out.print("o");
			serviceVideo.setVideoStatistics(videoStatDto);
		}
		System.out.println(" �Ϸ�.");
	}

	public void patchCommentsByCtgr(String ctgr) {
		System.out.print("\tDatabase -> ��� ���� �߰��� ���� ���� id ����Ʈ: ");
		List<String> videoIdList = serviceVideo.getVideoIdsForCommentByCtgr(ctgr);
		System.out.println(videoIdList.size() + " ��");
		
		System.out.println("\t\t��� ���� �߰��� ���� ���� id ����Ʈ -> Youtube API -> ��� ����Ʈ");
		ArrayList<Object> data = serviceYoutube.callCommentsByVideoId(videoIdList);
		List<CommentDto> commentDtoList = (List<CommentDto>) data.get(0);
		
		System.out.print("\t\t\t��� ����Ʈ -> Database | o -> ��� | ");
		for (CommentDto commentDto : commentDtoList) {
			System.out.print("o");
			serviceComment.setComment(commentDto);
		}
		System.out.println(" �Ϸ�.");
	}

	@RequestMapping(value = "/ctgr/channel/{ctgr}", method = RequestMethod.GET)
	public void patchChannelByCtgrApi(@PathVariable String ctgr) {
		System.out.println("ī�װ�: " + ctgr);
		patchChannelByCtgr(ctgr);
		System.out.println("ī�װ�: " + ctgr + " : ä�� ������ ���� �Ϸ�.");
	}

	@RequestMapping(value = "/ctgr/video/{ctgr}", method = RequestMethod.GET)
	public void patchVideosByCtgrApi(@PathVariable String ctgr) {
		System.out.println("ī�װ�: " + ctgr);
		patchVideoByCtgr(ctgr);
		System.out.println("ī�װ�: " + ctgr + " : ���� ������ ���� �Ϸ�.");
	}

	@RequestMapping(value = "/ctgr/comment/{ctgr}", method = RequestMethod.GET)
	public void patchCommentsByCtgrApi(@PathVariable String ctgr) {
		System.out.println("ī�װ�: " + ctgr);
		patchCommentsByCtgr(ctgr);
		System.out.println("ī�װ�: " + ctgr + " : ��� ������ ���� �Ϸ�.");
	}
	
	@RequestMapping(value = "/ctgr/all/{ctgr}", method = RequestMethod.GET)
	public void patchDataByCtgr(@PathVariable String ctgr) {
		System.out.println("ī�װ�: " + ctgr);
		patchChannelByCtgr(ctgr);
		patchVideoByCtgr(ctgr);
		patchCommentsByCtgr(ctgr);
		System.out.println("ī�װ�: " + ctgr + " : ������ ���� �Ϸ�.");
	}
	
	@RequestMapping(value = "/ctgr/daily", method = RequestMethod.GET)
	public void patchDailyByCtgr() {
		List<String> ctgrs = serviceBasic.getCtgrsForPatch();
		System.out.println("ī�װ� �˻��� ����");
		for (String ctgr: ctgrs) {
			System.out.print("\tī�װ� " + ctgr + " : ");
			ResultCtgr resultCtgr = (ResultCtgr) serviceYoutube.callResultCtgrBtCtgr(ctgr).get(0);
			if (resultCtgr != null) {
				System.out.print("o");
				serviceBasic.setResultCtgr(resultCtgr);
			} else {
				System.out.print("x");
			}
			System.out.println(" :����");
		}
		System.out.println("�Ϸ�");
		
		ctgrs = serviceBasic.getCtgrs();
		System.out.println("ä�� ��� ���� ����");
		for (String ctgr: ctgrs) {
			System.out.println("\tī�װ� : " + ctgr + " :����");
			List<String> channels = serviceChannel.getChannelIdsForStatisticsByCtgr(ctgr);
			System.out.println("\t\tä�� ����: " + channels.size());
			List<ChannelStatDto> channelStats = (List<ChannelStatDto>) serviceYoutube.callChannelStatsByChannelId(channels).get(0);
			for (ChannelStatDto item : channelStats) {
				serviceChannel.setChannelStatistics(item);
			}
			System.out.println("\tī�װ�: " + ctgr + " :�Ϸ�");
		}
		System.out.println("�Ϸ�");
		
		System.out.println("���� ��� ���� ����");
		for (String ctgr: ctgrs) {
			System.out.println("\tī�װ� : " + ctgr + " :����");
			List<String> videos = serviceVideo.getVideoIdsForStatisticsByCtgr(ctgr);
			System.out.println("\t\t���� ����: " + videos.size());
			List<VideoStatDto> videoStats = (List<VideoStatDto>) serviceYoutube.callVideoStatsByVideoId(videos).get(0);
			for(VideoStatDto item : videoStats) {
				serviceVideo.setVideoStatistics(item);
			}
			System.out.println("\tī�װ�: " + ctgr + " :�Ϸ�");
		}
		System.out.println("�Ϸ�");
	}

}
