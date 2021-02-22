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
	public void setComment(CommentDto item) {
		mapper.setComment(item);
	}

	@Override
	public int checkComment(String item) {
		return mapper.checkComment(item);
	}

}
