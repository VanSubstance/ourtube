package com.my.spring.domain.statistics;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class TopicStatistic {
	private String topic;
	private Date infoDate;
	private Integer rank;
	private Double ourScore;
	private Integer resultCount;
	private Integer viewCount;
	private Integer likeCount;
	private Integer dislikeCount;
	
	@Override
	public String toString() {
		return topic + "\n" +
				ourScore + "\n" + 
	infoDate + "\n" + 
				rank + "\n" + 
	resultCount + "\n" + 
				viewCount + "\n" + 
	likeCount + "\n" + 
				dislikeCount;
	}
}
