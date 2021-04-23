package com.my.spring.domain.statistics;

import lombok.*;

@Getter
@Setter
public class GameDailyStatistic {
	private String title;
	private int numExistedVid;
	private int numNewVid;
	private int avgAccuView;
	private int avgNewView;
	private int avgNewLike;
	private int avgNewDislike;
	private int avgNewComment;
}
