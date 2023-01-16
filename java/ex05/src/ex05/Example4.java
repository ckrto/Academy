package ex05;

import java.util.Scanner;

public class Example4 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		String[] name = new String[100];
		String[] address = new String[100];
		String[] tel = new String[100];
		String search = "";
		
		int sel;
		int count = 0;
		
		
		boolean searchCheck = false;
		boolean run = true;
		
		System.out.println("안녕하세요 주소록 정리 시스템 입니다.");
		while(run) {
			System.out.println("-----------------------------------------");
			System.out.println("1.주소등록 | 2.주소검색 | 3.주소목록 | 0. 종료");
			System.out.println("-----------------------------------------");
			System.out.print("실행할 작업을 선택해주세요 : ");
			sel = sc.nextInt();
			System.out.println("");
			
			switch(sel) {
			case 1 :
				System.out.println("주소 등록 탭입니다.");
				
				System.out.print("이름을 입력해주세요 : ");
				name[count] = sc.next(); sc.nextLine();
				
				System.out.print("주소를 입력해주세요 : ");
				address[count] = sc.nextLine();
				
				System.out.print("전화번호를 입력해주세요 : ");
				tel[count] = sc.next(); sc.nextLine();
				
				count++;
				
				System.out.println(count + "명 입력 완료했습니다.");
				break;
				
			case 2 :
				System.out.println("주소 검색 탭입니다.");
				System.out.print("이름을 입력해주세요 : ");
				search = sc.next();
				
				for(int i = 0; i < count; i++) {
					if(search.equals(name[i])) {
						searchCheck = true;
						System.out.println("이름 : " + name[i]);
						System.out.println("주소 : " + address[i]);
						System.out.println("전화번호 : " + tel[i]);
					}
				}
				if (searchCheck == false) {
					System.out.println("등록된 상품이 아닙니다.");
				}
				System.out.println("");
				
				break;
				
			case 3 :
				System.out.println("주소목록 탭입니다.");
				System.out.println("이름\t주소\t\t전화번호\t");
				
				for(int i = 0; i < count; i++) {
					System.out.println(name[i] + "\t" + address[i] + "\t" + tel[i]);
				}
				
				break;
				
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.");
				break;
				
			default :
				System.out.println("메뉴를 다시 선택해주세요.");
				break;
			}
		}
	}
}
