package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.WordDto;

public interface WordService {
	public List<WordDto> getWordFromTag(WordDto item);
	public void setWordFromTag(WordDto item);
	public void updateWordFromTag(WordDto item);
	public int checkCompleteForTag(String id);

	public List<WordDto> getWordFromChannel(WordDto item);
	public void setWordFromChannel(WordDto item);
	public void updateWordFromChannel(WordDto item);
	public int checkCompleteForChannel(String id);

	public List<WordDto> getWordFromVideo(WordDto item);
	public void setWordFromVideo(WordDto item);
	public void updateWordFromVideo(WordDto item);
	public int checkCompleteForVideo(String id);
}
