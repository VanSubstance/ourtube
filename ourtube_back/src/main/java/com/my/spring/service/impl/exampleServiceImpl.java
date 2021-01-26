package com.my.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.exampleVO;
import com.my.spring.mapper.exampleMapper;
import com.my.spring.service.exampleService;

@Service
public class exampleServiceImpl implements exampleService {
	
	@Autowired
	private exampleMapper mapper;
	
	@Override
	public List<exampleVO> exampleGet() {
		return mapper.exampleGet();
	}
}