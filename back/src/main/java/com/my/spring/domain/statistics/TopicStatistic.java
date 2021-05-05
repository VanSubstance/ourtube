package com.my.spring.domain.statistics;

import lombok.*;

@Setter
@Getter
public class TopicStatistic {
	private Double ourScore;
	private Integer resultCount;
	private Integer viewCount;
	private Integer likeCount;
	private Integer dislikeCount;
	
	@Override
	public String toString() {
		return ourScore + "\n" + resultCount + "\n" + viewCount + "\n" + likeCount + "\n" + dislikeCount;
	}
}
