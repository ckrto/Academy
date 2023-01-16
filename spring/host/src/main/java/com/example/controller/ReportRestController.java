package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ReportDAO;
import com.example.domain.ReportVO;


@RestController
@RequestMapping("/api/report")
public class ReportRestController {
	@Autowired
	ReportDAO dao;
	
	@RequestMapping("/list")
	public List<ReportVO> list(){
		return dao.list();
	}
	
	@RequestMapping("/read/{r_code}")
	public ReportVO read(@PathVariable String r_code){
		ReportVO vo=dao.read(r_code);
		return vo;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody ReportVO vo){
		dao.insert(vo);
	}	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@RequestBody ReportVO vo){
		dao.update(vo);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(@RequestBody ReportVO vo){
		dao.delete(vo.getR_code());
	}
}
