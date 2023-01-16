package ex05;

import java.util.ArrayList;
import java.util.Scanner;

public class Example7 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Address> array = new ArrayList<>();
		String sname;
		int sel;
		
		boolean find = false;
		boolean run = true;
		
		while(run) {
			System.out.println("-----------------------------------------");
			System.out.println("1.주소등록 | 2.주소검색 | 3.주소목록 | 0. 종료");
			System.out.println("-----------------------------------------");
			System.out.print("실행할 작업을 선택해주세요 : ");
			sel = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(sel)	{
			case 1 :
				Address juso = new Address();
				
				System.out.print("이름을 입력해주세요 : ");
				juso.name = sc.next(); sc.nextLine();
				
				System.out.print("주소를 입력해주세요 : ");
				juso.address = sc.nextLine();
				
				System.out.print("전화번호를 입력해주세요 : ");
				juso.tel = sc.next(); sc.nextLine();
				array.add(juso);
				
				System.out.println("");
				
				break;
				
			case 2 : 
				System.out.print("검색하실 이름을 입력해주세요. : ");
				sname = sc.nextLine();
				System.out.println("");
				
				for(Address a:array) {
					if(sname.equals(a.name)) {
						a.print();
						find = true;
					}
				}
				if(find == false) {
					System.out.println("등록된 이름이 아닙니다.");
				}
				break;
				
			case 3 : // 전체 주소 데이터를 출력 
				for(Address a:array) { // array라는 바구니에서 하나씩 꺼내 a에게 넣겠다.
					a.print();
				}
				break;
				
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.");
				break;
				
			default :
				System.out.println("0~3번 메뉴를 선택해주세요.");
			}
		}
	}
}
