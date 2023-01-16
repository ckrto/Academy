package com.example.dao;

import java.util.List;

import com.example.domain.MenuVO;

public interface MenuDAO {
	public void insert(MenuVO vo);
	public MenuVO read(String s_code, String m_name);
	public List<MenuVO> list(String s_code);
	public void update(MenuVO vo);
	public void delete(String s_code, String m_name);
}
