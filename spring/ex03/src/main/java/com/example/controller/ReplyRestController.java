package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ReplyDAO;
import com.example.domain.ReplyVO;
import com.example.service.ReplyService;

@RestController
@RequestMapping("/api/reply")
public class ReplyRestController {
	@Autowired
	ReplyDAO dao;
	
	@Autowired
	ReplyService service;
	
	@RequestMapping("/list/{bno}")
	public List<ReplyVO> list(@PathVariable int bno) {
		return dao.list(bno);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insert(ReplyVO vo) {
		service.insert(vo);
	}
	
	@RequestMapping(value="/delete/{rno}", method = RequestMethod.POST)
	public void delete(@PathVariable int rno) {
		service.delete(rno);
	}
}
