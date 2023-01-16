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
	public List<UserVO> list(int page) {
		int start=(page-1)*5;
		return session.selectList(namespace+".list",start);
	}

	@Override
	public UserVO read(String uid) {
		return session.selectOne(namespace+".read",uid);
	}

	@Override
	public void insert(UserVO vo) {
		session.insert(namespace+".insert",vo);
	}

	@Override
	public void update(UserVO vo) {
		session.update(namespace+".update",vo);
	}

	@Override
	public void updatePassword(UserVO vo) {
		session.update(namespace+".updatePassword",vo);
	}

}
