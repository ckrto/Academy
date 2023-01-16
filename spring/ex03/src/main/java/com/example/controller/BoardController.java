package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/list")
	public String list() {
		return "list";
	}
	
	@RequestMapping("/read/{bno}")
	public String read(Model model, @PathVariable int bno) {
		model.addAttribute("bno", bno);
		return "read";
	}
	
}
