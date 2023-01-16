package com.example.dao;

import java.util.List;

import com.example.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> list(int bno);
	public void insert(ReplyVO vo);
	public void delete(int rno);
	public ReplyVO read(int rno);
}
