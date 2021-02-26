package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.WordDto;
import com.my.spring.mapper.WordMapper;
import com.my.spring.service.WordService;

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	private WordMapper mapper;

	@Override
	public List<WordDto> getWordFromTag(WordDto item) {
		return mapper.getWordFromTag(item);
	}

	@Override
	public void setWordFromTag(WordDto item) {
		mapper.setWordFromTag(item);
	}

	@Override
	public void updateWordFromTag(WordDto item) {
		mapper.updateWordFromTag(item);
	}

	@Override
	public int checkCompleteForTag(String id) {
		return mapper.checkCompleteForTag(id);
	}

	@Override
	public List<WordDto> getWordFromChannel(WordDto item) {
		return mapper.getWordFromChannel(item);
	}

	@Override
	public void setWordFromChannel(WordDto item) {
		mapper.setWordFromChannel(item);
	}

	@Override
	public void updateWordFromChannel(WordDto item) {
		mapper.updateWordFromChannel(item);
	}

	@Override
	public int checkCompleteForChannel(String id) {
		return mapper.checkCompleteForChannel(id);
	}

	@Override
	public List<WordDto> getWordFromVideo(WordDto item) {
		return mapper.getWordFromVideo(item);
	}

	@Override
	public void setWordFromVideo(WordDto item) {
		mapper.setWordFromVideo(item);
	}

	@Override
	public void updateWordFromVideo(WordDto item) {
		mapper.updateWordFromVideo(item);
	}

	@Override
	public int checkCompleteForVideo(String id) {
		return mapper.checkCompleteForVideo(id);
	}
}
