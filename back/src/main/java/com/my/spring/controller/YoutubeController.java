package com.my.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.*;
import com.my.spring.service.BasicService;
import com.my.spring.service.YoutubeService;

@RestController
@RequestMapping(value = "/youtube")
public class YoutubeController {
	
	private YoutubeService service;

	@Autowired
	private BasicService serviceBasic;
	
	@Autowired
	public YoutubeController(final YoutubeService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/ctgr/{ctgr}", method = RequestMethod.GET)
	public List<ChannelDto> Index(@PathVariable String ctgr) {
		ArrayList<Object> dataset = service.getChannelsByCtgr(ctgr);
		List<String> ctgrs = new ArrayList();
		ctgrs.add(ctgr);
		ctgrs.addAll((List<String>) dataset.get(1));
		System.out.println("Getting Dataset dones.\n");
		System.out.println(ctgrs);
		for (int i = 0; i < ctgrs.size(); i++) {
			System.out.println(ctgrs.get(i));
			if (serviceBasic.checkCtgr(ctgrs.get(i)) == 0) {
				System.out.println("Add new ctgr.\n");
				serviceBasic.addCtgr(ctgrs.get(i));
			}
		}
		System.out.println("Adding new ctgrs done.\n\n");
		for (int i = 0; i < ctgrs.size(); i++) {
			for (int j = i; j < ctgrs.size(); j++) {
				System.out.println(ctgrs.get(i) + ", " + ctgrs.get(j));
				if (serviceBasic.checkCtgrRelation(ctgrs.get(i), ctgrs.get(j)) == 0) {
					System.out.println("Add new ctgrRelation.\n");
					serviceBasic.addCtgrRelation(ctgrs.get(i), ctgrs.get(j));
				}
			}
		}
		System.out.println("Adding new ctgrRelation done.\n\n");
		return (List<ChannelDto>) dataset.get(0);
	}
	
}
