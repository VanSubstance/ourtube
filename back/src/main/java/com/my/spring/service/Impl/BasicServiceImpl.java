package com.my.spring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.ResultCtgr;
import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper mapper;

	@Override
	public int checkResultCtgr(String ctgr) {
		return mapper.checkResultCtgr(ctgr);
	}

	@Override
	public void setResultCtgr(ResultCtgr item) {
		mapper.setResultCtgr(item);		
	}
}
