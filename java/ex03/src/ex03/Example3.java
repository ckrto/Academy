package ex03;

import java.util.Scanner;

public class Example3 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("단가와 수량을 정리하는 시스템입니다.\n");
		System.out.println("********** 매출 현황 **********");
		System.out.println("프로그램 종료는 0을 입력해주세요.");
		
		while(true) {
			System.out.print("단가를 입력하세요 : ");
			int price = sc.nextInt();
			
			if (price == 0) {
				break;
			} 
			else {
				System.out.print("수량을 입력하세요 : ");
				int quantity = sc.nextInt();
				
				int money = price * quantity;
				System.out.println("금액 : " + money);
				if (money >= 1000000) {
					System.out.println("최우수 입니다!");
				}
				else if (money >= 500000) {
					System.out.println("우수 입니다!");
				}
				else {
					System.out.println("분발해야 합니다!");
				}
			}
		}
		System.out.println("프로그램을 종료합니다!");
	}
}
