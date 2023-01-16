package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.NaverAPI;

@Controller
public class NaverController {
	
	@RequestMapping("/naver")
	public String naver(){
		return "naver";
	}
	
	@ResponseBody
	@RequestMapping("/naver/shop.json")
	public String shop(String query, int page){
		int start=(page-1) * 5 + 1;
		return NaverAPI.start(query, start);
	}
	
	@RequestMapping("/insert")
	public String insert(){
		return "insert";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "list";
	}

	@RequestMapping("/read/{code}")
	public String read(@PathVariable String code, Model model){
		model.addAttribute("code", code);
		return "read";
	}
}
