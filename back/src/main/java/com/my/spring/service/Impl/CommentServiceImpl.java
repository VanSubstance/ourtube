package com.my.spring.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.domain.CommentDto;
import com.my.spring.mapper.CommentMapper;
import com.my.spring.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentMapper mapper;

	@Override
	public void addCommentSingle(CommentDto item) {
		mapper.addCommentSingle(item);
	}

	@Override
	public int checkExistence(String id) {
		return mapper.checkExistence(id);
	}

	@Override
	public List<String> getCommentTextsByVideoId(String id) {
		return mapper.getCommentTextsByVideoId(id);
	}

}
