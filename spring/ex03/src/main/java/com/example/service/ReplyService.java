package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.ReplyVO;

@Service
public interface ReplyService {
	public void insert(ReplyVO vo);
	public void delete(int rno);
}
