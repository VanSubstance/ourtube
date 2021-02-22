package com.my.spring.mapper;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.CommentDto;

public interface CommentMapper {
	public void setComment(@Param("item") CommentDto item);
	public int checkComment(@Param("item") String item);
}
