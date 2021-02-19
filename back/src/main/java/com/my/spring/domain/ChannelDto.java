package com.my.spring.domain;

import java.math.BigInteger;
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
	private BigInteger viewCount;
	private BigInteger videoCount;
	private BigInteger subsCount;
	private Date infoDate;
}
