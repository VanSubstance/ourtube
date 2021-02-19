package com.my.spring.domain.statistics;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class ChannelDto {
	private String ctgr;
	private int viewCount;
	private int videoCount;
	private int subsCount;
	private Date infoDate;
}
