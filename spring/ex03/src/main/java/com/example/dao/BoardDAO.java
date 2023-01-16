package com.example.dao;

import java.util.List;

import com.example.domain.BoardVO;

public interface BoardDAO {
	public List<BoardVO> list();
	public BoardVO read(int bno);
	public void updateViewcnt(int bno);
	public void updateReplycnt(int bno, int amount);
}
