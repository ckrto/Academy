package com.example.dao;

import java.util.List;

import com.example.domain.StoreVO;

public interface StoreDAO {
	public void insert(StoreVO vo);
	public StoreVO read(String s_code);
	public List<StoreVO> list();
	public void update(StoreVO vo);
	public void delete(String s_code);
	public void updateBtn(String s_code);
}
