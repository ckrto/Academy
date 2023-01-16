package ex04;

import java.util.ArrayList;
import java.util.Scanner;

public class Example4 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> arrStudent = new ArrayList<>();
		ArrayList<Score> arrScore = new ArrayList<>();
		
		String type;
		
		int grade;
		int menu;
		int no;
		int sno;
		int sum = 0;
		int count = 0;
		double avg;
		
		boolean run = true;
		boolean find = false;
		
		no = 100;
		Student student = new Student();
		student.setNo(no);
		student.setName("홍길동");
		student.setAvg(90);
		arrStudent.add(student);
		
		Score score = new Score();
		score.setNo(no);
		score.setType("중간");
		score.setGrade(70);
		arrScore.add(score);
		
		score = new Score();
		score.setNo(no);
		score.setType("기말");
		score.setGrade(90);
		arrScore.add(score);
		
		no = no + 100;
		student = new Student();
		student.setNo(no);
		student.setName("심청이");
		student.setAvg(45);
		arrStudent.add(student);
		
		System.out.println("안녕하세요. 성적 관리 시스템입니다.");
		
		while(run) {
			System.out.println("-------------------------------");
			System.out.println("1.성적등록 | 2.성적검색 | 0.종료");
			System.out.println("-------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu)	{
			case 1 :
				find = false;
				
				System.out.println("안녕하세요. 성적 등록 탭입니다.");
				System.out.print("성적을 등록하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				for(Student st:arrStudent) {
					if(sno == st.getNo()) {
						find = true;
						System.out.println("학생 이름 : " + st.getName());
						System.out.println("평균 : " + st.getAvg());
						
						System.out.println("----------------");
						System.out.println("유형\t점수");
						System.out.println("----------------");
						
						for(Score sco:arrScore) {
							if(sno==sco.getNo()) {
								System.out.printf("%s\t%d\n", sco.getType(), sco.getGrade());
								count++;
								sum = sum + sco.getGrade();
							}
						}
						System.out.println("");
						
						System.out.print("시험의 유형을 입력해주세요 : ");
						type = sc.nextLine();
								
						System.out.print("점수를 입력해주세요 : ");
						grade = sc.nextInt(); sc.nextLine();
						System.out.println("");
								
						score = new Score();
						score.setNo(sno);
						score.setType(type);
						score.setGrade(grade);
						arrScore.add(score);
						
						sum = sum + grade;
						avg = sum/(count+1);
						st.setAvg(avg);
						
						System.out.println("성적 등록이 완료되었습니다.\n");
					}
				}
				if(find == false) {
					System.out.println("등록된 학생이 없습니다.");
				}
				break;
			case 2 :
				find = false;
				
				System.out.println("안녕하세요. 성적 검색 탭입니다.");
				System.out.print("검색하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				for(Student st:arrStudent) {
					if(sno == st.getNo()) {
						find = true;
						System.out.println("학생 이름 : " + st.getName());
						System.out.println("평균 : " + st.getAvg());
						
						System.out.println("----------------");
						System.out.println("유형\t점수");
						System.out.println("----------------");
						
						for(Score sco:arrScore) {
							if(sno==sco.getNo()) {
								System.out.printf("%s\t%d\n", sco.getType(), sco.getGrade());
							}
						}
						System.out.println("");
					}
				}
				if(find == false) {
					System.out.println("등록된 학생이 없습니다.");
				}
				
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default : 
				System.out.println("메뉴를 다시 선택해주세요. \n");
				break;
			}
		}
		
	}
}
