package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.TestDto;
import com.my.spring.service.BasicService;

@RestController
@RequestMapping("/basic")
public class BasicController {
	
	@Autowired
	private BasicService service;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<String> test() {
		return service.testGet();
	}
	
	@RequestMapping(value = "/test2/{parent}", method = RequestMethod.GET)
	public List<TestDto> test2(@PathVariable String parent) {
		return service.getCtgrBySearch(parent);
	}

}
