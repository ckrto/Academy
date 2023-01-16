package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MysqlTest {	
	
	@Autowired
	UserDAO dao;
	
//	@Transactional
	@Test
	public void insert() {
		UserVO vo = new UserVO();
		vo.setUid("ssss");
		vo.setUpass("pass");
		vo.setUname("³ë³ë³ë");
		dao.insert(vo);
	}

}
