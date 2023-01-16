package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.TradeDAO;
import com.example.domain.TradeVO;
import com.example.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeRestController {
	@Autowired
	TradeDAO tdao;
	
	@Autowired
	TradeService service;
	
	@RequestMapping("/list/{ano}")
	public List<TradeVO> list(@PathVariable String ano){
		return tdao.list(ano);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void trade(@RequestBody TradeVO vo){
		service.trade(vo);
	}
}
