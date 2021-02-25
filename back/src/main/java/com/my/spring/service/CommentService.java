package com.my.spring.service;

import com.my.spring.domain.CommentDto;

public interface CommentService {
	public void setComment(CommentDto item);
	public int checkComment(CommentDto item);
}
