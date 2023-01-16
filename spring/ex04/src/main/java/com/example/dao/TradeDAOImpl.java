package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.TradeVO;

@Repository
public class TradeDAOImpl implements TradeDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.TradeMapper";
	
	@Override
	public List<TradeVO> list(String ano) {
		return session.selectList(namespace + ".list", ano);
	}

	@Override
	public void insert(TradeVO vo) {
		session.insert(namespace + ".insert", vo);
	}
}
