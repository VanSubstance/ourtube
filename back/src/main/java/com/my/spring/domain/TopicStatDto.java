package com.my.spring.domain;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
public class TopicStatDto {
	private String topic;
	private Date infoDate;
	private Integer resultCount;
}
