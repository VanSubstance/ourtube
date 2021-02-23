package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ResultCtgr;

public interface BasicMapper {
	public int checkResultCtgr(@Param("ctgr") String ctgr);
	public void setResultCtgr(@Param("item") ResultCtgr item);
	public List<String> getCtgrs();
	public List<String> getCtgrsForPatch();
}
