package com.my.spring.controller;

import java.util.List;

import com.my.spring.domain.exampleVO;
import com.my.spring.service.exampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/example")
public class exampleController {
	
	@Autowired
	private exampleService service;
	
	@RequestMapping(value = "/all")
	public List<exampleVO> exampleGet() {
		return service.exampleGet();
	}
}