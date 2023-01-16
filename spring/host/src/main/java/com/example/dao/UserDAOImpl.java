package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.UserMapper";
	
	@Override
	public void insert(UserVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public UserVO read(String u_code) {
		return session.selectOne(namespace + ".read" , u_code);
	}

	@Override
	public List<UserVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public void update(UserVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(String u_code) {
		session.delete(namespace + ".delete" , u_code);	
	}
}
