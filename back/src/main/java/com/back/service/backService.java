package com.back.service;

import java.util.List;

import com.back.vo.backVO;

public interface backService {
	List<backVO> selectbackList() throws Exception;
}
