package com.my.spring.mapper;

import java.util.List;

import com.my.spring.domain.TestDto;

public interface BasicMapper {
	public List<String> testGet();
	public List<TestDto> testGetAll();
}
