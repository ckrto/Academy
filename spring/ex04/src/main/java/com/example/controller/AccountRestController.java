package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AccountDAO;
import com.example.domain.AccountVO;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	AccountDAO adao;
	
	@RequestMapping("/list")
	public List<AccountVO> list(){
		return adao.list();
	}
	
	@RequestMapping("/read/{ano}")
	public AccountVO read(@PathVariable String ano){
		return adao.read(ano);
	}
	
	@RequestMapping(value="/updateAmount", method=RequestMethod.POST)
	public void updateAmount(@RequestBody HashMap<String,Object> map){	
		String ano=(String)map.get("ano");
		double amount=Double.parseDouble((String)map.get("amount"));
		adao.updateAmount(ano, amount);
	}
}
