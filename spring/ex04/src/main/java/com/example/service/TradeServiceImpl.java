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
		//내계좌 잔액변경
		adao.updateAmount(vo.getAno(), -1*vo.getAmount());
		//이체계좌 잔액변경
		adao.updateAmount(vo.getTno(), vo.getAmount());
		
		//내계좌 거래정보입력
		vo.setType("출금");
		tdao.insert(vo);
		
		//이제계좌 거래정보입력
		String ano=vo.getTno();
		String tno=vo.getAno();
		vo.setType("입금");
		vo.setAno(ano);
		vo.setTno(tno);
		tdao.insert(vo);
	}

}
