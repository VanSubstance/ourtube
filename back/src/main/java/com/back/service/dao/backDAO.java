package com.back.service.dao;

import java.util.List;

import com.back.vo.backVO;

public interface backDAO {
	List<backVO> selectbackList() throws Exception;

}