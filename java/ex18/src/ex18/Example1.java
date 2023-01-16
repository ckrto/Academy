package ex18;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		AccountDAO accountdao = new AccountDAO();
		AccountVO accountvo = null;
		StatementDAO statementdao = new StatementDAO();
		StatementVO statementvo = null;
		
		int menu;
		int ano;
		int maxCode;
		int balance;
		int price;
		
		String sel;
		String aname;
		
		boolean run = true;
		
		while(run) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.계좌조회 | 4.입금 | 5.출금 | 0.종료");
			System.out.println("-----------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 : 
				maxCode = accountdao.getMaxCode();
				System.out.println("생성하실 귀하의 계좌의 계좌번호는 " + maxCode + " 입니다.");
				
				System.out.print("등록하실 계좌주명을 입력해주세요. : ");
				aname = sc.nextLine();
				
				if(aname.equals("")) {
					System.out.println("\n계좌생성을 취소합니다.\n");
					break;
				}
				
				balance = Input.input("초기입금액을 입력해주세요. : ");
				System.out.println("");
				
				if(balance == 0) {
					System.out.println("초기입금액을 입금해주셔야합니다.\n");
				} else {
					System.out.print("등록하시겠습니까(예 : Y | 아니요 : N)? ");
					sel = sc.nextLine();
					System.out.println("");
					
					if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
						accountvo = new AccountVO();
						accountvo.setAno(maxCode);
						accountvo.setAname(aname);
						accountvo.setBalance(balance);
						accountvo.setOdate(sdf.format(new Date()));
						accountdao.insert(accountvo);
						System.out.println("새로운 계좌가 등록되었습니다.");
					} 
					else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
						System.out.println("등록이 취소되었습니다.");
					} 
					else {
						System.out.println("다시 선택해주세요.");
					}
				}
				System.out.println("");
				break;
			case 2 :
				ArrayList<AccountVO> Accountarray = accountdao.list();
				System.out.println("계좌번호\t계좌주명\t잔액\t계좌생성일");
				for(AccountVO account : Accountarray) {
					System.out.printf("%d\t%s\t%d\t%s\n", account.getAno(), account.getAname(), account.getBalance(), account.getOdate());
				}
				System.out.println("");
				break;
			case 3 :
				ano = Input.input("조회하실 계좌번호를 입력해주세요. : ");
				accountvo = accountdao.read(ano);
				System.out.println("");
				
				if(accountvo.getAname() == null) {
					System.out.println("검색하신 계좌번호가 존재하지 않습니다.\n");
					break;
				} else {
					System.out.println("계좌번호\t계좌주명\t잔액\t계좌생성일");
					System.out.printf("%d\t%s\t%d\t%s\n", accountvo.getAno(), accountvo.getAname(), accountvo.getBalance(), accountvo.getOdate());
					ArrayList<StatementVO> statementarray = statementdao.list(ano);
					System.out.println("\n--------------------입출금 내역--------------------");
					System.out.println("계좌번호\t입출금일\t타입\t금액");
					for(StatementVO s : statementarray) {
						System.out.printf("%d\t%s\t%s\t%d\n", s.getAno(), s.getOdate(), s.getType(), s.getAmount());
					}
					System.out.println("");
				}
				
				System.out.println("");
				break;
			case 4 :
				ano = Input.input("입금을 진행하실 계좌번호를 입력해주세요. : ");
				accountvo = accountdao.read(ano);
				System.out.println("계좌주명 : " + accountvo.getAname());
				System.out.println("현재잔액 : " + accountvo.getBalance());
				price = Input.input("입금하실 금액을 입력해주세요. : ");
				
				System.out.println("");
				
				System.out.print("입금하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					balance = price + accountvo.getBalance();
					accountdao.update(ano, balance);
					System.out.println("입금이 완료되었습니다.\n");
					System.out.println("입금후 잔액은 : " + balance + " 입니다.");
					System.out.println("");
					
					statementvo = new StatementVO();
					statementvo.setAno(ano);
					statementvo.setOdate(sdf.format(new Date()));
					statementvo.setType("입금");
					statementvo.setAmount(price);
					statementdao.insert(statementvo);
					
					ArrayList<StatementVO> statementarray = statementdao.list(ano);
					System.out.println("--------------------입출금 내역--------------------");
					System.out.println("계좌번호\t입출금일\t타입\t금액");
					for(StatementVO s : statementarray) {
						System.out.printf("%d\t%s\t%s\t%d\n", s.getAno(), s.getOdate(), s.getType(), s.getAmount());
					}
					System.out.println("");
					
				} 
				else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("등록이 취소되었습니다.");
				} 
				else {
					System.out.println("다시 선택해주세요.");
				}

				break;
			case 5 :
				ano = Input.input("출금을 진행하실 계좌번호를 입력해주세요. : ");
				accountvo = accountdao.read(ano);
				System.out.println("계좌주명 : " + accountvo.getAname());
				System.out.println("현재잔액 : " + accountvo.getBalance());
				price = Input.input("출금하실 금액을 입력해주세요. : ");
				
				System.out.println("");
				
				System.out.print("등록하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					balance = accountvo.getBalance() - price;
					accountdao.update(ano, balance);
					
					statementvo = new StatementVO();
					statementvo.setAno(ano);
					statementvo.setOdate(sdf.format(new Date()));
					statementvo.setType("출금");
					statementvo.setAmount(price);
					statementdao.insert(statementvo);
					
					ArrayList<StatementVO> statementarray = statementdao.list(ano);
					System.out.println("--------------------입출금 내역--------------------");
					System.out.println("계좌번호\t입출금일\t타입\t금액");
					for(StatementVO s : statementarray) {
						System.out.printf("%d\t%s\t%s\t%d\n", s.getAno(), s.getOdate(), s.getType(), s.getAmount());
					}
					System.out.println("");
					
					System.out.println("출금 후 잔액은 : " + balance + " 입니다.");
					System.out.println("");
					
					System.out.println("출금이 완료되었습니다.\n");
				} 
				else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("등록이 취소되었습니다.");
				} 
				else {
					System.out.println("다시 선택해주세요.");
				}
				
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
		}
	}
}
