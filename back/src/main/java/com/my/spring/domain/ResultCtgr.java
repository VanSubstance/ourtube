package com.my.spring.domain;

import java.sql.Date;
import lombok.*;

@Getter
@Setter
public class ResultCtgr {
	private String ctgr;
	private Date infoDate;
	private Integer resultCount;
}
