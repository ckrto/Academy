package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BoardDAO;
import com.example.domain.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Transactional
	@Override
	public BoardVO read(int bno) {
		dao.updateViewcnt(bno);
		return dao.read(bno);
	}

}
