package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.ResultCtgr;

public interface BasicService {
	public int checkResultCtgr(String ctgr);
	public void setResultCtgr(ResultCtgr item);
	public List<String> getCtgrs();
	public List<String> getCtgrsForPatch();
}
