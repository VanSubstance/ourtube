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
import com.my.spring.domain.TopicStatDto;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.TopicDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.WordDto;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.CommentService;
import com.my.spring.service.KomoranService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping("")
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
	@Autowired
	private KomoranService serviceKomoran;
	@Autowired
	private WordService serviceWord;
	
	public void patchChannelByTopic(TopicDto topicDto, List<String> channelIdList) {
		System.out.println("------------------------ 채널 데이터 작업 시작  ------------------------");
		
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
		
		System.out.println("\t\t상위 채널 중 신규 id 리스트 -> 채널 기본 정보 & 채널 토픽 체인");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(channelIdList);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		List<ChainDto> chainChannel = new ArrayList<ChainDto>();
		
		System.out.print("\t\t\t채널 기본 정보 -> Database | o -> 등록 | ");
		for (ChannelDto channelDto : channelDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(channelDto.getId());
			newChain.setTopic(topicDto.getTopic());
			chainChannel.add(newChain);
			System.out.print("o");
			if (channelDto.getDescription().length() >= 2000) {
				channelDto.setDescription(channelDto.getDescription().substring(0, 2000));
			}
			serviceChannel.setChannelInfo(channelDto);
		}
		System.out.println(" 완료.");
		
		System.out.print("\t\t\t채널 토픽 체인 -> Database | o -> 신규 등록 | x -> 이미 존재 | ");
		System.out.print(chainChannel.size() + " 개\n\t\t\t");
		for (ChainDto chainDto : chainChannel) {
			if (serviceChannel.checkChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChain(chainDto);
			}
		}
		System.out.println(" 완료.");
		
		System.out.print("\tDatebase -> 금일 통계 정보 갱신을 위한 채널 id 리스트: ");
		channelIdList = serviceChannel.getChannelIdsForStatisticsByTopic(topicDto.getTopic());
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
		System.out.println("------------------------ 채널 데이터 작업 종료  ------------------------");
	}
	
	public void patchVideoByTopic(TopicDto topicDto, List<String> videoIdList) {
		System.out.println("------------------------ 비디오 데이터 작업 시작  ------------------------");
		
		System.out.print("\t\t신규 비디오 유효성 검사 | o -> 신규 등록 | x -> 이미 존재 | ");
		List<String> temp = new ArrayList<String>();
		for (String videoId: videoIdList) {
			if (serviceVideo.checkVideoInfo(videoId) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				temp.add(videoId);
			}
		}
		videoIdList = temp;
		System.out.println(" : 완료.");		
		
		
		System.out.println("\t\t상위 비디오 중 신규 id 리스트 -> 비디오 기본 정보 & 태그 & 비디오 토픽 체인");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(videoIdList);
		List<VideoDto> videoDtoList = (List<VideoDto>) data.get(0);
		List<ChainDto> chainVideo = new ArrayList<ChainDto>();
		
		System.out.print("\t\t\t비디오 기본 정보 -> Database | o -> 등록 | ");
		for (VideoDto videoDto : videoDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(videoDto.getId());
			newChain.setTopic(topicDto.getTopic());	
			chainVideo.add(newChain);
			System.out.print("o");
			if (videoDto.getDescription().length() >= 2000) {
				videoDto.setDescription(videoDto.getDescription().substring(0, 2000));
			}
			serviceVideo.setVideoInfo(videoDto);
		}
		System.out.println(" 완료.");
		
		List<TagDto> tagDtoList = (List<TagDto>) data.get(1);
		System.out.print("\t\t\t태그 -> Database | o -> 등록 | " + tagDtoList.size() +"개\n\t\t\t");
		
		for (TagDto tagDto : tagDtoList) {
			System.out.print("o");
			serviceVideo.setVideoTag(tagDto);
		}
		System.out.println(" 완료.");
		
		System.out.print("\t\t\t비디오 토픽 체인 -> Database | o -> 신규 등록 | x -> 이미 존재 | ");
		System.out.print(chainVideo.size() + " 개\n\t\t\t");
		for (ChainDto chainDto : chainVideo) {
			if (serviceVideo.checkChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceVideo.setChain(chainDto);
			}
		}
		System.out.println(" 완료.");

		System.out.print("\t\t\t댓글 -> Database | o -> 신규 등록 | x -> 이미 존재 | ");
		data = serviceYoutube.callCommentsByVideoId(videoIdList);
		List<CommentDto> commentDtoList = (List<CommentDto>) data.get(0);
		System.out.print(commentDtoList.size() + " 개\n\t\t\t");
		for (CommentDto commentDto : commentDtoList) {
			if (serviceComment.checkComment(commentDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceComment.setComment(commentDto);
			}
		}
		System.out.println(" 완료.");
		
		System.out.print("\tDatebase -> 금일 통계 정보 갱신을 위한 비디오 id 리스트: ");
		videoIdList = serviceVideo.getVideoIdsForStatisticsByTopic(topicDto.getTopic());
		System.out.println(videoIdList.size() + " 개");
		
		System.out.println("\t\t금일 통계 정보 갱신을 위한 비디오 id 리스트 -> Youtube API -> 금일 비디오 통계 정보");
		data = serviceYoutube.callVideoStatsByVideoId(videoIdList);
		System.out.println();
		List<VideoStatDto> videoStatList = (List<VideoStatDto>) data.get(0);
		System.out.print("\t\t\t금일 비디오 통계 정보 -> Database | o -> 등록 | ");
		for (VideoStatDto videoStatDto : videoStatList) {
			System.out.print("o");
			serviceVideo.setVideoStatistics(videoStatDto);
		}
		System.out.println(" 완료.");
		System.out.println("------------------------ 비디오 데이터 작업 종료  ------------------------");
	}
	
	public void patchVideoAndChannelByTopic(TopicDto topicDto) {
		System.out.println("------------------------ 데이터 작업 시작  ------------------------");
		System.out.print("\tYoutube API -> 상위 비디오 id 리스트");
		ArrayList<Object> data = serviceYoutube.callVideoIdsByTopic(topicDto);
		List<String> videoIdList = (List<String>) data.get(0);
		List<String> channelIdList = (List<String>) data.get(1);
		TopicStatDto topicStatDto = (TopicStatDto) data.get(2);
		System.out.print(videoIdList.size() + " 개, 채널 id 리스트: ");
		System.out.println(channelIdList.size() + " 개");

		System.out.print("토픽 당일 데이터: ");
		if (serviceBasic.checkTopicStat(topicStatDto.getTopic()) != 0) {
			System.out.println("x");
		} else {
			System.out.println("o");
			serviceBasic.setTopicStat(topicStatDto);
		}
		System.out.println(" :완료");
		
		patchChannelByTopic(topicDto, channelIdList);
		patchVideoByTopic(topicDto, videoIdList);
		
		System.out.println("------------------------ 데이터 작업 종료  ------------------------");
	}

	@RequestMapping(value = "/patch/all", method = RequestMethod.GET)
	public void patchDataByCtgr() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("토픽: " + topicDto.getTopic());
			patchVideoAndChannelByTopic(topicDto);
			System.out.println("토픽: " + topicDto.getTopic() + " : 데이터 갱신 완료.");
		}
	}

	public void parseTags() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("토픽: " + topicDto.getTopic());
			List<String> videoIdList = serviceVideo.getVideoIdsByTopic(topicDto.getTopic());
			for (String videoId : videoIdList) {
				System.out.print("\t비디오 id: " + videoId);
				if (serviceWord.checkCompleteForTag(videoId) == 0) {
					List<TagDto> tags = serviceVideo.getVideoTagByVideoId(videoId);
					System.out.print("\t\t태그 수: " + tags.size() + " : ");
					for (TagDto tag : tags) {
						System.out.print("|");
						List<WordDto> wordDtoList = serviceKomoran.parseTag(tag);
						for (WordDto wordDto : wordDtoList) {
							if (serviceWord.getWordFromTag(wordDto).size() != 0) {
								WordDto exist = serviceWord.getWordFromTag(wordDto).get(0);
								wordDto.setCount(wordDto.getCount() + exist.getCount());
								serviceWord.updateWordFromTag(wordDto);
							} else {
								serviceWord.setWordFromTag(wordDto);
							}
						}
					}
					System.out.println(" : 완료.");
				} else {
					System.out.println(" 이미 완료. ");
				}
			}
		}
	}

	public void parseChannels() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("토픽: " + topicDto.getTopic());
			List<String> channelIdList = serviceChannel.getChannelIdsByTopic(topicDto.getTopic());
			for (String channelId : channelIdList) {
				System.out.print("\t채널 id: " + channelId + " : ");
				if (serviceWord.checkCompleteForChannel(channelId) == 0) {
					String description = serviceChannel.getDescriptionByChannelId(channelId);
					if (description != null) {
						List<WordDto> wordDtoList = serviceKomoran.parseString(channelId, description);
						for (WordDto wordDto : wordDtoList) {
							System.out.print("|");
							if (serviceWord.getWordFromChannel(wordDto).size() != 0) {
								WordDto exist = serviceWord.getWordFromChannel(wordDto).get(0);
								wordDto.setCount(wordDto.getCount() + exist.getCount());
								serviceWord.updateWordFromChannel(wordDto);
							} else {
								serviceWord.setWordFromChannel(wordDto);
							}
						}
					}
					System.out.println(" : 완료.");
				} else {
					System.out.println(" 이미 완료. ");
				}
			}
		}
	}
	
	public void parseVideos() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("토픽: " + topicDto.getTopic());
			List<String> videoIdList = serviceVideo.getVideoIdsByTopic(topicDto.getTopic());
			for (String videoId : videoIdList) {
				System.out.print("\t비디오 id: " + videoId + " : ");
				if (serviceWord.checkCompleteForVideo(videoId) == 0) {
					String description = serviceVideo.getDescriptionByVideoId(videoId);
					if (description != null) {
						List<WordDto> wordDtoList = serviceKomoran.parseString(videoId, description);
						for (WordDto wordDto : wordDtoList) {
							System.out.print("|");
							if (serviceWord.getWordFromVideo(wordDto).size() != 0) {
								WordDto exist = serviceWord.getWordFromVideo(wordDto).get(0);
								wordDto.setCount(wordDto.getCount() + exist.getCount());
								serviceWord.updateWordFromVideo(wordDto);
							} else {
								serviceWord.setWordFromVideo(wordDto);
							}
						}
					}
					System.out.println(" : 완료.");
				} else {
					System.out.println(" 이미 완료. ");
				}
			}
		}
	}

	@RequestMapping(value = "/parse/all")
	public void parseWords() {
		System.out.println("----------------- 채널 키워드 추출 시작 -----------------");
		parseChannels();
		System.out.println("----------------- 채널 키워드 추출 시작 -----------------");
		System.out.println("----------------- 비디오 키워드 추출 시작 -----------------");
		parseVideos();
		System.out.println("----------------- 비디오 키워드 추출 시작 -----------------");
		System.out.println("----------------- 태그 키워드 추출 시작 -----------------");
		parseTags();
		System.out.println("----------------- 태그 키워드 추출 종료 -----------------");
	}
}
