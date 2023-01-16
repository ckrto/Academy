package ex07;

import java.util.ArrayList;
import java.util.Scanner;

public class Example6 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Score> scores = new ArrayList<>();
		
		String type;
		
		int no;
		int sno;
		int kor;
		int eng;
		int mat;
		int menu;
		
		boolean find = true;
		boolean run = true;
		
		no = 10;
		Student student = new Student();
		student.setNo(no);
		student.setName("홍길동");
		student.setDept("컴정");
		students.add(student);
		
		Score score = new Score();
		score.setNo(no);
		score.setType("중간");
		score.setKor(90);
		score.setEng(90);
		score.setMat(90);
		scores.add(score);
		
		score = new Score();
		score.setNo(no);
		score.setType("기말");
		score.setKor(95);
		score.setEng(95);
		score.setMat(95);
		scores.add(score);
		
		while(run) {
			System.out.println("안녕하세요. 사용자 정보 관리 시스템입니다. 아래 항목 중 선택해주세요.");
			System.out.println("----------------------------------------");
			System.out.println("1.학생등록 | 2.학생검색 | 3.성적등록 | 0.종료");
			System.out.println("----------------------------------------");
			System.out.print("선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 :
				System.out.println("안녕하세요. 학생 등록 탭입니다.");
				
				no = no + 1;
				student = new Student();
				System.out.println("학번은 " + no + "번 입니다.");
				student.setNo(no);
				
				System.out.print("이름을 입력해주세요. : ");
				student.setName(sc.nextLine());
				
				System.out.print("학과를 입력해주세요. : ");
				student.setDept(sc.nextLine());
				students.add(student);
				
				System.out.println("등록이 완료되었습니다.");
				System.out.println("");
				
				System.out.println("등록된 학생의 수는 " + students.size() + "명 입니다.\n");
				break;
			case 2 : 
				System.out.println("안녕하세요. 학생 검색탭입니다.");
				System.out.print("학번을 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				find = false;
				for(Student stu:students) {
					if(sno == stu.getNo()) {
						find = true;
						System.out.printf("검색하신 %s(%s)님의 성적입니다.\n", stu.getName(), stu.getDept());
						System.out.println("구분\t국어\t영어\t수학\t총점\t평균");
						for(Score sco:scores) {
							if(sno == sco.getNo()) {
								System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\n", sco.getType(), sco.getKor(), sco.getEng(), sco.getMat(), sco.getTot(), sco.getAvg());	
							}
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 학생이 아닙니다.");
				}
				System.out.println("");
				break;
			case 3 :
				System.out.println("안녕하세요. 성적 등록 탭입니다.");
				score = new Score();
				
				System.out.print("학번을 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				score.setNo(sno);
				
				System.out.print("시험 유형을 입력해주세요 : ");
				type = sc.nextLine();
				score.setType(type);
				
				System.out.print("국어점수를 입력해주세요 : ");
				kor = sc.nextInt(); sc.nextLine();
				score.setKor(kor);
				
				System.out.print("영어점수를 입력해주세요 : ");
				eng = sc.nextInt(); sc.nextLine();
				score.setEng(eng);
				
				System.out.print("수학점수를 입력해주세요 : ");
				mat = sc.nextInt(); sc.nextLine();
				score.setMat(mat);
				
				System.out.println("");
				
				scores.add(score);
				System.out.println("성적등록이 완료되었습니다.\n");
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 입력해주세요.\n");
				break;
			}
		}
	}
}
