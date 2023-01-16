package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.StoreDAO;
import com.example.domain.StoreVO;
import com.example.domain.UserVO;

@RestController
@RequestMapping("/api/store")
public class StoreRestController {
	@Autowired
	StoreDAO dao;
	
	@RequestMapping("/list")
	public List<StoreVO> list(){
		return dao.list();
	}
	
	@RequestMapping("/read/{s_code}")
	public StoreVO read(@PathVariable String s_code){
		StoreVO vo=dao.read(s_code);
		return vo;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody StoreVO vo){
		dao.insert(vo);
	}	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@RequestBody StoreVO vo){
		dao.update(vo);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(@RequestBody StoreVO vo){
		dao.delete(vo.getS_code());
	}
	
	@RequestMapping(value="/updateBtn", method=RequestMethod.POST)
	public void updateBtn(@RequestBody StoreVO vo){
		dao.updateBtn(vo.getS_code());
	}
}
