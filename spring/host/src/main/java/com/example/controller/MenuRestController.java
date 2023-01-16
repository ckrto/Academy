package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.MenuDAO;
import com.example.domain.MenuVO;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {
	@Autowired
	MenuDAO dao;
	
	@RequestMapping("/list/{s_code}")
	public List<MenuVO> list(@PathVariable String s_code){
		return dao.list(s_code);
	}
	
	@RequestMapping("/read")
	public MenuVO read(@RequestBody MenuVO vo){
		return dao.read(vo.getS_code(),vo.getM_name());
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody MenuVO vo){
		dao.insert(vo);
	}	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@RequestBody MenuVO vo){
		dao.update(vo);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(@RequestBody MenuVO vo){
		dao.delete(vo.getS_code(),vo.getM_name());
	}
}
