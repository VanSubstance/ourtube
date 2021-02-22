package com.my.spring.domain;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
public class VideoDto {
	private String id;
	private String channelId;
	private String title;
	private String description;
	private String thumbnail;
	private Date publishedDate;
}
