package ex03;

import java.util.Scanner;

public class Example2 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("안녕하세요 성적관리 시스템입니다.\n");
		
		System.out.println("국어 점수를 입력해주세요.");
		int kor = sc.nextInt();
		
		System.out.println("영어 점수를 입력해주세요.");
		int eng = sc.nextInt();
		
		System.out.println("수학 점수를 입력해주세요.");
		int mat = sc.nextInt();
		
		double avg = (kor + mat + eng) / 3.;
		
		System.out.println("---------------------------------");
		System.out.println("평균은 : " + avg + " 입니다.");
		
		if(avg >= 95) {
			System.out.println("등급은 : A+ 입니다.");
			System.out.println("완벽합니다!!");
		}
		else if(avg >= 90) {
			System.out.println("등급은 : A 입니다.");
			System.out.println("훌륭합니다!!");
		}
		else if(avg >= 85) {
			System.out.println("등급은 : B+ 입니다.");
			System.out.println("대단합니다!!");
		}
		else if(avg >= 80) {
			System.out.println("등급은 : B 입니다.");
			System.out.println("잘했습니다!!");
		}
		else if(avg >= 75) {
			System.out.println("등급은 : C+ 입니다.");
			System.out.println("아쉽습니다 노력해주세요!!");
		}
		else if(avg >= 70) {
			System.out.println("등급은 : C 입니다.");
			System.out.println("더 노력해주세요!!");
		}
		else if(avg >= 65) {
			System.out.println("등급은 : D+ 입니다.");
			System.out.println("복습과 예습을 확실히 해주세요!!");
		}
		else if(avg >= 60) {
			System.out.println("등급은 : D 입니다.");
			System.out.println("기초를 다시 한번 다져주세요!!");
		}
		else {
			System.out.println("등급은 : F 입니다.");
			System.out.println("재수강 할 때는 제대로 해주세요!!");
		}		
	}
}
