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
	
	@RequestMapping(value = "/checkCtgr/{ctgr}", method = RequestMethod.GET)
	public int checkCtgr(@PathVariable String ctgr) {
		return service.checkCtgr(ctgr);
	}
	
	@RequestMapping(value = "/checkCtgrRelation/{parent}/{title}", method = RequestMethod.GET)
	public int checkCtgrRelation(@PathVariable String parent, @PathVariable String title) {
		return service.checkCtgrRelation(parent, title);
	}
	
	@RequestMapping(value = "/searchCtgrsByParent/{parent}", method = RequestMethod.GET)
	public List<TestDto> searchCtgrsByParent(@PathVariable String parent) {
		return service.getCtgrBySearch(parent);
	}

}
