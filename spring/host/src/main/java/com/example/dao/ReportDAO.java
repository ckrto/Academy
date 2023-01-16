package com.example.dao;

import java.util.List;

import com.example.domain.ReportVO;

public interface ReportDAO {
	public void insert(ReportVO vo);
	public ReportVO read(String r_code);
	public List<ReportVO> list();
	public void update(ReportVO vo);
	public void delete(String r_code);
}
