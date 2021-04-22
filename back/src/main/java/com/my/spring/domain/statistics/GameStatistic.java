package com.my.spring.domain.statistics;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class GameStatistic {
	private String title;
	private Date infoDate;
	private int resultCount;
	private double ourScore;
	private int rank;
	
}
