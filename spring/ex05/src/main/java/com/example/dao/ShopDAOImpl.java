package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ShopVO;

@Repository
public class ShopDAOImpl implements ShopDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.ShopMapper";
	
	@Override
	public List<ShopVO> list(int page,int num) {
		HashMap<String, Object> map= new HashMap<>();
		map.put("start", (page-1)*num);
		map.put("num", num);
		return session.selectList(namespace+".list",map);
	}

	@Override
	public void insert(ShopVO vo) {
		session.insert(namespace+".insert",vo);
		
	}

	@Override
	public ShopVO read(String code) {
		return session.selectOne(namespace+".read",code);
	}

	@Override
	public void addAttach(String code, String image) {
		HashMap<String, Object> map= new HashMap<>();
		map.put("code", code);
		map.put("image", image);		
		session.insert(namespace+".addAttach",map);
	}

	@Override
	public List<HashMap<String, Object>> getAttach(String code) {
		return session.selectList(namespace+".getAttach",code);
	}

	@Override
	public void delAttach(int id) {
		session.delete(namespace+".delAttach",id);
	}

	@Override
	public int total() {
		return session.selectOne(namespace+".total");
	}

	@Override
	public void addSale(String code) {
		session.insert(namespace+".addSale",code);
		
	}

	@Override
	public List<ShopVO> getSale() {
		return session.selectList(namespace+".getSale");
	}

}
