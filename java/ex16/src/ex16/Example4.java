package ex16;

import java.util.ArrayList;
import java.util.Scanner;

public class Example4 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		TypeDAO tdao = new TypeDAO();
		ScoreDAO dao = new ScoreDAO();
		StudentVO svo = new StudentVO();
		StudentDAO sdao = new StudentDAO();
		
		int grade;
		int menu;
		int count = 0;
		
		String sno;
		String sel;
		String tcode;
		
		boolean isFind = false;
		boolean run = true;
		
		System.out.println("*************************** 성 적  관 리 ***************************");
		
		while(run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.성적조회 | 2.성적입력 | 3.성적삭제 | 0.종료");
			System.out.println("--------------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				System.out.print("조회하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				System.out.println("");
				
				if(sno.equals("")) {
					System.out.println("조회를 취소합니다.\n");
					break;
				}
				svo = sdao.read(sno);
				if(svo.getSname() == null) {
					System.out.println("해당학생이 존재하지 않습니다.\n");
					break;
				}
				
				System.out.printf("%s(%s)님의 성적입니다.\n", svo.getSname(), svo.getDname());
				System.out.println("----------------------------------------------------------");
				ArrayList<ScoreVO> array = dao.list(sno);
				System.out.println("시험유형(시험코드)\t시험일\t\t시험점수");
				for(ScoreVO v : array) {
					System.out.printf("%s(%s)\t%s\t%s점\n", v.getTname(), v.getTcode(), v.getTdate(), v.getGrade());
				}
				System.out.println("");
				break;
			case 2 :
				System.out.print("조회하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				System.out.println("");
				
				if(sno.equals("")) {
					System.out.println("등록을 취소합니다.\n");
					break;
				}
				
				svo = sdao.read(sno);
				if(svo.getSname() == null) {
					System.out.println("해당학생이 존재하지 않습니다.\n");
					break;
				}
				
				System.out.printf("%s(%s)님의 성적입니다.\n", svo.getSname(), svo.getDname());
				System.out.println("----------------------------------------------------------");
				array = dao.list(sno);
				System.out.println("시험유형(시험코드)\t시험일\t\t시험점수");
				
				for(ScoreVO v : array) {
					System.out.printf("%s(%s)\t%s\t%s점\n", v.getTname(), v.getTcode(), v.getTdate(), v.getGrade());
				}
				System.out.println("");
				
				ArrayList<TypeVO> tarray = tdao.list();
				for(TypeVO v : tarray) {
					System.out.printf("%s : %s | ", v.getTcode(), v.getTname());
				}
				System.out.println("");
				
				isFind = false;
				do {
					System.out.print("시험유형을 입력해주세요. : ");
					tcode = sc.nextLine();
					System.out.println("");
					
					if(tcode.equals("")) {
						break;
					}
					
					count = dao.read(sno, tcode);
					if(count == 1) {
						break;
					}
					
					TypeVO tvo = tdao.read(tcode);
					if(tvo.getTname() != null) {
						isFind = true;
					}
					
				} while(isFind == false);
				
				if(tcode.equals("")) {
					System.out.println("성적등록을 취소합니다.\n");
					break;
				}
				
				if(count == 1) {
					System.out.println("이미 등록된 성적입니다.\n");
					break;
				}
				
				grade = Input.input("성적을 입력해주세요. : ");
				System.out.println("");
				
				ScoreVO vo = new ScoreVO();
				vo.setSno(sno);
				vo.setTcode(tcode);
				vo.setGrade(grade);
				
				dao.insert(vo);
				System.out.println("성적이 등록되었습니다.");
				
				System.out.println("");
				break;
			case 3 :
				System.out.print("삭제하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				System.out.println("");
				svo = sdao.read(sno);
				if(svo.getSname() == null) { 
					System.out.println("해당학생이 존재하지않습니다.");
					break;
				}
				System.out.printf("%s(%s)님의 성적입니다.\n", svo.getSname(), svo.getDname());
				array = dao.list(sno);
				for(ScoreVO v : array) {
					System.out.printf("%s(%s)\t%s\t%s점\n", v.getTname(), v.getTcode(), v.getTdate(), v.getGrade());
				}
				System.out.println("");
				do {
					System.out.print("시험유형을 입력해주세요. : ");
					tcode = sc.nextLine();
					System.out.println("");
					
					if(tcode.equals("")) {
						break;
					}
					
					count = dao.read(sno, tcode);
					if(count == 0) {
						System.out.println("삭제할데이터가 없습니다.\n");
					}
					else {
						break;
					}
				} while(true);
				
				if(tcode.equals("")) {
					System.out.println("삭제를 취소합니다.\n");
					break;
				}
				
				System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					dao.delete(sno, tcode);
					System.out.println("삭제가 완료되었습니다.\n");
				} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("삭제가 취소되었습니다.\n");
				} else {
					System.out.println("삭제가 취소되었습니다.\n");
				}
				break;
			case 0 :
				run = false;
				System.out.println("메인메뉴로 돌아갑니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
		}
	}
}
