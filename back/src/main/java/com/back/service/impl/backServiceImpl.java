package com.back.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.service.backService;
import com.back.service.dao.backDAO;
import com.back.vo.backVO;



@Service("backService")
public class backServiceImpl implements backService {
	@Autowired
	private backDAO backMapper;

	@Override
	@Transactional
	public List<backVO> selectbackList() throws Exception {
		return backMapper.selectbackList();
	}	
}
