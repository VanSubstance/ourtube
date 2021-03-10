package com.my.spring.controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.ChainDto;
import com.my.spring.domain.ChannelDto;
import com.my.spring.domain.ChannelStatDto;
import com.my.spring.domain.CommentDto;
import com.my.spring.domain.IdComplete;
import com.my.spring.domain.TagDto;
import com.my.spring.domain.VideoDto;
import com.my.spring.domain.VideoStatDto;
import com.my.spring.domain.WordDto;
import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.domain.statistics.GameStatistic;
import com.my.spring.service.BasicService;
import com.my.spring.service.ChannelService;
import com.my.spring.service.CommentService;
import com.my.spring.service.CrawlerService;
import com.my.spring.service.VideoService;
import com.my.spring.service.WordService;
import com.my.spring.service.YoutubeService;

@EnableScheduling
@CrossOrigin("*")
@RestController
@RequestMapping("/patch")
public class PatchController {

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
	private WordService serviceWord;
	@Autowired
	private CrawlerService serviceCrawler;

	@Scheduled(cron = "1 0 0 * * *")
	@RequestMapping(value = "/crawler")
	public void patchGameFromYoutube() {
		System.out.println("------------------------------- ���� ũ�Ѹ� ���� -------------------------------");
		ArrayList<Object> data = serviceCrawler.crawlGame();
		List<Game> games = (List<Game>) data.get(0);
		List<GameTopic> gameTopics = (List<GameTopic>) data.get(1);
		List<String> topics = (List<String>) data.get(2);
		for (Game game : games) {
			System.out.println("���� ���.");
			try {
				serviceBasic.setGame(game);
			} catch (DataIntegrityViolationException e) {
				System.out.println("�̹� ����");
			}
		}
		for (GameTopic gameTopic : gameTopics) {
			System.out.println("���� ���� ü�� ���.");
			try {
				serviceBasic.setGameTopic(gameTopic);
			} catch (DataIntegrityViolationException e) {
				System.out.println("�̹� ����");
			}
		}
		for (String topic : topics) {
			System.out.println("���� ���.");
			try {
				serviceBasic.setTopic(topic);
			} catch (DataIntegrityViolationException e) {
				System.out.println("�̹� ����");
			}
		}
		System.out.println("------------------------------- ���� ũ�Ѹ� ���� -------------------------------");
	}
	
	public void patchChannelByGame(String game, List<String> channelIdList) {
		System.out.println("------------------------ ä�� ������ �۾� ����  ------------------------");

		System.out.print("\t\t�ű� ä�� ��ȿ�� �˻� | o -> �ű� ��� | x -> �̹� ���� | ");
		List<String> temp = new ArrayList<String>();
		List<String> existed = new ArrayList<String>();
		for (String channelId : channelIdList) {
			if (serviceChannel.checkChannelInfo(channelId) != 0) {
				System.out.print("x");
				existed.add(channelId);
			} else {
				System.out.print("o");
				temp.add(channelId);
			}
		}
		channelIdList = temp;
		System.out.println(" : �Ϸ�.");
		List<ChannelDto> existedInfo = new ArrayList<ChannelDto>();
		if (existed.size() != 0) {
			existedInfo = serviceChannel.getChannelInfoById(existed);
		}

		System.out.println("\t\t���� ä�� �� �ű� id ����Ʈ -> ä�� �⺻ ���� & ä�� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(channelIdList);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		List<ChainDto> chainChannel = new ArrayList<ChainDto>();

		System.out.print("\t\t\tä�� �⺻ ���� -> Database | o -> ��� | ");
		for (ChannelDto channelDto : channelDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(channelDto.getId());
			newChain.setGame(game);
			chainChannel.add(newChain);
			System.out.print("o");
			if (channelDto.getDescription().length() >= 2000) {
				channelDto.setDescription(channelDto.getDescription().substring(0, 2000));
			}
			if (serviceChannel.checkChannelInfo(channelDto.getId()) == 0) {
				serviceChannel.setChannelInfo(channelDto);
			}
		}
		for (ChannelDto channelDto : existedInfo) {
			ChainDto newChain = new ChainDto();
			newChain.setId(channelDto.getId());
			newChain.setGame(game);
			chainChannel.add(newChain);
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
		channelIdList = serviceChannel.getChannelIdsForStatisticsByGame(game);
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

	public void patchVideoByGame(String game, List<String> videoIdList) {
		System.out.println("------------------------ ���� ������ �۾� ����  ------------------------");

		System.out.print("\t\t�ű� ���� ��ȿ�� �˻� | o -> �ű� ��� | x -> �̹� ���� | ");
		List<String> temp = new ArrayList<String>();
		List<String> existed = new ArrayList<String>();
		for (String videoId : videoIdList) {
			if (serviceVideo.checkVideoInfo(videoId) != 0) {
				System.out.print("x");
				existed.add(videoId);
			} else {
				System.out.print("o");
				temp.add(videoId);
			}
		}
		videoIdList = temp;
		System.out.println(" : �Ϸ�.");
		List<VideoDto> existedInfo = new ArrayList<VideoDto>();
		if (existed.size() != 0) {
			existedInfo = serviceVideo.getVideoInfoById(existed);
		}
		System.out.println("\t\t���� ���� �� �ű� id ����Ʈ -> ���� �⺻ ���� & �±� & ���� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(videoIdList);
		List<VideoDto> videoDtoList = (List<VideoDto>) data.get(0);
		List<ChainDto> chainVideo = new ArrayList<ChainDto>();

		System.out.print("\t\t\t���� �⺻ ���� -> Database | o -> ��� | ");
		for (VideoDto videoDto : videoDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(videoDto.getId());
			newChain.setGame(game);
			chainVideo.add(newChain);
			System.out.print("o");
			if (videoDto.getDescription().length() >= 2000) {
				videoDto.setDescription(videoDto.getDescription().substring(0, 2000));
			}
			if (serviceVideo.checkVideoInfo(videoDto.getId()) == 0) {
				serviceVideo.setVideoInfo(videoDto);
			}
		}
		for (VideoDto videoDto : existedInfo) {
			ChainDto newChain = new ChainDto();
			newChain.setId(videoDto.getId());
			newChain.setGame(game);
			chainVideo.add(newChain);
		}
		System.out.println(" �Ϸ�.");

		List<TagDto> tagDtoList = (List<TagDto>) data.get(1);
		System.out.print("\t\t\t�±� -> Database | o -> ��� | " + tagDtoList.size() + "��\n\t\t\t");

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
				if (commentDto.getText().length() >= 2000) {
					commentDto.setText(commentDto.getText().substring(0, 2000));
				}
				serviceComment.setComment(commentDto);
			}
		}
		System.out.println(" �Ϸ�.");

		System.out.print("\tDatebase -> ���� ��� ���� ������ ���� ���� id ����Ʈ: ");
		videoIdList = serviceVideo.getVideoIdsForStatisticsByGame(game);
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

	public void patchVideoAndChannelByGame(String game) {
		System.out.println("------------------------ ������ �۾� ����  ------------------------");
		System.out.print("\tYoutube API -> ���� ���� id ����Ʈ");
		ArrayList<Object> data = serviceYoutube.callVideoIdsByGame(game);
		List<String> videoIdList = (List<String>) data.get(0);
		List<String> channelIdList = (List<String>) data.get(1);
		GameStatistic gameStat = (GameStatistic) data.get(2);
		System.out.print(videoIdList.size() + " ��, ä�� id ����Ʈ: ");
		System.out.println(channelIdList.size() + " ��");

		System.out.print("���� ���� ������: ");
		if (serviceBasic.checkGameStat(game) != 0) {
			System.out.print("x");
		} else {
			System.out.print("o");
			serviceBasic.setGameStat(gameStat);
		}
		System.out.println(" :�Ϸ�");

		patchChannelByGame(game, channelIdList);
		patchVideoByGame(game, videoIdList);

		System.out.println("------------------------ ������ �۾� ����  ------------------------");
	}
	
	public List<String> parseTags() {
		List<String> result = new ArrayList<String>();
		List<String> filter = serviceBasic.getNounFilter();
		List<String> gameList = serviceBasic.getGameTop();
		for (String game : gameList) {
			System.out.println("����: " + game);
			List<String> videoIdList = serviceVideo.getVideoIdsByGame(game);
			for (String videoId : videoIdList) {
				System.out.print("\t���� id: " + videoId);
				if (serviceWord.checkCompleteForTag(videoId) == 0) {
					List<TagDto> tags = serviceVideo.getVideoTagByVideoId(videoId);
					System.out.print("\t\t�±� ��: " + tags.size() + " : ");
					for (TagDto tag : tags) {
						String desc = tag.getTag();
						if (desc != null) {
							for (String target : filter) {
								desc = desc.replace(target, " ");
							}
							desc = desc.replace("\n", " ");
							desc = desc.replace("\r", " ");
							List<String> keywords = new ArrayList(Arrays.asList(desc.split(" ")));
							List<String> trashs = new ArrayList<String>();
							for (String keyword : keywords) {
								if (keyword.length() <= 1) {
									trashs.add(keyword);
								}
							}
							for (String trash : trashs) {
								keywords.remove(trash);
							}
							System.out.print(keywords.size());
							for (String keyword : keywords) {
								if (keyword.length() < 200 && keyword.length() != keyword.getBytes().length) {
									if (serviceWord.checkWordUnique(keyword) == 0) {
										serviceWord.setWordUnique(keyword);
									}
									WordDto newWord = new WordDto();
									newWord.setId(videoId);
									newWord.setWord(keyword);
									newWord.setCount(1);
									if (serviceWord.getWordFromTag(newWord).size() != 0) {
										WordDto exist = serviceWord.getWordFromTag(newWord).get(0);
										newWord.setCount(newWord.getCount() + exist.getCount());
										serviceWord.updateWordFromTag(newWord);
									} else {
										serviceWord.setWordFromTag(newWord);
									}
								}
							}
						}
					}
					result.add(videoId);
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
		return result;
	}

	public List<String> parseChannels() {
		List<String> result = new ArrayList<String>();
		List<String> filter = serviceBasic.getNounFilter();
		List<String> gameList = serviceBasic.getGameTop();
		for (String game : gameList) {
			System.out.println("����: " + game);
			List<String> channelIdList = serviceChannel.getChannelIdsByGame(game);
			for (String channelId : channelIdList) {
				System.out.print("\tä�� id: " + channelId + " : ");
				if (serviceWord.checkCompleteForChannel(channelId) == 0) {
					String desc = serviceChannel.getDescriptionByChannelId(channelId);
					if (desc != null) {
						for (String target : filter) {
							desc = desc.replace(target, " ");
						}
						desc = desc.replace("\n", " ");
						desc = desc.replace("\r", " ");
						List<String> keywords = new ArrayList(Arrays.asList(desc.split(" ")));
						List<String> trashs = new ArrayList<String>();
						for (String keyword : keywords) {
							if (keyword.length() <= 1) {
								trashs.add(keyword);
							}
						}
						for (String trash : trashs) {
							keywords.remove(trash);
						}
						System.out.print(keywords.size());
						for (String keyword : keywords) {
							if (keyword.length() < 200 && keyword.length() != keyword.getBytes().length) {
								if (serviceWord.checkWordUnique(keyword) == 0) {
									serviceWord.setWordUnique(keyword);
								}
								WordDto newWord = new WordDto();
								newWord.setId(channelId);
								newWord.setWord(keyword);
								newWord.setCount(1);
								if (serviceWord.getWordFromChannel(newWord).size() != 0) {
									WordDto exist = serviceWord.getWordFromChannel(newWord).get(0);
									newWord.setCount(newWord.getCount() + exist.getCount());
									serviceWord.updateWordFromChannel(newWord);
								} else {
									serviceWord.setWordFromChannel(newWord);
								}
							}
						}
					}
					result.add(channelId);
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
		return result;
	}

	public List<String> parseVideos() {
		List<String> result = new ArrayList<String>();
		List<String> filter = serviceBasic.getNounFilter();
		List<String> gameList = serviceBasic.getGameTop();
		for (String game : gameList) {
			System.out.println("����: " + game);
			List<String> videoIdList = serviceVideo.getVideoIdsByGame(game);
			for (String videoId : videoIdList) {
				System.out.print("\t���� id: " + videoId + " : ");
				if (serviceWord.checkCompleteForVideo(videoId) == 0) {
					String desc = serviceVideo.getDescriptionByVideoId(videoId);
					if (desc != null) {
						for (String target : filter) {
							desc = desc.replace(target, " ");
						}
						desc = desc.replace("\n", " ");
						desc = desc.replace("\r", " ");
						List<String> keywords = new ArrayList(Arrays.asList(desc.split(" ")));
						List<String> trashs = new ArrayList<String>();
						for (String keyword : keywords) {
							if (keyword.length() <= 1) {
								trashs.add(keyword);
							}
						}
						for (String trash : trashs) {
							keywords.remove(trash);
						}
						System.out.print(keywords.size());
						for (String keyword : keywords) {
							if (keyword.length() < 200 && keyword.length() != keyword.getBytes().length) {
								if (serviceWord.checkWordUnique(keyword) == 0) {
									serviceWord.setWordUnique(keyword);
								}
								WordDto newWord = new WordDto();
								newWord.setId(videoId);
								newWord.setWord(keyword);
								newWord.setCount(1);
								if (serviceWord.getWordFromVideo(newWord).size() != 0) {
									WordDto exist = serviceWord.getWordFromVideo(newWord).get(0);
									newWord.setCount(newWord.getCount() + exist.getCount());
									serviceWord.updateWordFromVideo(newWord);
								} else {
									serviceWord.setWordFromVideo(newWord);
								}
							}
						}
					}
					result.add(videoId);
					System.out.println(" : �Ϸ�.");
				} else {
					System.out.println(" �̹� �Ϸ�. ");
				}
			}
		}
		return result;
	}

	public void buildChains(List<String> videoIdList, List<String> videoIdListTag, List<String> channelIdList) {
		System.out.println("���� ����: " + videoIdList.size());
		System.out.println("ä�� ����: " +channelIdList.size());
		serviceWord.setWordChainsById();
		for (String channelId : channelIdList) {
			if (serviceBasic.getIdCompleteById(channelId) == 0) {
				IdComplete newComplete = new IdComplete();
				newComplete.setType("channel");
				newComplete.setId(channelId);
				serviceBasic.setIdComplete(newComplete);
			}
		}
		for (String videoId : videoIdList) {
			if (serviceBasic.getIdCompleteById(videoId) == 0) {
				IdComplete newComplete = new IdComplete();
				newComplete.setType("video");
				newComplete.setId(videoId);
				serviceBasic.setIdComplete(newComplete);
			}
		}
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void patchDataByGameNew() {
		List<String> gameList = serviceBasic.getGameNew();
		int mark = 0;
		for (String game : gameList) {
			System.out.println(mark);
			System.out.println("����: " + game);
			patchVideoAndChannelByGame(game);
			System.out.println("����: " + game + " : ������ ���� �Ϸ�.");
			mark ++;
		}
	}

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public void patchDataByGameFirst() {
		List<String> gameList = serviceBasic.getGameFirst();
		int mark = 0;
		for (String game : gameList) {
			System.out.println(mark);
			System.out.println("����: " + game);
			patchVideoAndChannelByGame(game);
			System.out.println("����: " + game + " : ������ ���� �Ϸ�.");
			mark ++;
		}
	}

	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public void patchDataByGameTop() {
		List<String> gameList = serviceBasic.getGameTop();
		int mark = 0;
		for (String game : gameList) {
			System.out.println(mark);
			System.out.println("����: " + game);
			patchVideoAndChannelByGame(game);
			System.out.println("����: " + game + " : ������ ���� �Ϸ�.");
			mark ++;
		}
	}

	@RequestMapping(value = "/parse/all")
	public void parseWords() {
		new ArrayList<String>();
		new ArrayList<String>();
		new ArrayList<String>();
		System.out.println("----------------- ä�� Ű���� ���� ���� -----------------");
		List<String> channelIdList = parseChannels();
		System.out.println("----------------- ä�� Ű���� ���� ���� -----------------");
		System.out.println("----------------- ���� Ű���� ���� ���� -----------------");
		List<String> videoIdList = parseVideos();
		System.out.println("----------------- ���� Ű���� ���� ���� -----------------");
		System.out.println("----------------- �±� Ű���� ���� ���� -----------------");
		List<String> videoIdListTag = parseTags();
		System.out.println("----------------- �±� Ű���� ���� ���� -----------------");
		System.out.println("----------------- ������ ���� ���� -----------------");
		buildChains(videoIdList, videoIdListTag, channelIdList);
		System.out.println("----------------- ������ ���� ���� -----------------");
	}

	@RequestMapping(value = "/chain/all")
	public void buildChains() {
		List<String> videoIdList = serviceVideo.getVideoIdsInComplete();
		List<String> channelIdList = serviceChannel.getChannelIdsInComplete();
		System.out.println("----------------- ������ ���� ���� -----------------");
		buildChains(videoIdList, videoIdList, channelIdList);
		System.out.println("----------------- ������ ���� ���� -----------------");
	}
	
}
