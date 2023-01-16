package com.example.dao;

import java.util.List;

import com.example.domain.ShopVO;

public interface ShopDAO {
	public void insert(ShopVO vo);
	public List<ShopVO> list();
	public ShopVO read(String code);
	public void update(ShopVO vo);
}
