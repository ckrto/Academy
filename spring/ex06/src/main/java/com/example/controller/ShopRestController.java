package com.example.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.ShopDAO;
import com.example.domain.ShopVO;

@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
	
	@Autowired
	ShopDAO dao;
	
	@RequestMapping("/list")
	public List<ShopVO> list() {
		return dao.list();
	}
	
	@RequestMapping("/read")
	public ShopVO read(String code) {
		return dao.read(code);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(ShopVO vo, MultipartHttpServletRequest multi) throws Exception {
		if(multi.getFile("file") != null) {
			MultipartFile file = multi.getFile("file");
			String path = "c:/upload/shop/";
			String name = file.getOriginalFilename();
			File newFile = new File(path + name);
			if(!newFile.exists()) {
				file.transferTo(newFile);
			}
			vo.setImage("/upload/shop/" + name);
		}
		dao.update(vo);
	}
}
