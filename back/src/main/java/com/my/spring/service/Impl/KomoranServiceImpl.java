package com.my.spring.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.my.spring.domain.TagDto;
import com.my.spring.domain.WordDto;
import com.my.spring.service.KomoranService;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.util.common.model.Pair;

@Service
public class KomoranServiceImpl implements KomoranService{
	
	private Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

	@Override
	public List<WordDto> parseTag(TagDto tagDto) {
		KomoranResult parsedOne = komoran.analyze(tagDto.getTag());
		HashMap<String, Integer> resultCount = new HashMap<String, Integer>();
		List<WordDto> result = new ArrayList<WordDto>();
		for (String word : parsedOne.getNouns()) {
			if (resultCount.containsKey(word)) {
				resultCount.put(word, resultCount.get(word) + 1);
			} else {
				resultCount.put(word, 1);
			}
		}
		for (Map.Entry<String, Integer> item : resultCount.entrySet()) {
			WordDto wordDto = new WordDto();
			wordDto.setId(tagDto.getId());
			wordDto.setWord(item.getKey());
			wordDto.setCount(item.getValue());
			result.add(wordDto);
		}
		return result;
	}

	@Override
	public List<WordDto> parseString(String id, String description) {
		KomoranResult parsedOne = komoran.analyze(description);
		HashMap<String, Integer> resultCount = new HashMap<String, Integer>();
		List<WordDto> result = new ArrayList<WordDto>();
		for (String word : parsedOne.getNouns()) {
			if (resultCount.containsKey(word)) {
				resultCount.put(word, resultCount.get(word) + 1);
			} else {
				resultCount.put(word, 1);
			}
		}
		for (Map.Entry<String, Integer> item : resultCount.entrySet()) {
			WordDto wordDto = new WordDto();
			wordDto.setId(id);
			wordDto.setWord(item.getKey());
			wordDto.setCount(item.getValue());
			result.add(wordDto);
		}
		return result;
	}
	
}
