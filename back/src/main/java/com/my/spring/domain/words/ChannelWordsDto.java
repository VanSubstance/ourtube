package com.my.spring.domain.words;

import java.util.List;

import lombok.*;

@Setter
@Getter
public class ChannelWordsDto {
	private String channelId;
	private List<String> words;

}
