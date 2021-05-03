package com.my.spring.domain.statistics;

import lombok.*;

@Setter
@Getter
public class DateStatistic {
	private Integer rank;
	private Integer numNewVid;
	private Integer avgNewView;
	private Integer avgNewLike;
	private Integer avgNewDislike;
	private Integer avgNewComment;
	private Integer numAccuVid;
	private Integer avgAccuView;
	private Integer avgAccuLike;
	private Integer avgAccuDislike;
	private Integer avgAccuComment;
}
