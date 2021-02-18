package com.my.spring.domain;

import com.google.api.client.util.DateTime;

import lombok.*;

@Getter
@Setter
public class ChannelDto {
	private String id;
	private String title;
	private String description;
	private String thumbnail;
	private DateTime publishedDate;
	private int views;
	private int videos;
	private int subs;
}
