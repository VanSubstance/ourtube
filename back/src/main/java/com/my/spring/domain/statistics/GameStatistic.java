package com.my.spring.domain.statistics;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class GameStatistic {
	private String title;
	private Date infoDate;
	private int resultCount;
	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private double w5;
	private double ourScore;
	private int rank;
}
