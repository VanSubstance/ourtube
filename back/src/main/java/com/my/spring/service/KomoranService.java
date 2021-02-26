package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.TagDto;
import com.my.spring.domain.WordDto;

public interface KomoranService {
	public List<WordDto> parseTag(TagDto tagDto);
	public List<WordDto> parseString(String id, String description);
}
