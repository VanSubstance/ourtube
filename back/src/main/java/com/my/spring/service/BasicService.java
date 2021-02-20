package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.TestDto;

public interface BasicService {
	public int checkCtgr(String ctgr);
	public int checkCtgrRelation(String parent, String title);
	
	public List<TestDto> getCtgrBySearch(String parent);
	public String translateCtgrByTopicId(String topicId);
	
	public Boolean addCtgr(String ctgr);
	public Boolean addCtgrRelation(String parent, String title);

	public List<String> checkExistenceChain(String id, String ctgr);
}
