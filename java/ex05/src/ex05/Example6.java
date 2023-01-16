package ex05;

import java.util.Scanner;

public class Example6 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			Calculator c1 = new Calculator();
			System.out.println("계산하시고 싶은 숫자를 입력해주세요.");
			System.out.println("종료는 0을 입력해주세요.");
			
			System.out.print("숫자1 : ");
			c1.num1 = sc.nextInt();
			if(c1.num1 == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			System.out.print("숫자2 : ");
			c1.num2 = sc.nextInt();
			
			c1.execute();
			c1.print();
		}
	}
}
