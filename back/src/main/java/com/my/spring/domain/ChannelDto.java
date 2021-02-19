package com.my.spring.domain;

import java.sql.Date;

import com.google.api.client.util.DateTime;

import lombok.*;

@Getter
@Setter
public class ChannelDto {
	private String id;
	private String title;
	private String description;
	private String thumbnail;
	private Date publishedDate;
	private int viewCount;
	private int videoCount;
	private int subsCount;
	private Date infoDate;
}
