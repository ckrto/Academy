package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession session;
	String namespace = "com.example.mapper.BoardMapper";

	@Override
	public List<BoardVO> list() {
		return session.selectList(namespace + ".list");
	}

	@Override
	public BoardVO read(int bno) {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void updateViewcnt(int bno) {
		session.update(namespace + ".updateViewcnt", bno);
	}

	@Override
	public void updateReplycnt(int bno, int amount) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("amount", amount);
		session.update(namespace + ".updateReplycnt", map);
	}

}
