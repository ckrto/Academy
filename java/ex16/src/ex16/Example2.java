package ex16;

import java.util.ArrayList;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		TypeDAO dao = new TypeDAO();
		TypeVO vo = null;
		
		int menu;
		int code;
		
		String sel;
		String newCode;
		String maxCode;
		String tcode;
		String tname;
		String tdate;
		
		boolean success;
		boolean run = true;
		
		System.out.println("*************************** 타 입 코 드 관 리 ***************************");
		while(run) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.타입등록 | 2.타입목록 | 3.타입수정 | 4.타입삭제 | 5.타입조회 | 0.종료");
			System.out.println("--------------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				maxCode = dao.getMaxCode();
				code = Integer.parseInt(maxCode.substring(1)) + 10;
				newCode = "T" + code;
				System.out.println("유형코드는 " + newCode);
				
				System.out.print("등록하실 유형의 이름을 입력해주세요. : ");
				tname = sc.nextLine();
				System.out.println("");
				
				System.out.print("등록하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					vo = new TypeVO();
					vo.setTcode(newCode);
					vo.setTname(tname);
					dao.insert(vo);
					System.out.println("새로운 유형이 등록되었습니다.\n");
				} 
				else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("등록이 취소되었습니다.\n");
				} 
				else {
					System.out.println("다시 선택해주세요.\n");
				}
				
				break;
			case 2 :
				ArrayList<TypeVO> array = dao.list();
				System.out.println("유형코드\t시험유형\t시험날짜");
				for(TypeVO v : array) {
					System.out.printf("%s\t%s\t%s\n", v.getTcode(), v.getTname(), v.getTdate());
				}
				System.out.println("");
				break;
			case 3 :
				System.out.print("수정하실 유형코드를 입력해주세요. : ");
				tcode = sc.nextLine();
				System.out.println("");
				
				vo = dao.read(tcode);
				
				if(vo.getTname() == null) {
					System.out.println("검색하신 시험 유형이 존재하지 않습니다.\n");
				} else {
					System.out.println("검색하신 " + tcode + "의 시험 유형은 " + vo.getTname() + " 입니다.");
					System.out.print("수정하실 시험명을 입력해주세요. : ");
					tname = sc.nextLine();
					
					if(!tname.equals("")) {
						vo.setTname(tname);
					}
					System.out.println("");
					
					System.out.println(vo.getTname() + "의 시험일은 " + vo.getTdate() + " 입니다.");
					System.out.print("수정하실 시험 날짜를 입력해주세요. : ");
					tdate = sc.nextLine();
					
					if(!tdate.equals("")) {
						vo.setTdate(tdate);
					}
					System.out.println("");
					
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
				}
				
				break;
			case 4 :
				System.out.print("삭제하실 유형의 코드를 입력해주세요. : ");
				tcode = sc.nextLine();
				System.out.println("");
				
				vo = dao.read(tcode);
				
				if(vo.getTname() == null) {
					System.out.println("검색하신 학과가 존재하지 않습니다.\n");
				} else {
					System.out.println("검색하신 " + tcode + "의 학과 이름은 " + vo.getTname() + " 입니다.\n");
					System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
					sel = sc.nextLine();
					System.out.println("");
					
					if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
						success = dao.delete(tcode);
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
				}
				break;
			case 5 :
				System.out.print("조회하실 유형의 코드를 입력해주세요. : ");
				tcode = sc.nextLine();
				vo = dao.read(tcode);
				System.out.println();
				
				if(vo.getTname() == null) {
					System.out.println("검색하신 학과가 존재하지 않습니다.\n");
				} else {
					System.out.println("검색하신 " + tcode + "의 시험 유형은 " + vo.getTname());
					System.out.println("시험일은 " + vo.getTdate() + " 입니다.\n");
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
