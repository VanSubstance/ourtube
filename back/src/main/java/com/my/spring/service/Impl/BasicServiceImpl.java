package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	
	@Autowired
	public BasicMapper mapper;

	@Override
	public List<String> testGet() {
		return mapper.testGet();
	}

}
