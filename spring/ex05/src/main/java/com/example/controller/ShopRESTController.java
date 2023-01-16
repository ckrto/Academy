package com.example.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.ShopDAO;
import com.example.domain.ShopVO;

@RestController
@RequestMapping("/api/shop")
public class ShopRESTController {
	@Autowired
	ShopDAO dao;
	SimpleDateFormat sdf= new SimpleDateFormat("yyMMddHHmmss");
			
	@RequestMapping("/list")
	public HashMap<String, Object> list(int page,int num){
		HashMap<String, Object> map= new HashMap<>();
		map.put("total", dao.total());
		map.put("list",dao.list(page,num));
		return map;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(ShopVO vo, MultipartFile file)throws Exception{
		if(!file.isEmpty()) {
			String strFile="c:/upload/shop/"+file.getOriginalFilename();
			File newFile=new File(strFile);
			if(!newFile.exists()){
				file.transferTo(newFile);
			}
			vo.setImage("/upload/shop/"+file.getOriginalFilename());
		}
		vo.setCode(sdf.format(new Date()));
		dao.insert(vo);
	}
	
	@RequestMapping("/read/{code}")
	public ShopVO read(@PathVariable String code){
		return dao.read(code);
	}
	
	@RequestMapping(value="/addAttach", method=RequestMethod.POST)
	public void addAttach(String code, MultipartHttpServletRequest multi)throws Exception{
		List<MultipartFile> files=multi.getFiles("files");
		
		String path="/upload/shop/"+code;
		File filePath=new File("c:"+path);
		if(!filePath.exists()) filePath.mkdir();
		
		for(MultipartFile file:files){
			if(!file.isEmpty()){
				String strFile=file.getOriginalFilename();
				File newFile=new File("c:/"+path+"/"+strFile);
				if(!newFile.exists()){
					file.transferTo(newFile);
					dao.addAttach(code, path+"/"+strFile);
				}
			}
		}
	}
	
	@RequestMapping("/getAttach")
	public List<HashMap<String, Object>> getAttach(String code){
		return dao.getAttach(code);
	}
	
	@RequestMapping(value="/delAttach", method=RequestMethod.POST)
	public void delAttach(int id, String image){
		File file = new File("c:/"+image);
		if(file.exists()){
			file.delete();
		}
		dao.delAttach(id);
	}
	
	@RequestMapping(value="/addSale", method=RequestMethod.POST)
	public void addSale(String code){
		dao.addSale(code);
	}
	
	@RequestMapping("/getSale")
	public List<ShopVO> getSale(){
		return dao.getSale();
	}

}
