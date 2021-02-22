package com.my.spring.domain;

import java.math.BigInteger;
import java.sql.Date;

import lombok.*;

@Getter
@Setter
public class VideoStatDto {
	private String id;
	private BigInteger viewCount;
	private BigInteger likeCount;
	private BigInteger dislikeCount;
	private BigInteger favoriteCount;
	private BigInteger commentCount;
	private Date infoDate;

}
