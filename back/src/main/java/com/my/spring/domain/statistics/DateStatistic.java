package com.my.spring.domain.statistics;

import lombok.*;

@Setter
@Getter
public class DateStatistic {
	private int rank;
	private int numNewVid;
	private int avgNewView;
	private int avgNewLike;
	private int avgNewDislike;
	private int avgNewComment;
	private int numAccuVid;
	private int avgAccuView;
	private int avgAccuLike;
	private int avgAccuDislike;
	private int avgAccuComment;
}
