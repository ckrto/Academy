package ex03;

import java.util.Scanner;

public class Repeat {
	public static void execute() {
		// 반복문 (for)
		System.out.println("---------- 반복문 (for) ----------");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("안녕하세요. n ~ 100까지의 숫자를 구하는 시스템입니다.");
		System.out.println("1 ~ 100까지의 숫자를 입력하세요 : ");
		int num = sc.nextInt();
		
		int sum = 0;
		for(int i = num; i <= 100; i++) {
			sum += i; 
		}
		System.out.println(num + " ~ 100까지의 합 = " + sum);
		
		int tot = 0;
		for(int i = 2; i <= 100; i=i+2) {
			tot += i; 
		}
		System.out.println("2 ~ 100까지의 짝수의 합 = " + tot);
		
		tot = 0;
		for(int i = 1; i <= 100; i = i+2) {
			tot += i;
		}
		System.out.println("1 ~ 100까지의 홀수의 합 = " + tot + "\n");
		
		// 반복문 (while)
		System.out.println("---------- 반복문 (while) ----------");
		
		int i = 1;
		sum = 0;
		while(i <= 100) {
			sum = sum + i;
			i = i + 1;
		}
		System.out.println("1 ~ 100까지의 합 = " + sum);
		
		sum = 0;
		i = 2;
		while(i <= 100) {
			sum = sum + i;
			i = i + 2;
		}
		System.out.println("2 ~ 100까지의 짝수의 합 = " + sum);
		
		sum = 0;
		i = 1;
		while(i <= 100) {
			sum = sum + i;
			i = i + 2;
		}
		System.out.println("1 ~ 100까지의 홀수의 합 = " + sum);
	}	
}