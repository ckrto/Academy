package com.example.dao;

import java.util.List;

import com.example.domain.AccountVO;

public interface AccountDAO {
	public List<AccountVO> list();
	public AccountVO read(String ano);
	public void updateAmount(String ano, double amount);
}
