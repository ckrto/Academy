package ex16;

import java.util.ArrayList;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		DeptDAO dao = new DeptDAO();
		DeptVO vo = null;
		
		int menu;
		int code;
		
		String sel;
		String dcode;
		String dname;
		String uname;
		String maxCode;
		String newCode;
		
		boolean success;
		boolean run = true;
		
		System.out.println("*************************** 학 과 코 드 관 리 ***************************");
		while(run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.코드등록 | 2.코드목록 | 3.코드수정 | 4.코드삭제 | 5.코드조회 | 0.종료");
			System.out.println("--------------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			switch(menu) {
			case 1 :
				maxCode = dao.getMaxCode();
				code = Integer.parseInt(maxCode.substring(1)) + 10;
				
				newCode = "D" + code;
				System.out.println("학과코드는 " + newCode);
				
				System.out.print("학과 이름을 입력해주세요. : ");
				dname = sc.nextLine();
				System.out.println("");
				
				System.out.print("등록하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					vo = new DeptVO();
					vo.setDcode(newCode);
					vo.setDname(dname);
					dao.insert(vo);
					System.out.println("새로운 학과가 등록되었습니다.\n");
				} 
				else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("등록이 취소되었습니다.\n");
				} 
				else {
					System.out.println("다시 선택해주세요.\n");
				}
				
				break;
			case 2 :
				ArrayList<DeptVO> array=dao.list();
				System.out.println("학과코드\t학과명");
				for(DeptVO v:array) {
					System.out.printf("%s\t%s\n",v.getDcode(),
										v.getDname());
				}
				
				System.out.println("");
				break;
			case 3 :
				System.out.print("수정하실 학과코드를 입력해주세요. : ");
				dcode = sc.nextLine();
				System.out.println("");
				
				vo = dao.read(dcode);
				
				if(vo.getDname() == null) {
					System.out.println("검색하신 학과가 존재하지 않습니다.\n");
				} else {
					System.out.println("검색하신 " + dcode + "의 학과 이름은 " + vo.getDname() + " 입니다.\n");
					System.out.print("수정하실 학과명을 입력해주세요. : ");
					uname = sc.nextLine();
					System.out.println("");
					
					System.out.print("정말로 수정하시겠습니까(예 : Y | 아니요 : N)? ");
					sel = sc.nextLine();
					System.out.println("");
					
					if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
						vo = new DeptVO();
						vo.setDcode(dcode);
						vo.setDname(uname);
						dao.update(vo);
						System.out.println("수정이 완료되었습니다.\n");
						
					} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
						System.out.println("수정이 취소되었습니다.\n");
					} else {
						System.out.println("다시 선택해주세요.\n");
					}
				}
				
				break;
			case 4 :
				System.out.print("삭제하실 학과코드를 입력해주세요. : ");
				dcode = sc.nextLine();
				System.out.println("");
				
				vo = dao.read(dcode);
				
				if(vo.getDname() == null) {
					System.out.println("검색하신 학과가 존재하지 않습니다.\n");
				} else {
					System.out.println("검색하신 " + dcode + "의 학과 이름은 " + vo.getDname() + " 입니다.\n");
					System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
					sel = sc.nextLine();
					System.out.println("");
					
					if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
						success = dao.delete(dcode);
						if(success == true) {
							System.out.println("삭제가 완료되었습니다.\n");
						} else {
							System.out.println("이 학과에 학생이 있습니다.\n");
						}
					} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
						System.out.println("삭제가 취소되었습니다.\n");
					} else {
						System.out.println("다시 선택해주세요.\n");
					}
				}
				break;
			case 5 :
				System.out.print("조회하실 학과코드를 입력해주세요. : ");
				dcode = sc.nextLine();
				vo = dao.read(dcode);
				System.out.println();
				
				if(vo.getDname() == null) {
					System.out.println("검색하신 학과가 존재하지 않습니다.\n");
					break;
				} else {
					System.out.println("검색하신 " + dcode + "의 학과 이름은 " + vo.getDname() + " 입니다.\n");
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
