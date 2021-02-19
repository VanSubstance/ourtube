package com.my.spring.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.domain.statistics.ChannelDto;
import com.my.spring.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService service;
	
	@RequestMapping(value = "/avg/channelByCtgr/{ctgr}/{term}", method = RequestMethod.GET)
	public List<ChannelDto> getAverageChannelByCtgr(@PathVariable String ctgr, @PathVariable int term) {
		term = 0 - term;
		List<ChannelDto> result = new ArrayList<ChannelDto>();
		System.out.println(ctgr + " 포함 채널 평균 통계값 출력\n");
		ChannelDto target = new ChannelDto();
		target.setCtgr(ctgr);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date infoDate = Date.valueOf(dateFormat.format(cal.getTime()));
		target.setInfoDate(infoDate);
		System.out.println("Date: " + infoDate.toString());
		while (service.getAverageChannelByCtgr(target) != null) {
			ChannelDto resultSingle = service.getAverageChannelByCtgr(target);
			resultSingle.setCtgr(ctgr);
			resultSingle.setInfoDate(infoDate);
			result.add(resultSingle);
			// term일 만큼 날짜 빼기
			cal.add(Calendar.DATE, term);
			infoDate = Date.valueOf(dateFormat.format(cal.getTime()));
			target.setInfoDate(infoDate);
			System.out.println("Date: " + infoDate.toString());
		}
		return result;
	}
}
