package ex04;

import java.util.Scanner;

public class Example1 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in); // 입력받기 위해 사용하는 클래스
		
		String name; // 입력받은 이름을 저장하기 위해 name이라는 string 변수 생성
		String grade = ""; // 성적을 저장하기 위해 grade라는 string 변수 생성
		int kor = 0; // 국어 점수를 저장하기 위해 kor라는 int 변수 생성 초기값을 0으로 지정
		int eng = 0; // 영어 점수를 저장하기 위해 kor라는 int 변수 생성 초기값을 0으로 지정
		int mat = 0; // 수학 점수를 저장하기 위해 kor라는 int 변수 생성 초기값을 0으로 지정
		int tot; // 국어 영어 수학 점수의 합을 저장하기 위한 int 변수 생성 
		double avg; // 국어 영어 수학 점수의 합의 평균을 저장하기 위한 double 변수 생성
		
		System.out.println("안녕하세요. 성적관리 시스템입니다.");
		System.out.println("********** 성적표 **********\n");
		
		int sel; // 프로그램 실행 및 종료를 위한 int 변수 생성
		boolean run = true; // 반복문의 실행 및 종료를 위한 boolean 변수 생성
		
		while(run) { // 다수의 값을 입력받고 저장하기 위한 while 반복문 생성
		
			System.out.print("종료는 0, 계속하시려면 1을 입력해주세요. : "); // sel값을 입력받기 위한 출력문
			sel = sc.nextInt(); // 종료 및 계속의 값을 입력받는 변수
			
			// 프로그램 실행 및 종료의 값에 따라 if 조건문 실행
			if(sel == 0) { // 프로그램 실행 및 종료 값이 0일 때 아래의 코드 수행
				System.out.println("프로그램을 종료합니다."); // 프로그램 실행 및 종료 값이 0일 때 출력하는 출력문
				run = false; // boolean의 변수를 false로 바꿔 반복문 종료
			}
			else { // 프로그램 실행 및 종료 값이 0이 아닐 때 아래의 코드 수행

				System.out.print("이름을 입력해주세요 : "); // name값을 사용자에게 입력받기 위한 출력문
				name = sc.next(); // 사용자에게 입력받은 이름을 name에 저장
		
				System.out.print("국어 점수를 입력해주세요 : "); // kor값을 사용자에게 입력받기 위한 출력문
				kor = sc.nextInt(); // 사용자에게 입력받은 이름을 kor에 저장
			
				System.out.print("영어 점수를 입력해주세요 : "); // eng값을 사용자에게 입력받기 위한 출력문
				eng = sc.nextInt(); // 사용자에게 입력받은 이름을 eng에 저장
				
				System.out.print("수학 점수를 입력해주세요 : "); // mat값을 사용자에게 입력받기 위한 출력문
				mat = sc.nextInt(); // 사용자에게 입력받은 이름을 mat에 저장
				
				System.out.println("----------------------------");
				
				tot = kor + eng + mat; // 총점을 구하기 위해 위에서 저장된 kor, eng, mat의 값을 모두 더함
				System.out.println("총점은 : " + tot + "점 입니다."); // 저장된 tot 값을 출력
				
				avg = tot / (double)3; // 평균을 구하기 위해 위에서 저장된 총점을 과목의 수만큼 나눔
				System.out.println("평균은 : " + avg + "점 입니다."); // 저장된 avg 값을 출력
				
				// 등급을 구하기 위한 if 조건문 생성
				if(avg >= 95) { // 95점 이상일때 grade 등급 변수에 A+를 저장
					grade = "A+";
				}
				else if(avg >= 90) { // 95점 이상일때 grade 등급 변수에 A를 저장
					grade = "A";
				}
				else if(avg >= 85) { // 95점 이상일때 grade 등급 변수에 B+를 저장
					grade = "B+";
				}
				else if(avg >= 80) { // 95점 이상일때 grade 등급 변수에 B를 저장
					grade = "B";
				}
				else if(avg >= 75) { // 95점 이상일때 grade 등급 변수에 C+를 저장
					grade = "C+";
				}
				else if(avg >= 70) { // 95점 이상일때 grade 등급 변수에 C를 저장
					grade = "C";
				}
				else if(avg >= 65) { // 95점 이상일때 grade 등급 변수에 D+를 저장
					grade = "D+";
				}
				else if(avg >= 60) { // 95점 이상일때 grade 등급 변수에 D를 저장
					grade = "D";
				}
				else {
					grade = "F"; // 95점 이상일때 grade 등급 변수에 F를 저장
				}
				System.out.println("등급은 : " + grade + " 입니다."); // 위의 조건문에서 저장된 grade 등급의 값을 출력
			}
		}	
	}
}

