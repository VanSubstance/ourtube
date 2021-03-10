package com.my.spring.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.my.spring.domain.basics.Game;
import com.my.spring.domain.chains.GameTopic;
import com.my.spring.service.CrawlerService;

@Service
public class CrawlerServiceImpl implements CrawlerService {
	
    private WebDriver driver;
    private String base_url;
    
    @Override
	public ArrayList<Object> crawlGame() {
        String WEB_DRIVER_ID = "webdriver.chrome.driver";
        String WEB_DRIVER_PATH = "C:/selenium_java/chromedriver.exe";
        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        //Driver SetUp
        driver = new ChromeDriver();
        base_url = "https://www.youtube.com/gaming/games";
        ArrayList<Object> result = new ArrayList<Object>();
        List<Game> games = new ArrayList<Game>();
        List<GameTopic> gameTopics = new ArrayList<GameTopic>();
        List<String> topicList = new ArrayList<String>();
        
        try {
            driver.get(base_url);
            WebElement body = driver.findElement(By.xpath("/html/body"));
            for (int i = 0; i < 5; i++) {
                body.sendKeys(Keys.END);
                Thread.sleep(1000);
            }
            Document soup = Jsoup.parse(driver.getPageSource());
            for (Element tag : soup.select("div#game")) {
            	System.out.println("-------------------------------------------------------------------------------");
            	String link = "https://www.youtube.com/" + tag.select("a").toString().split("href=\"")[1].split("\">")[0] + "/about";
            	String title = tag.select("yt-formatted-string#title").text();
                System.out.println("title       |\t" + title);
                System.out.println("link        |\t" + link);
                WebDriver driverDetail = new ChromeDriver();
                try {
                    driverDetail.get(link);
                    Document soupDetail = Jsoup.parse(driverDetail.getPageSource());
                    String thumbnail = soupDetail.select("img#img").toString().split(" src=\"")[1].split("\">")[0];
                    String description = soupDetail.select("yt-formatted-string#description").text();
                    List<String> topics = new ArrayList<String>();
                    for (Element topic : soupDetail.select("span.style-scope.ytd-badge-supported-renderer")) {
                    	GameTopic gameTopic = new GameTopic();
                    	gameTopic.setTitle(title);
                    	gameTopic.setTopic(topic.text());
                    	gameTopics.add(gameTopic);
                    	topics.add(topic.text());
                    	if (!topicList.contains(topic.text())) {
                        	topicList.add(topic.text());
                    	}
                    }
                    title = title.replaceAll("'", "\"");
                    description = description.replaceAll("'", "\"");
                    
                    System.out.println("thumbnail   |\t" + thumbnail);
                    System.out.println("description |\t" + description);
                    System.out.println("topics      |\t" + topics);
                    
                    Game game = new Game();
                    game.setTitle(title);
                    game.setDescription(description);
                    game.setThumbnail(thumbnail);
                    games.add(game);
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                driverDetail.close();
	            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        result.add(games);
        result.add(gameTopics);
        result.add(topicList);
        return result;
	}

}
