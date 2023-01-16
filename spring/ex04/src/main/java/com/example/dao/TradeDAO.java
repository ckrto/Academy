package com.example.dao;

import java.util.List;

import com.example.domain.TradeVO;

public interface TradeDAO {
	public List<TradeVO> list(String ano);
	public void insert(TradeVO vo);
}
