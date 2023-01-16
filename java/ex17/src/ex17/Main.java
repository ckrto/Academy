package ex17;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CampDAO dao = new CampDAO();
		CampVO vo = null;
		FacilityDAO fdao = new FacilityDAO();
		FacilityVO fvo = null;
		TypeDAO tdao = new TypeDAO();
		TypeVO tvo = null;
		
		int menu;
		
		String newCode;
		String cname;
		String address;
		String code;
		
		boolean run = true;
		
		while(run) {
			System.out.println("-------------------------");
			System.out.println("1.캠핑장등록");
			System.out.println("2.캠핑장목록");
			System.out.println("3.캠핑장조회");
			System.out.println("4.시설물등록");
			System.out.println("5.유형등록");
			System.out.println("0.프로그램 종료");
			System.out.println("-------------------------");
			menu = Input.input("실행할 작업을 선택해주세요. : ");
			System.out.println("");
			
			switch(menu) {
			case 1 :
				newCode = dao.getNewCode();
				System.out.println("등록하실 캠핑장의 코드는 " + newCode + " 입니다.\n");
				
				System.out.print("캠핑장의 이름을 입력해주세요. : ");
				cname = sc.nextLine();
				System.out.println("");
				
				if(cname.equals("")) {
					System.out.println("등록을 취소합니다.\n");
					break;
				}
				
				System.out.print("캠핑장의 주소를 입력해주세요. : ");
				address = sc.nextLine();
				System.out.println("");
				
				if(address.equals("")) {
					System.out.println("등록을 취소합니다.\n");
					break;
				}
				vo = new CampVO();
				vo.setCode(newCode);
				vo.setCname(cname);
				vo.setAdress(address);
				dao.insert(vo);
				System.out.println("새로운 캠핑장이 등록되었습니다.\n");
				break;
			case 2 :
				ArrayList<CampVO> array = dao.list();
				System.out.println("------------------------------------------------");
				for(CampVO v : array) {
					System.out.printf("%s\t%s\t%s\n", v.getCode(), v.getCname(), v.getAdress());
					ArrayList<FacilityVO> farray = fdao.list(v.getCode());
					System.out.print("시설물 현황 : ");
					for(FacilityVO f : farray) {
						System.out.printf("%s(%s) | ", f.getFname(), f.getFcode());
					}
					System.out.println("");
					ArrayList<TypeVO> tarray = tdao.list(v.getCode());
					System.out.println("캠핑장유형 현황 : ");
					for(TypeVO t : tarray) {
						System.out.printf("%s(%d개) | ", t.getTname(), t.getQnt());
					}
					System.out.println("");
					System.out.println("------------------------------------------------");
				}		
				System.out.println("");
				break;
			case 3 :
				System.out.print("조회할 캠핑장의 코드를 입력해주세요. : ");
				code = sc.nextLine();
				System.out.println("");
				if(code.equals("")) {
					System.out.println("조회를 취소합니다.\n");
					break;
				}
				vo = dao.read(code);
				if(vo.getCname() == null) {
					System.out.println("해당 캠핑장이 없습니다.");
					break;
				}
				
				System.out.printf("%s(%s)\n", vo.getCname(), vo.getAdress());
				ArrayList<FacilityVO> farray = fdao.list(code);
				System.out.println("시설물 : ");
				for(FacilityVO f : farray) {
					System.out.printf("%s(%s) | ", f.getFname(), f.getFcode());
				}
				System.out.println("\n------------------------------------------------");
				
				ArrayList<TypeVO> tarray = tdao.list(code);
				System.out.println("유형 : ");
				for(TypeVO t : tarray) {
					System.out.printf("%s(%s) | ", t.getTname(), t.getTcode());
				}
				System.out.println("\n------------------------------------------------");
				
				
				System.out.println("");
				break;
			case 4 :
				Example1.execute();
				break;
			case 5 :
				Example2.execute();
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
