package ex04;

import java.util.Scanner;

public class Example2 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		
		String name; // 상품명
		String grade = ""; // 비고
		int price = 0; // 단가
		int quantity = 0; // 수량
		int money; // 판매 금액
		int sel;
		boolean run = true;
		
		System.out.println("상품의 매출 현황 관리 시스템 입니다.");
		System.out.println("********** 상품 매출 현황 **********");
		
		while(run) {
			
			System.out.print("종료는 0, 계속하시려면 1을 입력해주세요. : "); 
			sel = sc.nextInt(); 
			
			if(sel == 0) { 
				System.out.println("프로그램을 종료합니다."); 
				run = false; 
			}
			else { 
				
				System.out.print("상품명을 입력해주세요 : ");
				name = sc.next();
				
				System.out.print("단가를 입력해주세요 : ");
				price = sc.nextInt();
				
				System.out.print("수량을 입력해주세요 : ");
				quantity = sc.nextInt();
				
				System.out.println("--------------------------------");
				
				money = price * quantity;
				System.out.println("판매 금액은 " + money + " 입니다.");
				
				if(money >= 5000) {
					grade = "최우수 입니다.";
				}
				else if(money >= 3000) {
					grade = "우수 입니다.";
				}
				else {
					grade = "보통 입니다.";
				}
				System.out.println("등급은 "+ grade);
			}
		}
	}
}
