package com.my.spring.domain;

import java.math.BigInteger;
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
	private BigInteger viewCount;
	private BigInteger likeCount;
	private BigInteger dislikeCount;
	private BigInteger favoriteCount;
	private BigInteger commentCount;
	private Date infoDate;
}
