package ex05;

import java.util.Scanner;

public class Example1 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		
		String name1 = "홍길동";
		String name2 = "홍길동";
		
		if(name1 == name2) {
			System.out.println("저장된 주소가 같다.");
		} 
		else {
			System.out.println("저장된 주소가 같지 않다.");
		}
		
		if(name1.equals(name2)) {
			System.out.println("저장된 값이 같다.");
		}
		else {
			System.out.println("저장된 값이 같지 않다.");
		}
		
		String name3 = "홍길동";
		String name4 = "홍길동";
		
		if(name3 == name4) {
			System.out.println("저장된 주소가 같다.");
		} 
		else {
			System.out.println("저장된 주소가 같지 않다.");
		}
		
		if(name3.equals(name4)) {
			System.out.println("저장된 값이 같다.");
		}
		else {
			System.out.println("저장된 값이 같지 않다.");
		}
	}
}
