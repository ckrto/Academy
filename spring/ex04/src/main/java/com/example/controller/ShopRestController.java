package com.example.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(ShopVO vo, MultipartHttpServletRequest multi)throws Exception{
		MultipartFile file=multi.getFile("file");
		
		if(!file.isEmpty()){
			//파일업로드
			String strFile="c:/upload/shop/" + file.getOriginalFilename();
			File newFile=new File(strFile);
			if(!newFile.exists()) {
				file.transferTo(new File(strFile));
			}
			vo.setImage("/upload/shop/" + file.getOriginalFilename());
		}
		
		String code=sdf.format(new Date());
		vo.setCode(code);
		System.out.println(vo.toString());
		dao.insert(vo);	
	}
	
	@RequestMapping("/list")
	public List<ShopVO> list(){
		return dao.list();
	}
	
	@RequestMapping("/read/{code}")
	public HashMap<String,Object> read(@PathVariable String code){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("shop", dao.read(code));
		map.put("attach", dao.getAttach(code));
		return map;
	}
	
	@RequestMapping(value="/attach", method=RequestMethod.POST)
	public void attachInsert(String code, MultipartHttpServletRequest multi)
						throws Exception{

		String path="/upload/shop/" + code;
		File filePath= new File("c:/" + path);
		if(!filePath.exists()) filePath.mkdir();
		
		List<MultipartFile> files=multi.getFiles("files");
		for(MultipartFile file:files){
			if(!file.isEmpty()){
				String strFile=file.getOriginalFilename();
				File newFile=new File("c:/" + path + "/" + strFile);
				if(!newFile.exists()) {
					file.transferTo(newFile);
					dao.attach(code, path + "/" + strFile);
				}
			}
		}
	}
	
	@RequestMapping(value="/delAttach", method=RequestMethod.POST)
	public void delAttach(int id, String image){
		File file=new File("c:/" + image);
		if(file.exists()) file.delete();
		dao.delAttach(id);
	}
}





