package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.service.BasicService;

@RestController
@RequestMapping(value = "/basic")
public class BasicController {
	@Autowired
	private BasicService service;
	
	@RequestMapping(value = "/test")
	public List<String> testGet() {
		return service.testGet();
	}

}
