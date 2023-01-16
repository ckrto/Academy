package ex10;

import java.util.ArrayList;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		AccountVO vo = null;
		AccountDAO dao = new AccountDAO();
		ArrayList<AccountVO> array = dao.list();
		
		int menu;
		int no = 400;
		int ino;
		int eno;
		int amount;
		
		String sno;
		
		boolean run = true;
		
		while(run) {
			System.out.println("---------------------------------------------------");
			System.out.println("1.계좌생성 | 2.잔액조회 | 3.입금 | 4.출금 | 5.계좌목록 | 0.종료");
			System.out.println("---------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			
			System.out.println("");
			
			switch(menu) {
			case 1 :
				vo = new AccountVO();
				
				System.out.println("귀하의 계좌번호는 " + no + " 입니다.");
				vo.setNo(no);
				System.out.print("이름을 입력해주세요 : ");
				vo.setName(sc.nextLine());
				System.out.print("초기 입금액을 입력해주세요. : ");
				vo.setBalance(Integer.parseInt(sc.nextLine()));
				
				dao.insert(vo, array);
				
				System.out.println("계좌가 등록되었습니다.\n");
				break;
			case 2 :
				System.out.print("조회하실 계좌번호를 입력해주세요 : ");
				sno = sc.nextLine();
				System.out.println("");
				vo = dao.read(Integer.parseInt(sno), array);
				
				if(vo.getName() == null) {
					System.out.println("등록되지않은 계좌번호입니다.");
				}
				else {
					System.out.println("계좌주명 : " + vo.getName());
					System.out.println("잔액 : " + vo.getBalance());
				}
				
				System.out.println("");
				break;
			case 3 : 
				System.out.print("입금을 진행하실 계좌번호를 입력해주세요 : ");
				ino = sc.nextInt(); sc.nextLine();
				vo = dao.read(ino, array);
				
				if(vo.getName() == null) {
					System.out.println("해당계좌가 존재하지 않습니다.");
				}
				else {
					System.out.print("입금할 금액을 입력해주세요 : ");
					amount = sc.nextInt(); sc.nextLine();
					dao.trade(ino, amount, array);
					System.out.println("");
					
					System.out.println("입금이 완료되었습니다.\n");
					
					vo = dao.read(ino, array);
					System.out.println("현재 잔액은 " + vo.getBalance() + "원 입니다.\n");
				}
				break;
			case 4 :
				System.out.print("출금을 진행하실 계좌번호를 입력해주세요 : ");
				eno = sc.nextInt(); sc.nextLine();
				vo = dao.read(eno, array);
				
				if(vo.getName() == null) {
					System.out.println("해당계좌가 존재하지 않습니다.");
				}
				else {
					System.out.print("출금할 금액을 입력해주세요 : ");
					amount = sc.nextInt(); sc.nextLine();
					
					if(amount > vo.getBalance()) {
						System.out.println("계좌에 현재잔액보다 출금하려고 하시는 금액이 더 많습니다.\n");
					}
					else {
						dao.trade(eno, -amount, array);
						System.out.println("");
						
						System.out.println("출금이 완료되었습니다.\n");
						
						vo = dao.read(eno, array);
						System.out.println("현재 잔액은 " + vo.getBalance() + "원 입니다.\n");
					}
				}
				break;
			case 5 :
				for(AccountVO v:array) {
					System.out.println(v.toString());
				}
				System.out.println("");
				System.out.println("등록된 전체 계좌의 수는 " + array.size() + "개 입니다.\n");
				break;
			case 0 : 
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
				break;
			}
			
		}
		
	}
}
