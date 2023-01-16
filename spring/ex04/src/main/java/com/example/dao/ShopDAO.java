package com.example.dao;

import java.util.HashMap;
import java.util.List;

import com.example.domain.ShopVO;

public interface ShopDAO {
	public void insert(ShopVO vo);
	public List<ShopVO> list();
	public ShopVO read(String code);
	public void attach(String code, String image);
	public List<HashMap<String,Object>> getAttach(String code);
	public void delAttach(int id);
}
