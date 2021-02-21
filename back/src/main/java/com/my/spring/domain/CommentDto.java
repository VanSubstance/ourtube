package com.my.spring.domain;

import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class CommentDto {
	private String id;
	private String name;
	private String text;
	private String videoId;
	private int likeCount;
	private Date publishedDate; 
}
