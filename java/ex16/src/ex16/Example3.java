package ex16;

import java.util.ArrayList;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();
		StudentVO vo = null;
		DeptDAO ddao = new DeptDAO();
		
		int menu;
		int newNo;
		
		String sel;
		String maxNo;
		String sno;
		String sname;
		String dcode;
		
		boolean success;
		boolean isFind = false;
		boolean run = true;
		
		System.out.println("*************************** 학 생  관 리 ***************************");
		
		while(run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.학생등록 | 2.학생목록 | 3.학생수정 | 4.학생삭제 | 5.학생조회 | 0.종료");
			System.out.println("--------------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				maxNo = dao.maxNo();
				newNo = Integer.parseInt(maxNo) + 100;
				System.out.println("귀하의 학번은 " + newNo + "번 입니다.\n");
				
				do {
					System.out.print("이름을 입력해주세요. : ");
					sname = sc.nextLine();
					System.out.println("");
					
					if(sname.equals("")) {
						System.out.println("이름을 입력하세요!");
					}
				} while(sname.equals(""));
				
				ArrayList<DeptVO> darray = ddao.list();
				for(DeptVO v : darray) {
					System.out.printf("%s : %s | ", v.getDcode(), v.getDname());
				}
				System.out.println("");
				
				do {
					System.out.print("학과 코드를 선택해주세요. : ");
					dcode = sc.nextLine();
					System.out.println("");
					
					DeptVO v = ddao.read(dcode);
					if(v.getDname() != null) {
						isFind = true;
					}
					if(isFind == false) {
						System.out.println("해당 학과코드를 가진 학과가 존재하지 않습니다.\n");
					}
				} while(isFind == false);
				
				vo = new StudentVO();
				vo.setSno(String.valueOf(newNo));
				vo.setSname(sname);
				vo.setDcode(dcode);
				dao.insert(vo);
				
				System.out.println("새로운 학생이 등록되었습니다.\n");
				
				break;
			case 2 :
				ArrayList<StudentVO> array = dao.list();
				System.out.println("학번\t이름\t학과코드(학과)");
				for(StudentVO v : array) {
					System.out.printf("%s\t%s\t%s(%s)\n", v.getSno(), v.getSname(), v.getDcode(), v.getDname());
				}
				System.out.println("");
				break;
			case 3 :
				System.out.print("수정하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				if(sno.equals("")) {
					System.out.println("수정을 취소합니다.\n");
					break;
				}
				System.out.println("");
				
				vo = dao.read(sno);
				
				if(vo.getSname() == null) {
					System.out.println("검색하신 학번을 가진 학생이 존재하지않습니다.\n");
					break;
				}
				
				System.out.println("검색하신 학생의 이름은 " + vo.getSname() + "입니다.");
				System.out.print("변경하실 이름을 입력해주세요. : ");
				sname = sc.nextLine();
				
				if(!sname.equals("")) {
					vo.setSname(sname);
				}
				System.out.println("");
				
				darray = ddao.list();
				for(DeptVO v : darray) {
					System.out.printf("%s : %s | ", v.getDcode(), v.getDname());
				}
				System.out.println("");
				
				isFind = false;
				do {
					System.out.printf("현재 학과코드와 학과는 %s(%s) 입니다. 변경할 학과코드를 입력해주세요. : ",vo.getDcode(), vo.getDname());
					dcode = sc.nextLine();
					if(dcode.equals("")) {
						break;
					}
					System.out.println("");
					DeptVO dvo = ddao.read(dcode);
					if(dvo.getDname() != null) {
						isFind = true;
					}
					if(isFind == false) {
						System.out.println("해당학과가 없습니다.\n");
					}
				} while(isFind == false);
				
				if(!dcode.equals("")) {
					vo.setDcode(dcode);
				}
				
				System.out.print("정말로 수정하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					dao.update(vo);
					System.out.println("수정이 완료되었습니다.\n");
					
				} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("수정이 취소되었습니다.\n");
				} else {
					System.out.println("수정이 취소되었습니다.\n");
				}
				
				System.out.println("");
				break;
			case 4 :
				System.out.print("삭제하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				if(sno.equals("")) {
					System.out.println("삭제를 취소합니다.\n");
					break;
				}
				System.out.println("");
				
				vo = dao.read(sno);
				if(vo.getSname() == null) {
					System.out.println("해당 학생이 존재하지 않습니다.\n");
					break;
				}
				System.out.printf("%s(%s)\n", vo.getSname(), vo.getDname());
				
				System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					success = dao.delete(sno);
					if(success == true) {
						System.out.println("삭제가 완료되었습니다.\n");
					} else {
						System.out.println("성적테이블에서 사용중이여서 삭제할 수 없습니다.\n");
					}
				} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("삭제가 취소되었습니다.\n");
				} else {
					System.out.println("삭제가 취소되었습니다.\n");
				}
				break;
			case 5:
				System.out.print("조회하실 학생의 학번을 입력해주세요. : ");
				sno = sc.nextLine();
				vo = dao.read(sno);
				System.out.println("");
				
				if(vo.getSname() == null) {
					System.out.println("검색하신 학번을 가진 학생이 존재하지않습니다.\n");
					break;
				}
				System.out.println("검색하신 학생의 이름은 " + vo.getSname());
				System.out.printf("학과코드와 학과는 %s(%s) 입니다.\n", vo.getDcode(), vo.getDname());
				System.out.println("");
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
