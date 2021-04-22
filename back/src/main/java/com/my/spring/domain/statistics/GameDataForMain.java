package com.my.spring.domain.statistics;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class GameDataForMain {
	private String title;
	private Date infoDate;
	private int resultCount;
	private int viewCount;
	private int likeCount;
	private int dislikeCount;
	private int commentCount;
	private double ourScore;
	private int rank;
}
