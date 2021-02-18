package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.CtgrDto;
import com.my.spring.domain.TestDto;

public interface BasicService {
	public List<String> checkCtgr(String ctgr);
	public List<CtgrDto> checkCtgrRelation(String parent, String title);
	public List<TestDto> getCtgrBySearch(String parent);
}
