package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.MenuVO;

@Repository
public class MenuDAOImpl implements MenuDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.MenuMapper";

	@Override
	public void insert(MenuVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public MenuVO read(String s_code, String m_name) {
		HashMap<String, Object> map=new HashMap<>();
		map.put("m_name", m_name);
		map.put("s_code", s_code);
		return session.selectOne(namespace + ".read" , map);
	}

	@Override
	public List<MenuVO> list(String s_code) {
		return session.selectList(namespace + ".list", s_code);
	}

	@Override
	public void update(MenuVO vo) {
		session.update(namespace + "update" , vo);
		
	}

	@Override
	public void delete(String s_code, String m_name) {
		HashMap<String, Object> map=new HashMap<>();
		map.put("m_name", m_name);
		map.put("s_code", s_code);
		session.delete(namespace + ".delete" , map);
		
	}

}
