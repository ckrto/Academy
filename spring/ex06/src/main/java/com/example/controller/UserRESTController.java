package com.example.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserRESTController {
	@Autowired
	   UserDAO dao;
	   
	   @Autowired
	   PasswordEncoder encoder;
	   
	   // 사용자 등록
	   @RequestMapping(value="/insert", method=RequestMethod.POST)
	   public void insert(@RequestBody UserVO vo) throws Exception {
//	      if(multi.getFile("file") != null) {
//	         MultipartFile file = multi.getFile("file");
//	         String path = "c:/upload/photo/";
//	         String fileName = file.getOriginalFilename();
//	         File newFile = new File(path + fileName);
//	         
//	         if(!newFile.exists()) file.transferTo(newFile);
//	         
//	         vo.setPhoto("/upload/photo/" + fileName);
//	      }
//	      vo.setUpass(encoder.encode(vo.getUpass()));
		   System.out.println(vo.toString());
	      dao.insert(vo);
	   }
	   
	   // 사용자 정보
	   @RequestMapping("/read/{uid}")
	   public UserVO read(@PathVariable String uid) {
	      return dao.read(uid);
	   }
	   
	   // 로그인 체크
	   @RequestMapping(value="/login", method=RequestMethod.POST)
	   public int login(@RequestBody UserVO vo) {
	      UserVO readVO = dao.read(vo.getUid());
	      int result = 0;

	      if(readVO != null) {
	         if(encoder.matches(vo.getUpass(), readVO.getUpass()));
	         result = 1;
	      } else {
	         result = 2;
	      }
	      return result;
	   }

}
