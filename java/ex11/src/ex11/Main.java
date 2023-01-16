package ex11;

import java.util.Scanner;

import Sale.Example2;
import Student.Example1;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int menu;
		
		boolean run = true;
		
		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1.학생관리 프로그램");
			System.out.println("2.매출관리 프로그램");
			System.out.println("0.프로그램 종료");
			System.out.println("--------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 :
				Example1.execute();
				break;
			case 2 :
				Example2.execute();
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
