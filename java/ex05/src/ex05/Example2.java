package ex05;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		DecimalFormat df = new DecimalFormat("###.00");
		Scanner sc = new Scanner(System.in);
		
		String[] name = new String[100];
		String[] sub = {"국어", "영어", "수학"};
		String search = "";
		String subject = "";
		int[] kor = new int[100];
		int[] eng = new int[100];
		int[] mat = new int[100];
		int cnt = 0;
		int tot = 0;
		int sel = 0;
		double avg;
		boolean run = true;
		boolean searchCheck = false;
		boolean subjectCheck = false;
		
		while(run) {
			
			System.out.println("안녕하세요 점수 관리 시스템 입니다. 아래의 작업들 중 원하시는 작업을 선택해주세요");
			System.out.println("---------------------------------------------------");
			System.out.println("1.성적입력 | 2.성적검색 | 3.성적수정 | 4.성적표출력 | 0.종료");
			System.out.println("---------------------------------------------------");
			System.out.print("실행할 작업 : ");
			sel = sc.nextInt();
			System.out.print("\n");
			
			
			
			switch(sel) {
			case 1 :
				System.out.print("입력할 학생 수를 입력해주세요 : ");
				cnt = sc.nextInt();
				
				for(int i = 0; i < cnt; i++) {
					System.out.print("이름을 입력해주세요 : ");
					name[i] = sc.next();
				}
				System.out.print("\n");
			
				for(int i = 0; i < cnt; i++) {
					System.out.print(name[i] + "의 국어 점수를 입력해주세요 : ");
					kor[i] = sc.nextInt();
				}
				System.out.print("\n");
			
				for(int i = 0; i < cnt; i++) {
					System.out.print(name[i] + "의 영어 점수를 입력해주세요 : ");
					eng[i] = sc.nextInt();
				}
				System.out.print("\n");
			
				for(int i = 0; i < cnt; i++) {
					System.out.print(name[i] + "의 수학 점수를 입력해주세요 : ");
					mat[i] = sc.nextInt();
				}
				System.out.print("\n");
				break;
				
			case 2 : 
				System.out.print("이름을 입력해주세요 : ");
				search = sc.next();
				System.out.print("\n");
				
				for(int i = 0; i < cnt; i++) {
					if(search.equals(name[i])) {
						searchCheck = true;
						
						System.out.print("검색하실 과목의 이름을 입력해주세요 : ");
						subject = sc.next();
						
						if(subject.equals(sub[0])) {
							subjectCheck = true;
							System.out.println(search +"님의 " + sub[0] + " 성적은 " + kor[i] + "입니다." + "\n");
						}
						else if(subject.equals(sub[1])) {
							subjectCheck = true;
							System.out.println(search +"님의 " + sub[1] + " 성적은 " + eng[i] + "입니다." + "\n");
						}
						else if(subject.equals(sub[2])) {
							subjectCheck = true;
							System.out.println(search +"님의 " + sub[2] + " 성적은 " + mat[i] + "입니다." + "\n");
						}
					}
					else if(!search.equals(name[i]) && !subject.equals(sub[i])) {
						searchCheck = false;
					}
				}
				if(searchCheck == false) {	
					System.out.println("그런 이름은 존재하지 않습니다.\n");
				}
				break;
				
			case 3:
				System.out.print("이름을 입력해주세요 : ");
				search = sc.next();
				System.out.print("점수를 수정하실 과목을 입력해주세요 : ");
				subject = sc.next();
				
				for(int i = 0; i < cnt; i++) {
					if(search.equals(name[i])) {
						searchCheck = true;
						if(subject.equals(sub[0])) {
							subjectCheck = true;
							System.out.print(name[i] + "님의 수정하실 국어 점수를 입력해주세요 : \n");
							kor[i] = sc.nextInt();
							System.out.println(search +"님의 수정하신 " + sub[0] + " 성적은 " + kor[i] + "입니다." + "\n");
						}
						else if(subject.equals(sub[1])) {
							subjectCheck = true;
							System.out.print(name[i] + "님의 수정하실 영어 점수를 입력해주세요 : ");
							eng[i] = sc.nextInt();
							System.out.println(search +"님의 수정하신 " + sub[1] + " 성적은 " + eng[i] + "입니다." + "\n");
						}
						else if(subject.equals(sub[2])) {
							subjectCheck = true;
							System.out.print(name[i] + "님의 수정하실 수학 점수를 입력해주세요 : ");
							mat[i] = sc.nextInt();
							System.out.println(search +"님의 수정하신" + sub[2] + " 성적은 " + mat[i] + "입니다." + "\n");
						}
					}
					else if(!search.equals(name[i]) && !subject.equals(sub[i])) {
						searchCheck = false;
					}
				}
				if(searchCheck == false) {	
					System.out.println("그런 이름은 존재하지 않습니다.\n");
				}
				break;
				
			case 4 : 
				System.out.print("이름을 입력해주세요 : ");
				search = sc.next();
				System.out.print("\n");
				
				for(int i = 0; i < cnt; i++) {
					if(search.equals(name[i])) {
						tot = kor[i] + eng[i] + mat[i];
						avg = tot / (double) 3;
						
						System.out.println(search + " 님의 최종 성적표는 아래와 같습니다.");
						System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
						
						System.out.print(name[i] + "\t");
						System.out.print(kor[i] + "\t");
						System.out.print(eng[i] + "\t");
						System.out.print(mat[i] + "\t");
						System.out.print(tot + "\t");
						System.out.println(df.format(avg) + "\n");
					}
					else if(!search.equals(name[i]) && !subject.equals(sub[i])) {
						searchCheck = false;
					}
				}
				if(searchCheck == false) {	
					System.out.println("그런 이름은 존재하지 않습니다.\n");
				}
				break;
				
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
				
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
			}
		}
	}
}
