package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.CtgrDto;
import com.my.spring.domain.TestDto;

public interface BasicMapper {
	public List<String> checkCtgr(@Param("ctgr") String ctgr);
	public List<CtgrDto> checkCtgrRelation(@Param("parent") String parent, @Param("title") String title);
	public List<TestDto> getCtgrBySearch(@Param("parent") String parent);
}
