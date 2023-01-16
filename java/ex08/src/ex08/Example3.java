package ex08;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("#,###원");
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Account> array = new ArrayList<>();
		ArrayList<Trans> tarray = new ArrayList<>();
		
		String date;
		String bal;
		String amount;
	
		int no;
		int sno;
		int menu;
		int balance;
		int input = 0;
		int withdraw = 0;
		
		boolean find = false;
		boolean run = true;
		
		Account acc = new Account();
		
		no = 100;
		acc.setNo(no);
		acc.setName("홍길동");
		acc.setBalance(1000);
		acc.setOpenDate(new Date());
		array.add(acc);
		
		Trans tr = new Trans();
		tr.setNo(no);
		tr.setType("입금");
		tr.setAmount(1000);
		tr.setDate(new Date());
		tarray.add(tr);
		
		acc = new Account();
		no = no + 100;
		acc.setNo(no);
		acc.setName("심청이");
		acc.setBalance(2000);
		acc.setOpenDate(new Date());
		array.add(acc);
		

		tr = new Trans();
		tr.setNo(no);
		tr.setType("입금");
		tr.setAmount(2000);
		tr.setDate(new Date());
		tarray.add(tr);
		
		System.out.println("안녕하세요. 통장 관리 시스템입니다. 아래 항목 중 실행할 작업을 선택해주세요.");
		
		while(run) {
			System.out.println("------------------------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 0.종료");
			System.out.println("------------------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 :
				acc = new Account();
				no = no + 100;
				System.out.println("안녕하세요. 계좌 생성 탭입니다.\n");
				System.out.printf("귀하의 계좌번호는 : %d 입니다.\n", no);
				acc.setNo(no);
				
				System.out.print("계좌주(이름)를 입력해주세요. : ");
				acc.setName(sc.nextLine());
				
				System.out.print("초기 입금액을 입력해주세요. : ");
				balance = sc.nextInt(); sc.nextLine();
				acc.setBalance(balance);
				
				acc.setOpenDate(new Date());
				array.add(acc);
				
				System.out.println("계좌가 생성되었습니다.");
				System.out.println("");
				break;
			case 2 :
				System.out.println("안녕하세요. 계좌 목록 탭입니다.\n");
				System.out.println("계좌번호\t이름\t시간\t\t\t잔액");
				for(Account ac:array) {
					date = sdf.format(ac.getOpenDate());
					bal = df.format(ac.getBalance());
					System.out.printf("%d\t%s\t%s\t%s\n", ac.getNo(), ac.getName(), date, bal);
				}
				System.out.println("");
				break;
			case 3 :
				find = false;
				
				System.out.println("안녕하세요. 입금 탭입니다.\n");
				System.out.print("계좌번호를 입력해주세요 : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				for(Account ac:array) {
					if(sno == ac.getNo()) {
						find = true;
						System.out.println("계좌주(이름)는 " + ac.getName() + " 입니다.");
						
						bal = df.format(ac.getBalance());
						
						System.out.println("---------------------------------------------");
						System.out.println("구분\t입출금일\t\t\t입출금액");
						System.out.println("---------------------------------------------");
						
						for(Trans ts:tarray) {
							if(sno == ts.getNo()) {
								date = sdf.format(ts.getDate());
								amount = df.format(ts.getAmount());
								System.out.printf("%s\t%s\t%s\n", ts.getType(), date, amount);
							}
						}
						
						System.out.println("");
						System.out.print("입금할 금액을 입력해주세요 : ");
						input = sc.nextInt(); sc.nextLine();
						
						if(input > 0) {
							tr = new Trans();
							tr.setNo(sno);
							tr.setDate(new Date());
							tr.setType("입금");
							tr.setAmount(input);
							tarray.add(tr);
							
							System.out.println("");
							ac.setBalance(ac.getBalance()+input);
							System.out.println("입금이 완료되었습니다.");
						}
						
						
						
					}
				}
				
				if(find == false) {
					System.out.println("등록되지않은 계좌입니다.");
				}
				System.out.println("");
				break;
			case 4 :
				System.out.println("안녕하세요. 출금 탭입니다.\n");
				System.out.print("계좌번호를 입력해주세요 : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				for(Account ac:array) {
					if(sno == ac.getNo()) {
						find = true;
						System.out.println("계좌주(이름)는 " + ac.getName() + " 입니다.");
						
						bal = df.format(ac.getBalance());
						
						System.out.println("---------------------------------------------");
						System.out.println("구분\t입출금일\t\t\t입출금액");
						System.out.println("---------------------------------------------");
						
						for(Trans ts:tarray) {
							if(sno == ts.getNo()) {
								date = sdf.format(ts.getDate());
								amount = df.format(ts.getAmount());
								System.out.printf("%s\t%s\t%s\n", ts.getType(), date, amount);
								
							}
						}
						System.out.println("");
						
						System.out.print("출금할 금액을 입력해주세요 : ");
						withdraw = sc.nextInt(); sc.nextLine();
						
						if(ac.getBalance() < withdraw) {
							System.out.println("");
							System.out.println("출금하시려고 하시는 금액이 계좌에 있는 잔액보다 많습니다.");
							System.out.println("출금에 실패하였습니다.");
						}
						else {
							if(withdraw > 0) {
								tr = new Trans();
								tr.setNo(sno);
								tr.setDate(new Date());
								tr.setType("출금");
								tr.setAmount(withdraw);
								tarray.add(tr);
								System.out.println("");
								
								ac.setBalance(ac.getBalance()-withdraw);
								System.out.println("출금이 완료되었습니다.");
							}
						}
					}
				}
				
				
				if(find == false) {
					System.out.println("등록되지않은 계좌입니다.");
				}
				System.out.println("");
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default : 
				System.out.println("메뉴를 다시 선택해주세요. \n");
				break;
			}
		}
	}
}
