package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ReportVO;

@Repository
public class ReportDAOImpl implements ReportDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.ReportMapper";
	
	@Override
	public void insert(ReportVO vo) {
		session.insert(namespace + ".insert", vo);	
	}

	@Override
	public ReportVO read(String r_code) {
		return session.selectOne(namespace + ".read" , r_code);
	}

	@Override
	public List<ReportVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public void update(ReportVO vo) {
		session.update(namespace + "update" , vo);	
	}

	@Override
	public void delete(String r_code) {
		session.delete(namespace + ".delete" , r_code);
		
	}

}
