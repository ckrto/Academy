package ex17;

import java.util.ArrayList;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		TypeDAO tdao = new TypeDAO();
		TypeVO tvo = null;
		CampDAO cdao = new CampDAO();
		CampVO cvo = null;
		
		int menu;
		int qnt;
		
		String code;
		String sel;
		String newCode;
		String tname;
		String tcode;
		
		boolean find = false;
		boolean success = false;
		boolean run = true;
		
		while(run) {
			System.out.println("************************* 시설물 관리 *************************");
			System.out.println("-----------------------------------------------------------");
			System.out.println("1.등록 | 2.목록 | 3.삭제 | 4.등록(캠핑장) | 5.삭제(캠핑장) | 0.종료");
			System.out.println("-----------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				newCode = tdao.getMaxCode();
				
				System.out.println("등록하실 시설물의 코드는 " + newCode + " 입니다.\n");
				System.out.print("시설물의 이름을 입력해주세요. : ");
				tname = sc.nextLine();
				System.out.println("");
				
				if(tname.equals("")) {
					System.out.println("등록을 취소합니다.\n");
					break;
				}
				tdao.insert(newCode, tname);
				System.out.println("새로운 시설물이 등록되었습니다.\n");
				break;
			case 2 :
				for(TypeVO t : tdao.list()) {
					System.out.printf("%s\t%s\n",t.getTcode(), t.getTname());
				}
				System.out.println("");
				break;
			case 3 :
				System.out.print("삭제할 시설물의 코드를 입력해주세요. : ");
				tcode = sc.nextLine();
				System.out.println("");
				
				if(tcode.equals("")) {
					System.out.println("삭제를 취소했습니다.\n");
				}
				
				TypeVO vo = tdao.read(tcode);
				if(vo.getTname() == null) {
					System.out.println("삭제할 코드가 없습니다.\n");
					break;
				}
				
				System.out.println("유형 이름은 " + vo.getTname() + " 입니다.\n");
				System.out.println("");
				
				System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					success = tdao.delete(tcode);
					if(success == true) {
						System.out.println("삭제가 완료되었습니다.\n");
					} else {
						System.out.println("이 유형을 사용중인 캠핑장이 있습니다.\n");
					}
				} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("삭제가 취소되었습니다.\n");
				} else {
					System.out.println("삭제가 취소되었습니다.\n");
	
				}
				System.out.println("");
				break;
			case 4 :
				System.out.print("등록을 진행할 캠핑장의 코드를 입력해주세요. : ");
				code = sc.nextLine();
				System.out.println("");
				if(code.equals("")) {
					System.out.println("유형등록을 취소합니다.\n");
					break;
				}
				cvo = cdao.read(code);
				if(cvo.getCname() == null) {
					System.out.println("캠핑장이 존재하지 않습니다.");
					break;
				}
				System.out.printf("%s(%s) \n", cvo.getCname(), cvo.getAdress());
				System.out.println("");
				
				ArrayList<TypeVO> tarray = tdao.list(code);
				System.out.print("등록된 유형 현황 : ");
				for(TypeVO t : tarray) {
					System.out.printf("%s(%s) | ", t.getTcode(), t.getTname());
				}
				System.out.println("\n------------------------------------------------");
				
				System.out.print("등록되지 않은 유형 현황 : ");
				ArrayList<TypeVO> aarray = tdao.list();
				for(TypeVO a : aarray) {
					TypeVO v = tdao.read(code, a.getTcode());
					if(v.getTname() == null) {
						System.out.printf("%s(%s) | ", a.getTcode(), a.getTname());
					}
				}
				System.out.println("\n------------------------------------------------");
				
				do {
					System.out.print("등록하실 유형코드를 입력해주세요. : ");
					tcode = sc.nextLine();
					if(tcode.equals("")) {
						break;
					}
					tvo = tdao.read(tcode);
					if(tvo.getTname() == null) {
						System.out.println("없는 유형코드입니다.\n");
					} else {
						tvo = tdao.read(code, tcode);
						if(tvo.getTname() == null) {
							
							break;
						} else {
							System.out.println("이미 등록된 유형입니다.");
						}
					}
				} while(true);
				
				System.out.print("수량을 입력해주세요. : ");
				qnt = sc.nextInt(); sc.nextLine();
				tvo = new TypeVO();
				tvo.setCode(code);
				tvo.setTcode(tcode);
				tvo.setQnt(qnt);
				tdao.insert(tvo);
				System.out.println("새로운 유형이 등록되었습니다.\n");
				
				System.out.println("");
				break;
			case 5 :
				
				System.out.print("등록을 진행할 캠핑장의 코드를 입력해주세요. : ");
				code = sc.nextLine();
				System.out.println("");
				if(code.equals("")) {
					System.out.println("유형등록을 취소합니다.\n");
					break;
				}
				cvo = cdao.read(code);
				if(cvo.getCname() == null) {
					System.out.println("캠핑장이 존재하지 않습니다.");
					break;
				}
				System.out.printf("%s(%s) \n", cvo.getCname(), cvo.getAdress());
				System.out.println("");
				
				tarray = tdao.list(code);
				System.out.print("등록된 유형 현황 : ");
				for(TypeVO t : tarray) {
					System.out.printf("%s(%s) | ", t.getTcode(), t.getTname());
				}
				System.out.println("\n------------------------------------------------");
				
				do {
					System.out.print("삭제하실 유형코드를 입력해주세요. : ");
					tcode = sc.nextLine();
					System.out.println("");
					
					if(tcode.equals("")) {
						break;
					}
					tvo = tdao.read(code, tcode);
					if(tvo.getTname() == null) {
						System.out.println("삭제할 유형이 없습니다.\n");
						break;
					} else {
						tdao.delete(code, tcode);
						System.out.println("삭제되었습니다.\n");
					}
				} while(true);
				
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
