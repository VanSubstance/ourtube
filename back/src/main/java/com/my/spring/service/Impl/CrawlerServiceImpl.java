package com.my.spring.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.service.CrawlerService;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	private WebDriver driver;
	private WebDriver driverRecommend;
	private String base_url;
	
	@Override
	public ArrayList<Object> crawlGame() {
		List<String> notGames = new ArrayList<String>();
		notGames.add("Just Chatting");
		notGames.add("음악");
		notGames.add("먹방");
		notGames.add("토크쇼 & 팟케스트");
		notGames.add("과학 & 기술");
		notGames.add("제작 & 공예");
		notGames.add("정치");
		notGames.add("함께 시청하기");
		notGames.add("ASMR");
		notGames.add("Stocks And Bonds");
		notGames.add("Slots");
		notGames.add("Art");
		notGames.add("Games + Demos");
		notGames.add("토크 쇼");
		notGames.add("Chess");
		notGames.add("체스");
		notGames.add("스포츠");
		notGames.add("데모 게임");
		notGames.add("ATL+F4");
		notGames.add("VRChat");
		notGames.add("슬롯 머신");
		String WEB_DRIVER_ID = "webdriver.chrome.driver";
		String WEB_DRIVER_PATH = "C:/selenium_java/chromedriver.exe";
		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		// Driver SetUp
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		base_url = "https://www.twitch.tv/directory?sort=VIEWER_COUNT";
		ArrayList<Object> result = new ArrayList<Object>();
		List<Game> games = new ArrayList<Game>();
		List<GameTopic> gametopics = new ArrayList<GameTopic>();

		System.out.println("\n\n시청자 순\n");
		try {
			driver.get(base_url);
			Thread.sleep(2000);
			Document soup = Jsoup.parse(driver.getPageSource());
			for (Element tag : soup.select("div.tw-box-art-card")) {
				System.out.println("-------------------------------------------------------------------------------");
				String title = tag.select("h3").text();
				String thumbnail = tag.select("img.tw-image").attr("src");
				String nextUrl = "https://www.twitch.tv" + tag.select("a.tw-box-art-card__link.tw-link").attr("href");
				WebDriver driverDetail = new ChromeDriver();
				try {
					List<String> topics = new ArrayList<String>();
					driverDetail.get(nextUrl);
					Thread.sleep(4000);
					Document soupDetail = Jsoup.parse(driverDetail.getPageSource());
					for (Element topic : soupDetail.select("div.tw-flex.tw-flex-column.tw-justify-content-center")
							.select("div.tw-align-items-center.tw-flex.tw-font-size-7.tw-tag__content")) {
						topics.add(topic.text());
					}
					System.out.println();
					String googleUrl = "https://www.google.com/search?q=" + title;
					WebDriver driverTranslation = new ChromeDriver();
					try {
						driverTranslation.get(googleUrl);
						Thread.sleep(2000);
						Document soupTranslation = Jsoup.parse(driverTranslation.getPageSource());
						if (soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc.hNKfZe").select("span").text()
								.length() != 0) {
							String titleTranslated = soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc.hNKfZe")
									.select("span").text();
							titleTranslated = titleTranslated.split(" \\(")[0];
							title = titleTranslated;
						} else if (soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc").select("span").text()
								.length() != 0) {
							String titleTranslated = soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc")
									.select("span").text();
							titleTranslated = titleTranslated.split(" \\(")[0];
							title = titleTranslated;
						}

						if (!notGames.contains(title)) {
							System.out.println("제목: " + title);
							System.out.println("썸네일: " + thumbnail);
							System.out.println("토픽: " + topics);
							Game newGame = new Game();
							newGame.setTitle(title);
							newGame.setThumbnail(thumbnail);
							games.add(newGame);
							for (String topic : topics) {
								GameTopic newGameTopic = new GameTopic();
								newGameTopic.setTitle(title);
								newGameTopic.setTopic(topic);
								gametopics.add(newGameTopic);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						driverTranslation.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					driverDetail.close();
				}
				System.out.println("-------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

		driverRecommend = new ChromeDriver();
		driverRecommend.manage().window().maximize();
		base_url = "https://www.twitch.tv/directory?sort=RELEVANCE";
		System.out.println("\n\n추천 순\n");
		try {
			driverRecommend.get(base_url);
			Thread.sleep(2000);
			Document soup = Jsoup.parse(driverRecommend.getPageSource());
			System.out.println("게임 개수: " + soup.select("div.tw-box-art-card").size());
			for (Element tag : soup.select("div.tw-box-art-card")) {
				System.out.println("-------------------------------------------------------------------------------");
				String title = tag.select("h3").text();
				String thumbnail = tag.select("img.tw-image").attr("src");
				String nextUrl = "https://www.twitch.tv" + tag.select("a.tw-box-art-card__link.tw-link").attr("href");
				WebDriver driverDetail = new ChromeDriver();
				try {
					List<String> topics = new ArrayList<String>();
					driverDetail.get(nextUrl);
					Thread.sleep(2000);
					Document soupDetail = Jsoup.parse(driverDetail.getPageSource());
					for (Element topic : soupDetail.select("div.tw-flex.tw-flex-column.tw-justify-content-center")
							.select("div.tw-align-items-center.tw-flex.tw-font-size-7.tw-tag__content")) {
						topics.add(topic.text());
					}
					System.out.println();
					String googleUrl = "https://www.google.com/search?q=" + title;
					WebDriver driverTranslation = new ChromeDriver();
					try {
						driverTranslation.get(googleUrl);
						Thread.sleep(2000);
						Document soupTranslation = Jsoup.parse(driverTranslation.getPageSource());
						if (soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc.hNKfZe").select("span").text()
								.length() != 0) {
							String titleTranslated = soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc.hNKfZe")
									.select("span").text();
							titleTranslated = titleTranslated.split(" \\(")[0];
							title = titleTranslated;
						} else if (soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc").select("span").text()
								.length() != 0) {
							String titleTranslated = soupTranslation.select("h2.qrShPb.kno-ecr-pt.PZPZlf.mfMhoc")
									.select("span").text();
							titleTranslated = titleTranslated.split(" \\(")[0];
							title = titleTranslated;
						}

						if (!notGames.contains(title)) {
							System.out.println("제목: " + title);
							System.out.println("썸네일: " + thumbnail);
							System.out.println("토픽: " + topics);
							Game newGame = new Game();
							newGame.setTitle(title);
							newGame.setThumbnail(thumbnail);
							games.add(newGame);
							for (String topic : topics) {
								GameTopic newGameTopic = new GameTopic();
								newGameTopic.setTitle(title);
								newGameTopic.setTopic(topic);
								gametopics.add(newGameTopic);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						driverTranslation.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					driverDetail.close();
				}
				System.out.println("-------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driverRecommend.close();
		}
		result.add(games);
		result.add(gametopics);
		return result;
	}

	@Override
	public ArrayList<Object> crawlGameVidsManual(String gameQ) {
		String WEB_DRIVER_ID = "webdriver.chrome.driver";
		String WEB_DRIVER_PATH = "C:/selenium_java/chromedriver.exe";
		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		// Driver SetUp
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		base_url = "https://www.youtube.com/results?search_query=" + gameQ;
		ArrayList<Object> result = new ArrayList<Object>();
		List<String> videoIdList = new ArrayList();
		List<String> channelIdList = new ArrayList();

		try {
			driver.get(base_url);
			Thread.sleep(2000);
			Document soup = Jsoup.parse(driver.getPageSource());
			for (Element item : soup.select("ytd-video-renderer")) {
				Elements tag = item.select("a#video-title");
				String vidId = tag.attr("href");
				String title = tag.attr("title");
				vidId = vidId.replace("/watch?v=", "").split("&qq=")[0];
				videoIdList.add(vidId);
				System.out.println("비디오: " + title + " : " + vidId);
				tag = item.select("div#channel-info");
				String channelId = tag.select("a").attr("href");
				channelId = channelId.replace("/channel/", "");
				if (!channelId.contains("/user/")) {
					channelIdList.add(channelId);
					System.out.println("채널: " + channelId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
		
		result.add(videoIdList);
		result.add(channelIdList);
		return result;
	}
	
}
