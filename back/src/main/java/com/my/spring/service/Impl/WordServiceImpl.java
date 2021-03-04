package com.my.spring.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.WordDto;
import com.my.spring.domain.chains.WordChain;
import com.my.spring.domain.words.NounDto;
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

	@Override
	public List<NounDto> getTagWordsByTopic(String topic) {
		return mapper.getTagWordsByTopic(topic);
	}

	@Override
	public List<NounDto> getChannelWordsByTopic(String topic) {
		return mapper.getChannelWordsByTopic(topic);
	}

	@Override
	public List<NounDto> getVideoWordsByTopic(String topic) {
		return mapper.getVideoWordsByTopic(topic);
	}
	
	@Override
	public int checkWordUnique(String word) {
		return mapper.checkWordUnique(word);
	}
	
	@Override
	public void setWordUnique(String word) {
		mapper.setWordUnique(word);
	}

	@Override
	public WordChain getWordChain(WordChain item) {
		return mapper.getWordChain(item);
	}

	@Override
	public void setWordChain(WordChain item) {
		mapper.setWordChain(item);
	}

	@Override
	public void updateWordChain(WordChain item) {
		mapper.updateWordChain(item);
	}

	@Override
	public List<WordChain> buildWordChainByTag(String videoId) {
		return mapper.buildWordChainByTag(videoId);
	}

	@Override
	public List<WordChain> buildWordChainByVideo(String videoId) {
		return mapper.buildWordChainByVideo(videoId);
	}

	@Override
	public List<WordChain> buildWordChainByChannel(String channelId) {
		return mapper.buildWordChainByChannel(channelId);
	}

	@Override
	public void setWordChainsById() {
		mapper.setWordChainsById();
	}

}
