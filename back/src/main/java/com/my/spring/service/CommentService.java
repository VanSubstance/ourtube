package com.my.spring.service;

import java.util.List;

import com.my.spring.domain.CommentDto;

public interface CommentService {
	public void addCommentSingle(CommentDto item);
	public int checkExistence(String id);
	
	public List<String> getCommentTextsByVideoId(String id);
}
