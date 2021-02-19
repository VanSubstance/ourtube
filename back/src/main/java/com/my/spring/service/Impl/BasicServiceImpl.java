package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.TestDto;
import com.my.spring.mapper.BasicMapper;
import com.my.spring.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	
	@Autowired
	public BasicMapper mapper;

	@Override
	public int checkCtgr(String ctgr) {
		return mapper.checkCtgr(ctgr);
	}
	
	@Override
	public List<TestDto> getCtgrBySearch(String parent) {
		return mapper.getCtgrBySearch(parent);
	}

	@Override
	public int checkCtgrRelation(String parent, String title) {
		return mapper.checkCtgrRelation(parent, title);
	}

	@Override
	public Boolean addCtgr(String ctgr) {
		return mapper.addCtgr(ctgr);
	}

	@Override
	public Boolean addCtgrRelation(String parent, String title) {
		return mapper.addCtgrRelation(parent, title);
	}

	@Override
	public String translateCtgrByTopicId(String topicId) {
		return mapper.translateCtgrByTopicId(topicId);
	}

}
