package ex10;

import java.util.ArrayList;

public class AccountDAO {
	//목록
	public ArrayList<AccountVO> list() {
		ArrayList<AccountVO> array = new ArrayList<AccountVO>();
		
		AccountVO vo = new AccountVO();
		vo.setNo(100);
		vo.setName("홍길동");
		vo.setBalance(1000);
		array.add(vo);
		
		vo = new AccountVO();
		vo.setNo(200);
		vo.setName("심청이");
		vo.setBalance(1000);
		array.add(vo);
		
		vo = new AccountVO();
		vo.setNo(300);
		vo.setName("강감찬");
		vo.setBalance(1000);
		array.add(vo);
		
		return array;
	}
	
	//검색
	public AccountVO read(int no, ArrayList<AccountVO> array) {
		AccountVO vo = new AccountVO();
		for(AccountVO v : array) {
			if(no == v.getNo()) {
				vo.setNo(v.getNo());
				vo.setName(v.getName());
				vo.setBalance(v.getBalance());
			}
		}
		return vo;
	}
	
	//등록
	public void insert(AccountVO vo, ArrayList<AccountVO> array) {
		array.add(vo);
	}
	
	//입출금
	public void trade(int no, int amount, ArrayList<AccountVO> array) {
		for(AccountVO v : array) {
			if(no == v.getNo()) {
				v.setBalance(v.getBalance() + amount);
			}
		}
	}
}
