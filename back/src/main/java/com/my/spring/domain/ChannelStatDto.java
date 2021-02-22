package com.my.spring.domain;

import java.math.BigInteger;
import java.sql.Date;

import lombok.*;

@Setter
@Getter
public class ChannelStatDto {
	private String id;
	private BigInteger viewCount;
	private BigInteger videoCount;
	private BigInteger subsCount;
	private Date infoDate;
}
