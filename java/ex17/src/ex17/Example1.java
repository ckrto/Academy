package ex17;

import java.util.ArrayList;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		FacilityDAO fdao = new FacilityDAO();
		CampDAO cdao = new CampDAO();
		
		int menu;
		
		String code;
		String sel;
		String fcode;
		String fname;
		String newCode;
		
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
				newCode = fdao.getMaxCode();
				
				System.out.println("등록하실 시설물의 코드는 " + newCode + " 입니다.\n");
				System.out.print("시설물의 이름을 입력해주세요. : ");
				fname = sc.nextLine();
				System.out.println("");
				
				if(fname.equals("")) {
					System.out.println("등록을 취소합니다.\n");
					break;
				}
				fdao.insert(newCode, fname);
				System.out.println("새로운 시설물이 등록되었습니다.\n");
				break;
			case 2 : 
				ArrayList<FacilityVO> farray = fdao.list();
				System.out.println("시설물코드\t시설물이름");
				for(FacilityVO f : farray) {
					System.out.printf("%s\t%s\n", f.getFcode(), f.getFname());
				}
				System.out.println("");
				break;
			case 3 :
				System.out.print("삭제할 시설물의 코드를 입력해주세요. : ");
				fcode = sc.nextLine();
				System.out.println("");
				
				if(fcode.equals("")) {
					System.out.println("삭제를 취소했습니다.\n");
				}
				
				FacilityVO vo = fdao.read(fcode);
				if(vo.getFname() == null) {
					System.out.println("해당 시설물이 존재하지 않습니다.");
					break;
				}
				
				System.out.println("시설물의 이름은 " + vo.getFname() + " 입니다.\n");
				System.out.println("");
				
				System.out.print("정말로 삭제하시겠습니까(예 : Y | 아니요 : N)? ");
				sel = sc.nextLine();
				System.out.println("");
				
				if(sel.equals("ㅛ") || sel.equals("Y") || sel.equals("y")) {
					success = fdao.delete(fcode);
					if(success == true) {
						System.out.println("삭제가 완료되었습니다.\n");
					} else {
						System.out.println("이 시설물을 사용중인 캠핑장이 있습니다.\n");
					}
				} else if(sel.equals("n") || sel.equals("N") || sel.equals("ㅜ")) {
					System.out.println("삭제가 취소되었습니다.\n");
				} else {
					System.out.println("삭제가 취소되었습니다.\n");
	
				}
				System.out.println("");
				break;
			case 4 :
				System.out.print("캠핑장 코드를 입력해주세요. : ");
				code = sc.nextLine();
				System.out.println("");
				
				if(code.equals("")) {
					System.out.println("시설물 등록을 취소합니다.\n");
					break;
				}
				
				CampVO cvo = cdao.read(code);
				if(cvo.getCname() == null) {
					System.out.println("해당되는 캠핑장이 존재하지 않습니다.\n");
					break;
				}
				
				System.out.printf("%s(%s)\n", cvo.getCname(), cvo.getAdress());
				System.out.println("");
				
				System.out.println("등록된 시설물 목록입니다.");
				System.out.println("----------------------------------------------");
				farray = fdao.list(code);
				for(FacilityVO f : farray) {
					System.out.printf("%s:%s | ", f.getFcode(), f.getFname());
				}
				System.out.println("\n");
				System.out.println("등록이 안된 시설물 목록입니다.");
				System.out.println("----------------------------------------------");
				ArrayList<FacilityVO> allArray = fdao.list();
				for(FacilityVO a : allArray) {
					boolean find = false;
					for(FacilityVO f : farray) {
						if(a.getFcode().equals(f.getFcode()))
						{
							find = true;
						}
					}
					
					if(find == false) {
						System.out.printf("%s:%s | ", a.getFcode(), a.getFname());
					}
				}
				System.out.println("\n----------------------------------------------\n");
				
				System.out.print("등록하실 시설물을 선택해주세요. : ");
				fcode = sc.nextLine();
				System.out.println("");
				
				if(fcode.equals("")) {
					System.out.println("시설물 등록을 취소합니다.\n");
					break;
				}
				
				FacilityVO fvo = fdao.read(fcode);
				if(fvo.getFname() == null) {
					System.out.println("시설물을 찾을 수 없습니다.\n");
					break;
				}
				
				fvo = fdao.read(code, fcode);
				if(fvo.getFname() == null) {
					fvo = new FacilityVO();
					fvo.setCode(code);
					fvo.setFcode(fcode);
					fdao.insert(fvo);
					System.out.println("새로운 시설물이 등록되었습니다.\n");
				} else {
					System.out.println(fvo.getFname() + " 시설물이 이미 등록되어있습니다.\n");
				}
				
				break;
			case 5 :
				System.out.print("캠핑장 코드를 입력해주세요. : ");
				code = sc.nextLine();
				System.out.println("");
				if(code.equals("")) {
					System.out.println("시설물 삭제를 취소합니다.\n");
					break;
				}
				cvo = cdao.read(code);
				if(cvo.getCname() == null) {
					System.out.println("해당되는 캠핑장이 존재하지 않습니다.\n");
					break;
				}
				System.out.printf("%s(%s)\n", cvo.getCname(), cvo.getAdress());
				System.out.println("");
				
				farray = fdao.list(code);
				System.out.println("등록된 시설물 목록입니다.");
				System.out.println("----------------------------------------------");
				for(FacilityVO f : farray) {
					System.out.printf("%s:%s | ", f.getFcode(), f.getFname());
				}
				
				System.out.println("\n----------------------------------------------");
				do {
					System.out.print("\n삭제하실 시설물 코드를 입력해주세요. : ");
					fcode = sc.nextLine();
					System.out.println("");
					
					if(fcode.equals("")) {
						System.out.println("시설물 삭제을 취소합니다.\n");
						break;
					}
					fvo = fdao.read(fcode);
					if(fvo.getFname() == null) {
						System.out.println("해당 시설물이 없습니다.\n");
					} else {
						fdao.delete(code, fcode);
						System.out.println(fvo.getFname() + " 시설물이 삭제되었습니다.");
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
