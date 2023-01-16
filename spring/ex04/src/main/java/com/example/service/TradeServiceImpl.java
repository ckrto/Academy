package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AccountDAO;
import com.example.dao.TradeDAO;
import com.example.domain.TradeVO;

@Service
public class TradeServiceImpl implements TradeService{
	@Autowired
	AccountDAO adao;
	
	@Autowired
	TradeDAO tdao;
	
	@Transactional
	@Override
	public void trade(TradeVO vo) {
		//������ �ܾ׺���
		adao.updateAmount(vo.getAno(), -1*vo.getAmount());
		//��ü���� �ܾ׺���
		adao.updateAmount(vo.getTno(), vo.getAmount());
		
		//������ �ŷ������Է�
		vo.setType("���");
		tdao.insert(vo);
		
		//�������� �ŷ������Է�
		String ano=vo.getTno();
		String tno=vo.getAno();
		vo.setType("�Ա�");
		vo.setAno(ano);
		vo.setTno(tno);
		tdao.insert(vo);
	}

}
