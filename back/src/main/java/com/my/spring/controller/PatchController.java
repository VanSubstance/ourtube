package com.my.spring.controller;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.my.spring.service.StatisticService;
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
	@Autowired
	private StatisticService serviceStatistic;
	
	private final int hour = 3;
	
	@RequestMapping(value = "/menually")
	public void patchMenually() {
		patchGameFromYoutube();
		patchDataByGameFirst();
		parseWords();
	}
	
	@Scheduled(cron = "1 5 " + hour + " * * *")
	public void patchGameFromYoutube() {
		System.out.println("------------------------------- ���� ũ�Ѹ� ���� -------------------------------");
		ArrayList<Object> data = serviceCrawler.crawlGame();
		List<Game> games = (List<Game>) data.get(0);
		List<GameTopic> gameTopics = (List<GameTopic>) data.get(1);
		for (Game game : games) {
			System.out.println("���� ���.");
			try {
				serviceBasic.setGame(game);
				serviceBasic.setGameInGameSearch(game.getTitle());
			} catch (DataIntegrityViolationException e) {
				System.out.println("�̹� ����");
			}
		}
		for (GameTopic gameTopic : gameTopics) {
			System.out.println("ü�� ���");
			try {
				serviceBasic.setGameTopic(gameTopic);
			} catch (DataIntegrityViolationException e) {
				System.out.println("�̹� ����");
			}
		}
		System.out.println("------------------------------- ���� ũ�Ѹ� ���� -------------------------------");
	}

	@Scheduled(cron = "1 25 " + hour + " * * *")
	public void patchDataByGameFirst() {
		List<String> gameQList = serviceBasic.getGameQ();
		int mark = 0;
		for (String gameQ : gameQList) {
			System.out.println(mark);
			System.out.println("����: " + gameQ);
			patchVideoAndChannelByGame(gameQ, mark % 4);
			System.out.println("����: " + gameQ + " : ������ ���� �Ϸ�.");
			mark ++;
		}
		System.out.println("���� ���� ������ �Խ����� 70��(10��)�� �Ѿ�� ������ id�� ����");
		serviceBasic.deleteOldDatas();
	}
	
//	@Scheduled(cron = "1 40 " + hour + " * * *")
//	@RequestMapping(value = "/calc/weight")
//	public void calculatePercentile() {
//		System.out.println("���� ��� -------------");
//		SimpleRegression regression = new SimpleRegression();
//		List<String> GamesOrderByView = serviceStatistic.getGamesByDate();
//		List<String> GamesOrderByComment = serviceStatistic.getGamesOrderByComment();
//		List<Double> w3s = serviceStatistic.getLikeRatioByDate();
//		HashMap<String, GameStatistic> gameStats = new HashMap<String, GameStatistic>();
//		for (String title : GamesOrderByView) {
//			System.out.println("\t" + title);
//			int seq = GamesOrderByView.indexOf(title);
//			GameStatistic gameStat = new GameStatistic();
//			gameStat.setTitle(title);
//			
//			// w0 ���
//			// w0: ��� �ű� ��ȸ��
//			double w0 = serviceVideo.getNumNewViewTodayByTitle(title);
//			gameStat.setW0(w0);
//			
//			// w1 ���
//			// w1: ��� �ű� ��ȸ��
//			double w1 = serviceVideo.getNumNewVidTodayByTitle(title);
////			double w1 = (double) seq
////					/ (double) GamesOrderByView.size();
////			w1 -= 0.5;
////			if (w1 < 0.0) {
////				w1 = - Math.pow(Math.abs(w1), 1.0/3.0);
////			} else {
////				w1 = Math.pow(w1, 1.0/3.0);
////			}
//			gameStat.setW1(w1);
//			
//			// w2 ���
//			// w2: �ش� ���� �� 14�ϰ� �� �� ���� ��ȸ�� ���� ���� ���
//			List<Integer> y = serviceStatistic.getRecentViewsByGame(title);
//			double[][] data = new double[14][2];
//			for (int i = 0; i < y.size(); i++) {
//				data[i][0] = i;
//				data[i][1] = y.get(i);
//			}
//			regression.addData(data);
//			double w2 = regression.getSlope()/10000;
//			if (w2 > 100.0) w2 = 100.0;
//			if (w2 < -100.0) w2 = -100.0;
//			gameStat.setW2(w2);
//			
//			// w3 ���
//			// w3: �ش� ���� ���� �� ���Ⱥ� (���� ���ƿ� / ���� �Ⱦ��)
//			double w3 = w3s.get(seq);
//			if (w3 > 100.0) w3 = 100.0;
//			if (w3 < 1.0) w3 = 1.0;
//			gameStat.setW3(w3);
//			
//			// w4 ���
//			// w4: �ش� ���� ���� ���� ��� �����
//			double w4 = (double) GamesOrderByComment.indexOf(title)
//					/ (double) GamesOrderByComment.size();
//			w4 -= 0.5;
//			if (w4 < 0.0) {
//				w4 = - Math.pow(Math.abs(w4), 1.0/3.0);
//			} else {
//				w4 = Math.pow(w4, 1.0/3.0);
//			}
//			gameStat.setW4(w4);
//			
//			// w5 ���
//			// w5: �ش� ���� �� 14�ϰ� �� �� ���� ���� ��� �� ���� ���� ���
//			regression = new SimpleRegression();
//			y = serviceStatistic.getRecentCommentCountsByGame(title);
//			data = new double[14][2];
//			for (int i = 0; i < y.size(); i++) {
//				data[i][0] = i;
//				data[i][1] = y.get(i);
//			}
//			regression.addData(data);
//			double w5 = regression.getSlope()/10;
//			if (w5 > 30.0) w5 = 30.0;
//			if (w5 < -30.0) w5 = -30.0;
//			gameStat.setW5(w5);
//			
//			System.out.println("w0: " + gameStat.getW0());
//			System.out.println("w1: " + gameStat.getW1());
//			System.out.println("w2: " + gameStat.getW2());
//			System.out.println("w3: " + gameStat.getW3());
//			System.out.println("w4: " + gameStat.getW4());
//			System.out.println("w5: " + gameStat.getW5());
//			
//			// OurScore ���			
////			gameStat.calcScore();
////			System.out.println("Score: " + gameStat.getOurScore());
////			
////			gameStats.put(title, gameStat);
////			
////			// ����
////			serviceBasic.setWeightsGameTodayByGame(gameStat);
//			
//		}
//
//		System.out.println("���� ��� ���� -------------\n");
//	}
	
	@Scheduled(cron = "1 55 " + hour + " * * *")
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
	
	public void patchChannelByGame(String title, List<String> channelIdList, int api) {
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
		System.out.println(" : �Ϸ�.");
		List<ChannelDto> existedInfo = new ArrayList<ChannelDto>();
		if (existed.size() != 0) {
			existedInfo = serviceChannel.getChannelInfoById(existed);
		}

		System.out.println("\t\t���� ä�� �� �ű� id ����Ʈ -> ä�� �⺻ ���� & ä�� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callChannelInfosByChannelId(temp, api);
		List<ChannelDto> channelDtoList = (List<ChannelDto>) data.get(0);
		List<ChainDto> chainChannel = new ArrayList<ChainDto>();

		System.out.print("\t\t\tä�� �⺻ ���� -> Database | o -> ��� | ");
		for (ChannelDto channelDto : channelDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(channelDto.getId());
			newChain.setGame(title);
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
			newChain.setGame(title);
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
		
		System.out.println("\t\t���� ��� ���� ������ ���� ä�� id ����Ʈ -> Youtube API -> ���� ä�� ��� ����");
		channelIdList = serviceChannel.getChannelIdsByGame(title);
		data = serviceYoutube.callChannelStatsByChannelId(channelIdList, api);
		System.out.println();
		List<ChannelStatDto> channelStatList = (List<ChannelStatDto>) data.get(0);
		System.out.print("\t\t\t���� ä�� ��� ���� -> Database | o -> ��� | ");
		for (ChannelStatDto channelStatDto : channelStatList) {
			try {
				serviceChannel.setChannelStatistics(channelStatDto);
				System.out.print("o");
			} catch (DataIntegrityViolationException e) {
				System.out.print("x");
			}
		}
		System.out.println(" �Ϸ�.");
		System.out.println("------------------------ ä�� ������ �۾� ����  ------------------------");
	}

	public void patchVideoByGame(String title, List<String> videoIdList, int api) {
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
		System.out.println(" : �Ϸ�.");
		List<VideoDto> existedInfo = new ArrayList<VideoDto>();
		if (existed.size() != 0) {
			existedInfo = serviceVideo.getVideoInfoById(existed);
		}
		System.out.println("\t\t���� ���� �� �ű� id ����Ʈ -> ���� �⺻ ���� & �±� & ���� ���� ü��");
		ArrayList<Object> data = serviceYoutube.callVideoInfosByVideoId(temp, api);
		List<VideoDto> videoDtoList = (List<VideoDto>) data.get(0);
		List<ChainDto> chainVideo = new ArrayList<ChainDto>();

		System.out.print("\t\t\t���� �⺻ ���� -> Database | o -> ��� | ");
		for (VideoDto videoDto : videoDtoList) {
			ChainDto newChain = new ChainDto();
			newChain.setId(videoDto.getId());
			newChain.setGame(title);
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
			newChain.setGame(title);
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
		data = serviceYoutube.callCommentsByVideoId(videoIdList, api);
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
				System.out.println(commentDto.getText());
				if (commentDto.getText().length() != 0) {
					serviceComment.setComment(commentDto);
				}
			}
		}
		System.out.println(" �Ϸ�.");
		
		System.out.println("\t\t���� ��� ���� ������ ���� ���� id ����Ʈ -> Youtube API -> ���� ���� ��� ����");
		videoIdList = serviceVideo.getVideoIdsByGame(title);
		data = serviceYoutube.callVideoStatsByVideoId(videoIdList, api);
		System.out.println();
		List<VideoStatDto> videoStatList = (List<VideoStatDto>) data.get(0);
		System.out.print("\t\t\t���� ���� ��� ���� -> Database | o -> ��� | ");
		for (VideoStatDto videoStatDto : videoStatList) {
			try {
				serviceVideo.setVideoStatistics(videoStatDto);
				System.out.print("o");
			} catch (DataIntegrityViolationException e) {
				System.out.print("x");
			}
		}
		System.out.println(" �Ϸ�.");
		System.out.println("------------------------ ���� ������ �۾� ����  ------------------------");
	}
	
	@RequestMapping(value = "/menual")
	public void renewDataToday() {
		List<String> titles = serviceBasic.getAllTitle();
		int api = 0;
		for (String title: titles) {
			System.out.println(title + " ����");
			List<String> videoIdList = serviceVideo.getVideoIdsByGame(title);
			System.out.println("���� ����: " + videoIdList.size() + "��");
			ArrayList<Object> data = serviceYoutube.callVideoStatsByVideoId(videoIdList, api);
			List<VideoStatDto> videoStatList = (List<VideoStatDto>) data.get(0);
			System.out.print("\t���� ���� ��� ���� -> Database | o -> ��� | ");
			for (VideoStatDto videoStatDto : videoStatList) {
				System.out.println(videoStatDto.getId());
				try {
					System.out.print("o");
				} catch (DataIntegrityViolationException e) {
					System.out.print("x");
				}
			}
			System.out.println("���� �Ϸ�.");
			
			List<String> channelIdList = serviceChannel.getChannelIdsByGame(title);
			System.out.println("ä�� ����: " + channelIdList.size() + "��");
			data = serviceYoutube.callChannelStatsByChannelId(channelIdList, api);
			List<ChannelStatDto> channelStatList = (List<ChannelStatDto>) data.get(0);
			System.out.print("\t���� ä�� ��� ���� -> Database | o -> ��� | ");
			for (ChannelStatDto channelStatDto : channelStatList) {
				try {
					serviceChannel.setChannelStatistics(channelStatDto);
					System.out.print("o");
				} catch (DataIntegrityViolationException e) {
					System.out.print("x");
				}
			}
			System.out.println("ä�� �Ϸ�.");
			
			System.out.println(title + " �Ϸ�");
			api++;
			api %= 4;
		}
	}

	public void patchVideoAndChannelByGame(String gameQ, int api) {
		System.out.println("------------------------ ������ �۾� ����  ------------------------");
		System.out.print("\tYoutube API -> ���� ���� id ����Ʈ");
		ArrayList<Object> data = serviceYoutube.callVideoIdsByGame(gameQ, api);
		List<String> videoIdList = (List<String>) data.get(0);
		List<String> channelIdList = (List<String>) data.get(1);
		GameStatistic gameStat = (GameStatistic) data.get(2);
		data = serviceCrawler.crawlGameVidsManual(gameQ);
		videoIdList.addAll((List<String>) data.get(0));
		channelIdList.addAll((List<String>) data.get(1));
		Set<String> container = new HashSet<String>(videoIdList);
		videoIdList = new ArrayList<String>(container);
		container = new HashSet<String>(channelIdList);
		channelIdList = new ArrayList<String>(container);
		System.out.print(videoIdList.size() + " ��, ä�� id ����Ʈ: ");
		System.out.println(channelIdList.size() + " ��");

		String title = serviceBasic.getTitleByQ(gameQ);
		gameStat.setTitle(title);
		System.out.print("���� ���� ������: ");
		try {
			serviceBasic.setGameStat(gameStat);
			System.out.println("�Ϸ�");
		} catch (DataIntegrityViolationException e) {
			System.out.println("�̹� ����");
		}

		patchVideoByGame(title, videoIdList, api);
		patchChannelByGame(title, channelIdList, api);

		System.out.println("------------------------ ������ �۾� ����  ------------------------");
	}
	
	public List<String> parseTags() {
		List<String> result = new ArrayList<String>();
		List<String> filter = serviceBasic.getNounFilter();
		List<String> gameList = serviceBasic.getAllTitle();
		for (String game : gameList) {
			System.out.println("����: " + game);
			List<String> videoIdList = serviceVideo.getTagNotParsedVideoIdsByTitle(game);
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
		List<String> gameList = serviceBasic.getAllTitle();
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
		List<String> gameList = serviceBasic.getAllTitle();
		for (String game : gameList) {
			System.out.println("����: " + game);
			List<String> videoIdList = serviceVideo.getNotParsedVideoIdsByTitle(game);
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
	
}
