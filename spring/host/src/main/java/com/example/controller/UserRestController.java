package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	@Autowired
	UserDAO dao;

	@RequestMapping("/read/{u_code}")
	public UserVO read(@PathVariable String u_code) {
		UserVO vo = dao.read(u_code);
		return vo;
	}

	@RequestMapping("/list")
	public List<UserVO> list(){
		return dao.list();
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody UserVO vo){
		dao.insert(vo);
	}	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@RequestBody UserVO vo){
		dao.update(vo);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(@RequestBody UserVO vo){
		dao.delete(vo.getU_code());
	}
}
