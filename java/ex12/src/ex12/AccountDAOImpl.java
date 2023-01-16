package ex12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AccountDAOImpl implements AccountDAO{
	File file = new File("c:/data/java/ex12/account.txt");
	
	public void trade(int no, int price) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			String lines = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				AccountVO vo = new AccountVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				int balance = Integer.parseInt(st.nextToken());
				vo.setBalance(balance + price);
				
				if(no == vo.getNo()) {
					lines = lines + vo.getNo() + "|" + vo.getName() + "|" + vo.getBalance() + "\n";
				}
				else {
					lines = lines + line + "\n";
				}
			}
			FileWriter writer = new FileWriter(file, false);
			writer.write(lines);
			writer.flush();
			writer.close();
			
		} catch(Exception e) {
			System.out.println("입금 출금 : " + e.toString());
		}
	}
	
	public int getLastNo() {
		int no = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				no = Integer.parseInt(st.nextToken());
			}
		} catch(Exception e) {
			System.out.println("마지막 계좌번호 : " + e.toString());
		}
		return no;
	}
	
	@Override
	public ArrayList<AccountVO> list() {
		ArrayList<AccountVO> array = new ArrayList<AccountVO>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			
			while((line=bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				AccountVO vo = new AccountVO();
				vo.setNo(Integer.parseInt(st.nextToken()));
				vo.setName(st.nextToken());
				vo.setBalance(Integer.parseInt(st.nextToken()));
				array.add(vo);
			}
		} catch(Exception e) {
			System.out.println("목록 : " + e.toString());
		}
		return array;
	}

	@Override
	public void insert(AccountVO vo) {
		try {
			FileWriter writer = new FileWriter(file, true);
			writer.write(vo.getNo() + "|" + vo.getName() + "|" + vo.getBalance() + "\n");
			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.out.println("계좌생성 : " + e.toString());
		}
		
	}

	@Override
	public AccountVO read(int no) {
		AccountVO vo = new AccountVO();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = bf.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "|");
				vo.setNo(Integer.parseInt(st.nextToken()));
				if(no==vo.getNo()) {
					vo.setName(st.nextToken());
					vo.setBalance(Integer.parseInt(st.nextToken()));
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("검색 : " + e.toString());
		}
		return vo;
	}

	@Override
	public void update(AccountVO vo) {

		
	}

	@Override
	public void delete(int no) {

		
	}

}
