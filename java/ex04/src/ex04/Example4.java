package ex04;

import java.util.Scanner;

public class Example4 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		int sel;
		int money = 1000;
		int deposit = 0;
		int withdraw = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("안녕하세요 OO은행 ATM기 입니다. 아래의 작업들 중 원하시는 작업을 선택해주세요");
			System.out.println("---------------------------------");
			System.out.println("1.잔액확인 | 2.입금 | 3.출금 | 0.종료");
			System.out.println("---------------------------------");
			
			System.out.print("실행할 작업 : ");
			sel = sc.nextInt();
			
			switch(sel) {
			case 1 :
				System.out.println("귀하의 계좌에 존재하는 금액은 " + money + " 원 입니다.\n");
				break;
			case 2 :
				System.out.println("얼마를 입금하시겠습니까?");
				System.out.print("금액 : ");
				deposit = sc.nextInt();
				
				System.out.println(deposit +"원을 입금하였습니다.");
				money = money + deposit;
				System.out.println("현재 잔액은 " + money + "원 입니다.\n");
				break;
			case 3 :
				System.out.println("얼마를 출금하시겠습니까?");
				System.out.print("금액 : ");
				withdraw = sc.nextInt();
				if(money < withdraw)
				{
					System.out.println("잔액이 부족하여 출금할 수 없습니다.\n");
				}
				else {
					System.out.println(withdraw +"원을 출금하였습니다.");
					money = money - withdraw;
					System.out.println("현재 잔액은 " + money + "원 입니다.\n");
				}
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
			}
		}
		
	}
}
