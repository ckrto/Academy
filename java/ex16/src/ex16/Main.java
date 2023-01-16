package ex16;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run) {
			System.out.println("-------------------------");
			System.out.println("1.학과관리");
			System.out.println("2.시험타입관리");
			System.out.println("3.학생관리");
			System.out.println("4.성적관리");
			System.out.println("0.프로그램 종료");
			System.out.println("-------------------------");
			
			int menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("\n");
			
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
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
			
		}
	}
}
