package ex04;

import java.util.Scanner;

public class Example3 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		int sel;
		boolean run = true;
		
		System.out.println("안녕하세요. 합계를 구하는 시스템 입니다.");
	
		while(run) {
			
			int sum = 0;
			int num = 0;
			
			System.out.println("밑의 항목중에 선택해주세요.");
			System.out.println("1.합계 2.짝수합계 3.홀수합계 0.종료");
			System.out.print("선택 : ");
			sel = sc.nextInt(); 
			
			if(sel >= 1 && sel <= 3) {
				System.out.print("1 ~ n값중 n값을 선택해주세요. : ");
				num = sc.nextInt();
			}
				
			switch(sel)	{
			case 1 :
				for(int i = 1; i <= num; i++) {
					sum += i;	
				}
				System.out.println("1 ~ " + num + " 까지의 합은 : " + sum + " 입니다.\n");
				break;
			case 2 :
				for(int i = 2; i <= num; i = i+2) {
					sum += i;
				}
				System.out.println("2 ~  " + num + " 까지의 짝수의 합은 : " + sum + " 입니다.\n");
				break;
			case 3 :
				for(int i = 1; i <= num; i = i+2) {
					sum += i;
				}
				System.out.println("1 ~ " + num + " 까지의 홀수의 합은 : " + sum + " 입니다.\n");
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다."); 
				run = false;
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
			}
		}
	}
}
