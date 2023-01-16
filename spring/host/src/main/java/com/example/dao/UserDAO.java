package com.example.dao;

import java.util.List;

import com.example.domain.UserVO;

public interface UserDAO {
	public void insert(UserVO vo);
	public UserVO read(String u_code);
	public List<UserVO> list();
	public void update(UserVO vo);
	public void delete(String u_code);
}
