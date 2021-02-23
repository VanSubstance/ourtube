package com.my.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.ResultCtgr;

public interface BasicMapper {
	public int checkResultCtgr(@Param("ctgr") String ctgr);
	public void setResultCtgr(@Param("item") ResultCtgr item);
}
