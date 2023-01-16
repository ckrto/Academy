package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.StoreVO;
import com.example.domain.UserVO;

@Repository
public class StoreDAOImpl implements StoreDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.StoreMapper";
	
	@Override
	public void insert(StoreVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public StoreVO read(String s_code) {
		return session.selectOne(namespace + ".read" , s_code);
	}

	@Override
	public List<StoreVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public void update(StoreVO vo) {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(String s_code) {
		session.delete(namespace + ".delete" , s_code);	
	}

	@Override
	public void updateBtn(String s_code) {
		session.update(namespace + ".updateBtn" , s_code);
		
	}

}
