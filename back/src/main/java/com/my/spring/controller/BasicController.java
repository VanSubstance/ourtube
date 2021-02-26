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
		System.out.println("------------------------ ä�� ������ �۾� ����  ------------------------");
		
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
		
		System.out.println("\t\t���� ä�� �� �ű� id ����Ʈ -> ä�� �⺻ ���� & ä�� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(channelIdList);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		List<ChainDto> chainChannel = new ArrayList<ChainDto>();
		
		System.out.print("\t\t\tä�� �⺻ ���� -> Database | o -> ��� | ");
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
		System.out.println(" �Ϸ�.");
		
		System.out.print("\t\t\tä�� ���� ü�� -> Database | o -> �ű� ��� | x -> �̹� ���� | ");
		System.out.print(chainChannel.size() + " ��\n\t\t\t");
		for (ChainDto chainDto : chainChannel) {
			if (serviceChannel.checkChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceChannel.setChain(chainDto);
			}
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\tDatebase -> ���� ��� ���� ������ ���� ä�� id ����Ʈ: ");
		channelIdList = serviceChannel.getChannelIdsForStatisticsByTopic(topicDto.getTopic());
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
		System.out.println("------------------------ ä�� ������ �۾� ����  ------------------------");
	}
	
	public void patchVideoByTopic(TopicDto topicDto, List<String> videoIdList) {
		System.out.println("------------------------ ���� ������ �۾� ����  ------------------------");
		
		System.out.print("\t\t�ű� ���� ��ȿ�� �˻� | o -> �ű� ��� | x -> �̹� ���� | ");
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
		System.out.println(" : �Ϸ�.");		
		
		
		System.out.println("\t\t���� ���� �� �ű� id ����Ʈ -> ���� �⺻ ���� & �±� & ���� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(videoIdList);
		List<VideoDto> videoDtoList = (List<VideoDto>) data.get(0);
		List<ChainDto> chainVideo = new ArrayList<ChainDto>();
		
		System.out.print("\t\t\t���� �⺻ ���� -> Database | o -> ��� | ");
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
		System.out.println(" �Ϸ�.");
		
		List<TagDto> tagDtoList = (List<TagDto>) data.get(1);
		System.out.print("\t\t\t�±� -> Database | o -> ��� | " + tagDtoList.size() +"��\n\t\t\t");
		
		for (TagDto tagDto : tagDtoList) {
			System.out.print("o");
			serviceVideo.setVideoTag(tagDto);
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\t\t\t���� ���� ü�� -> Database | o -> �ű� ��� | x -> �̹� ���� | ");
		System.out.print(chainVideo.size() + " ��\n\t\t\t");
		for (ChainDto chainDto : chainVideo) {
			if (serviceVideo.checkChain(chainDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceVideo.setChain(chainDto);
			}
		}
		System.out.println(" �Ϸ�.");

		System.out.print("\t\t\t��� -> Database | o -> �ű� ��� | x -> �̹� ���� | ");
		data = serviceYoutube.callCommentsByVideoId(videoIdList);
		List<CommentDto> commentDtoList = (List<CommentDto>) data.get(0);
		System.out.print(commentDtoList.size() + " ��\n\t\t\t");
		for (CommentDto commentDto : commentDtoList) {
			if (serviceComment.checkComment(commentDto) != 0) {
				System.out.print("x");
			} else {
				System.out.print("o");
				serviceComment.setComment(commentDto);
			}
		}
		System.out.println(" �Ϸ�.");
		
		System.out.print("\tDatebase -> ���� ��� ���� ������ ���� ���� id ����Ʈ: ");
		videoIdList = serviceVideo.getVideoIdsForStatisticsByTopic(topicDto.getTopic());
		System.out.println(videoIdList.size() + " ��");
		
		System.out.println("\t\t���� ��� ���� ������ ���� ���� id ����Ʈ -> Youtube API -> ���� ���� ��� ����");
		data = serviceYoutube.callVideoStatsByVideoId(videoIdList);
		System.out.println();
		List<VideoStatDto> videoStatList = (List<VideoStatDto>) data.get(0);
		System.out.print("\t\t\t���� ���� ��� ���� -> Database | o -> ��� | ");
		for (VideoStatDto videoStatDto : videoStatList) {
			System.out.print("o");
			serviceVideo.setVideoStatistics(videoStatDto);
		}
		System.out.println(" �Ϸ�.");
		System.out.println("------------------------ ���� ������ �۾� ����  ------------------------");
	}
	
	public void patchVideoAndChannelByTopic(TopicDto topicDto) {
		System.out.println("------------------------ ������ �۾� ����  ------------------------");
		System.out.print("\tYoutube API -> ���� ���� id ����Ʈ");
		ArrayList<Object> data = serviceYoutube.callVideoIdsByTopic(topicDto);
		List<String> videoIdList = (List<String>) data.get(0);
		List<String> channelIdList = (List<String>) data.get(1);
		TopicStatDto topicStatDto = (TopicStatDto) data.get(2);
		System.out.print(videoIdList.size() + " ��, ä�� id ����Ʈ: ");
		System.out.println(channelIdList.size() + " ��");

		System.out.print("���� ���� ������: ");
		if (serviceBasic.checkTopicStat(topicStatDto.getTopic()) != 0) {
			System.out.println("x");
		} else {
			System.out.println("o");
			serviceBasic.setTopicStat(topicStatDto);
		}
		System.out.println(" :�Ϸ�");
		
		patchChannelByTopic(topicDto, channelIdList);
		patchVideoByTopic(topicDto, videoIdList);
		
		System.out.println("------------------------ ������ �۾� ����  ------------------------");
	}

	@RequestMapping(value = "/patch/all", method = RequestMethod.GET)
	public void patchDataByCtgr() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("����: " + topicDto.getTopic());
			patchVideoAndChannelByTopic(topicDto);
			System.out.println("����: " + topicDto.getTopic() + " : ������ ���� �Ϸ�.");
		}
	}

	public void parseTags() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("����: " + topicDto.getTopic());
			List<String> videoIdList = serviceVideo.getVideoIdsByTopic(topicDto.getTopic());
			for (String videoId : videoIdList) {
				System.out.print("\t���� id: " + videoId);
				if (serviceWord.checkCompleteForTag(videoId) == 0) {
					List<TagDto> tags = serviceVideo.getVideoTagByVideoId(videoId);
					System.out.print("\t\t�±� ��: " + tags.size() + " : ");
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
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
	}

	public void parseChannels() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("����: " + topicDto.getTopic());
			List<String> channelIdList = serviceChannel.getChannelIdsByTopic(topicDto.getTopic());
			for (String channelId : channelIdList) {
				System.out.print("\tä�� id: " + channelId + " : ");
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
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
	}
	
	public void parseVideos() {
		List<TopicDto> topicDtoList = serviceBasic.getTopics();
		for (TopicDto topicDto : topicDtoList) {
			System.out.println("����: " + topicDto.getTopic());
			List<String> videoIdList = serviceVideo.getVideoIdsByTopic(topicDto.getTopic());
			for (String videoId : videoIdList) {
				System.out.print("\t���� id: " + videoId + " : ");
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
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
	}

	@RequestMapping(value = "/parse/all")
	public void parseWords() {
		System.out.println("----------------- ä�� Ű���� ���� ���� -----------------");
		parseChannels();
		System.out.println("----------------- ä�� Ű���� ���� ���� -----------------");
		System.out.println("----------------- ���� Ű���� ���� ���� -----------------");
		parseVideos();
		System.out.println("----------------- ���� Ű���� ���� ���� -----------------");
		System.out.println("----------------- �±� Ű���� ���� ���� -----------------");
		parseTags();
		System.out.println("----------------- �±� Ű���� ���� ���� -----------------");
	}
}
