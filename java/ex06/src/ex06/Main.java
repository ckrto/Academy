package ex06;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
			
		String name;
			
		int menu;
				
		boolean run = true;
				
		while(run) {
			System.out.println("안녕하세요 오버로딩 연습 시스템입니다.");
			System.out.println("아래의 항목 중 선택하여 주세요.");
			System.out.println("-----------------------");
			System.out.println("1.생성자 오버로딩 연습");
			System.out.println("2.자동차 관리 시스템");
			System.out.println("3.성적관리 시스템");
			System.out.println("4.상품관리 시스템");
			System.out.println("-----------------------");				
			System.out.print("선택해 주세요. : ");
			menu = sc.nextInt();
			
			System.out.println("");
				
				switch(menu) {
				case 1 :
					Example1.execute();
					break;
				case 2 :
					Example2.execute();
					break;
				case 3 :
					Example3.execute();
					break;
				case 4 : 
					Example4.execute();
					break;
				case 0 :
					run = false;
					System.out.println("프로그램을 종료 합니다.");
					break;
					
			}
		}
	}

}
