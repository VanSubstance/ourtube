package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.TestDto;

public interface BasicMapper {
	public List<String> testGet();
	public List<TestDto> getCtgrBySearch(@Param("parent") String parent);
}
