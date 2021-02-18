package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.TestDto;

public interface BasicMapper {
	public int checkCtgr(@Param("ctgr") String ctgr);
	public int checkCtgrRelation(@Param("parent") String parent, @Param("title") String title);
	public List<TestDto> getCtgrBySearch(@Param("parent") String parent);
	
	public Boolean addCtgr(@Param("ctgr") String ctgr);
	public Boolean addCtgrRelation(@Param("parent") String parent, @Param("title") String title);
}
