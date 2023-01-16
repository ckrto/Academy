package ex09;

import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		int a;
		
		while(true) {
			try {
				System.out.print("정수를 입력해주세요 : ");
				a = sc.nextInt();
				if(a==0) {
					break;
				}
				System.out.println("입력한 정수값 : " + a);
			}
			catch (Exception e){ // try를 하다 에러가나면 catch문을 실행하라 
								 // Exception은 에러처리를 위한 클래스(안에 모든 에러처리 방법이 들어있음)
				System.out.println("숫자가 아닙니다.");
				break;
			}
		}
	}
}
