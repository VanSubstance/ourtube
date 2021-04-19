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
	
	public void calcScore() {
		System.out.println("w1f: " + ((w1 + 0.8) * 17.5));
		System.out.println("w2f: " + (Math.pow((w2 + 100.0)/20.0, 2) / 4.0));
		System.out.println("w3f: " + ((Math.log10(w3 - 0.9) + 1.0) * 3.33));
		System.out.println("w4f: " + ((w4 + 0.8) * 12.5));
		System.out.println("w5f: " + ((Math.log10(w5 + 30.1) + 1.22) * 5.0));
		ourScore = ((w1 + 0.8) * 20.0) + 
				(Math.pow((w2 + 100.0)/20.0, 2) / 4.0) + 
				((Math.log10(w3 - 0.9) + 1.0) * 3.33) +
				((w4 + 0.8) * 1.25) +
				((Math.log10(w5 + 30.1) + 1.22) * 5.0);
	}
}
