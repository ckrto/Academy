package ex14;

import java.util.ArrayList;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
	
		CampDAO cdao = new CampDAO();
		TypeDAO tdao = new TypeDAO();
		CampVO vo = null;
		String[] type = {"취소","오토캠장","카라반","글램핑","팬션"};
		
		int menu;
		int sel;
		
		boolean isFind = false;
		boolean run = true;
		
		while(run) {
			System.out.println("\n-----------------------------------");
			System.out.println("1.시설물등록 | 2.시설물삭제 | 0.전메뉴");
			System.out.println("-----------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				int code = Input.input("캠핑장 번호를 입력해주세요. : ");
				vo = cdao.read(code);
				if(vo.getName() == null) {
					System.out.println("해당캠핑장이 없습니다.\n");
					break;
				}
				System.out.println("캠핑장 이름 : " + vo.getName());
				System.out.println("-----------------------------------");
				
				ArrayList<String> tarray = tdao.list(code);
				ArrayList<String> check = new ArrayList<>();
				
				for(int i = 0; i < type.length; i++) {	
					isFind = false;
					
					for(String f : tarray) {
						if(type[i].equals(f)) {
							isFind = true;
						}	
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s(%s)\n", i, type[i], check.get(i));
				}
				System.out.println("---------------------------------------------");
				
				sel = Input.input("\n추가하실 시설물 선택해주세요. : ");
				System.out.println("");
				
				if(check.get(sel).equals("O")) {
					System.out.println("이미 등록된 시설물입니다.\n");
					break;
				}
				
				tdao.insert(code, type[sel]);
				
				System.out.println(type[sel] + "이(가) 등록되었습니다.");
				break;
			case 2 :
				code = Input.input("캠핑장 번호를 입력해주세요. : ");
				
				vo = cdao.read(code);
				
				if(vo.getName() == null) {
					System.out.println("해당캠핑장이 없습니다.\n");
					break;
				}
				System.out.println("캠핑장 이름 : " + vo.getName());
				System.out.println("-----------------------------------");
				
				tarray = tdao.list(code);
				check = new ArrayList<>();
				
				for(int i = 0; i < type.length; i++) {	
					isFind = false;
					
					for(String t : tarray) {
						if(type[i].equals(t)) {
							isFind = true;
						}						
					}
					if(isFind == true) {
						check.add("O");
					} else {
						check.add("X");
					}
					System.out.printf("[%d] %s(%s)\n", i, type[i], check.get(i));
					
				}
				System.out.println("---------------------------------------------");

				sel = Input.input("\n삭제하실 시설물 선택해주세요. : ");
				if(check.get(sel).equals("X")) {
					System.out.println("등록된 시설물이 아닙니다.");
					break;
				}
				tdao.delect(code, type[sel]);
				
				System.out.println(type[sel] + "이(가) 삭제되었습니다.");
				
				System.out.println("");
				break;
			case 0 :
				run = false;
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
		}
	}
}
