package com.example.dao;

import com.example.domain.UserVO;

public interface UserDAO {
	public void insert(UserVO vo);
	public UserVO read(String uid);
}
