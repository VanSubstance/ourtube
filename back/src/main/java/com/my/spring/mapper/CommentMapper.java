package com.my.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.my.spring.domain.CommentDto;

public interface CommentMapper {
	public void addCommentSingle(@Param("item") CommentDto item);
	public int checkExistence(@Param("id") String id);
	public List<String> getCommentTextsByVideoId(@Param("id") String id);

}
