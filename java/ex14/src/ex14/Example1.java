package ex14;

import java.util.ArrayList;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		CampDAO cdao = new CampDAO();
		TypeDAO tdao = new TypeDAO();
		FacilDAO fdao = new FacilDAO();
		CampVO vo = null;
		
		int menu;
		int code;
		int newCode;
		
		boolean run = true;
		
		while(run) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("1.캠핑장등록 | 2.캠핑장조회 | 3.캠핑장목록 | 4.시설물관리 | 5.종류관리 | 0.종료");
			System.out.println("-------------------------------------------------------------------");
			menu = Input.input("실행할 작업을 선택해주세요 : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				newCode = cdao.getLastCode() + 100;
				vo = new CampVO();
				
				System.out.println("캠핑장 코드는 " +  newCode + " 입니다.");
				vo.setCode(newCode);
				
				System.out.print("캠핑장 이름을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				
				System.out.print("캠핑장의 주소를 입력해주세요. : ");
				vo.setAddress(sc.nextLine());
				
				System.out.println("");
				
				cdao.insert(vo);
				
				System.out.println("새로운 캠핑장이 등록되었습니다.\n");
				break;
			case 2 :
				code = Input.input("조회할 코드를 입력해주세요. : ");
				vo = cdao.read(code);
				if(vo.getName() == null) {
					System.out.println("검색한 코드의 캠핑장은 존재하지 않습니다.");
				} else {
					System.out.println("캠핑장이름 : " + vo.getName());
					System.out.println("캠핑장주소 : " + vo.getAddress());
					ArrayList<String> tarray = tdao.list(code);
					System.out.print("캠핑장 Type :");
					if(tarray.size() == 0) {
						System.out.print(" 없음");
					}
					for(String t : tarray) {
						System.out.printf(" %s |" ,t);
					}
					System.out.println("");
					ArrayList<String> farray = fdao.list(code);
					System.out.print("캠핑장 시설물 :");
					if(farray.size() == 0) {
						System.out.print(" 없음");
					}
					for(String f : farray) {
						System.out.printf(" %s |", f);
					}
				}
				System.out.println("\n");
				break;
			case 3 : 
				System.out.println("------------------------");
				System.out.println("코드\t캠핑장이름\t캠핑장주소");
				System.out.println("------------------------");
				ArrayList<CampVO> carray = cdao.list();
				for(CampVO c : carray) {
					System.out.printf("%d\t%s\t%s\n", c.getCode(), c.getName(), c.getAddress());
					ArrayList<String> tarray = tdao.list(c.getCode());
					System.out.print("캠핑장 Type :");
					if(tarray.size() == 0) {
						System.out.print(" 없음");
					}
					for(String t : tarray) {
						System.out.printf(" %s |" ,t);
					}
					System.out.println("");
					ArrayList<String> farray = fdao.list(c.getCode());
					System.out.print("캠핑장 시설물 :");
					if(farray.size() == 0) {
						System.out.print(" 없음");
					}
					for(String f : farray) {
						System.out.printf(" %s |", f);
					}
					System.out.println("\n--------------------------");
				}
				System.out.println("");
				
				System.out.println("등록된 캠핑장 수는 " + carray.size() + "개 입니다.\n");
				break;
			case 4 :
				Example2.execute();
				break;
			case 5 :
				Example3.execute();
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요\n");
				break;
			}
		}
	}
}
