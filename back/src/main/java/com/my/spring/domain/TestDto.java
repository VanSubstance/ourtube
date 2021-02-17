package com.my.spring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TestDto {
	private String B_CAT;
	private String C_CAT;
	private String KEYWORD;
	
	public TestDto(String B_CAT, String C_CAT, String KEYWORD) {
		this.B_CAT = B_CAT;
		this.C_CAT = C_CAT;
		this.KEYWORD = KEYWORD;
	}
}
