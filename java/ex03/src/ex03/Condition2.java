package ex03;

import java.util.Scanner;

public class Condition2 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		int time = sc.nextInt();
		
		switch(time) {
		case 6 : 
			System.out.println("일어나세요!");
			break;
		case 7 : 
			System.out.println("회사로 출발하세요!");
			break;
		case 13 : 
			System.out.println("점심식사하세요!");
			break;
		case 18 : 
			System.out.println("퇴근하세요!");
			break;
		}
	}
}
