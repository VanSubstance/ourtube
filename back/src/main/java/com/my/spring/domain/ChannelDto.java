package com.my.spring.domain;

import java.sql.Date;

import lombok.*;

@Getter
@Setter
public class ChannelDto {
	private String id;
	private String title;
	private String description;
	private String thumbnail;
	private Date publishedDate;
}
