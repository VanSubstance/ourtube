package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.TestDto;

public interface BasicService {
	public List<String> testGet();
	public List<TestDto> testGetAll();
}
