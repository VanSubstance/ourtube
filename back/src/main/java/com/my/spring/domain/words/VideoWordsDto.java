package com.my.spring.domain.words;

import java.util.List;

import lombok.*;

@Setter
@Getter
public class VideoWordsDto {
	private String videoId;
	private List<String> words;

}
