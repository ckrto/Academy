package ex03;

import java.util.Scanner;

public class Example1 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("안녕하세요 계산기 프로그램입니다.");
		
		System.out.println("숫자를 입력해주세요");
		int num1 = sc.nextInt();
		
		System.out.println("숫자를 입력해주세요");
		int num2 = sc.nextInt();
		
		int add = num1 + num2;
		int sub = num1 - num2;
		double mul = num1 * num2;
		double div = num1 / num2;
		
		System.out.println("계산하고 싶은 연산자를 선택하세요");
		System.out.println("1.덧셈 2.뺄셈 3.곱셈 4.나눗셈");
		
		int result = sc.nextInt();
		
		switch(result) {
		case 1 : 
			System.out.println("계산결과는 : " + num1 + " + " + num2 + " = " + add);
			break;
		case 2 : 
			System.out.println("계산결과는 : " + num1 + " - " + num2 + " = " + sub);
			break;
		case 3 : 
			System.out.println("계산결과는 : " + num1 + " x " + num2 + " = " + mul);
			break;
		case 4 : 
			System.out.println("계산결과는 : " + num1 + " / " + num2 + " = " + div);
			break;
		default :
			System.out.println("잘못입력하셨습니다!");
			break;
		}
	}
}
