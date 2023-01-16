package ex07;

import java.util.ArrayList;
import java.util.Scanner;

public class Example5 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		
		String sel;
		String uname;
		String utel;
		String ujuso;
		
		
		int no;
		int sno;
		int dno;
		int uno;
		int menu;
		
		ArrayList<Address> array = new ArrayList<>();
		
		no = 10;
		Address a = new Address();
		a.setNo(no);
		a.setName("홍길동");
		a.setTel("010-0001-0001");
		a.setJuso("인천 서구 경서동");
		array.add(a);
		
		no = no + 1;
		a = new Address();
		a.setNo(no);
		a.setName("심청이");
		a.setTel("010-0002-0002");
		a.setJuso("서울 강남구 압구정동");
		array.add(a);
		
		no = no + 1;
		a = new Address();
		a.setNo(no);
		a.setName("홍길동");
		a.setTel("010-0003-0003");
		a.setJuso("인천 부평구 계산동");
		array.add(a);
		
		boolean find = true;
		boolean run = true;
		
		while(run) {
			System.out.println("안녕하세요. 사용자 정보 관리 시스템입니다. 아래 항목 중 선택해주세요.");
			System.out.println("------------------------------------------------");
			System.out.println("1.등록 | 2.검색 | 3.수정 | 4.삭제 | 5.목록 | 0.종료");
			System.out.println("------------------------------------------------");
			System.out.print("선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 :
				System.out.println("안녕하세요 사용자 등록 탭입니다.");
				
				no = no + 1;
				a = new Address();
				System.out.println("번호 : " + no);
				a.setNo(no);
				
				System.out.print("이름을 입력해주세요 : ");
				a.setName(sc.nextLine());
				
				System.out.print("전화번호를 입력해주세요 : ");
				a.setTel(sc.nextLine());
				
				System.out.print("주소를 입력해주세요 : ");
				a.setJuso(sc.nextLine());
				array.add(a);
				
				System.out.println("등록된 사용자 수는" + array.size() + "입니다.\n");
				break;
			case 2 :
				System.out.println("안녕하세요 사용자 정보 검색 탭입니다.");
				System.out.print("검색하실 번호를 입력해주세요 : ");
				sno = sc.nextInt();
				System.out.println("");
				
				find = false;
				
				for(Address ad:array) {
					if(sno == ad.getNo()) {
						find = true;
						System.out.println("이름 : " + ad.getName());
						System.out.println("전화번호 : " + ad.getTel());
						System.out.println("주소 : " + ad.getJuso());
					}
				}
				
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.");
				}
				System.out.println("");
				break;
			case 3: 
				System.out.println("안녕하세요. 정보 수정 탭입니다.");
				System.out.print("수정하실 사용자의 번호를 입력해주세요. : ");
				uno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				find = false;
				
				for(Address ad:array) {
					if(uno == ad.getNo()) {
						find = true;
						
						System.out.print("현재 이름은 " + ad.getName() + " 입니다 수정하실 이름을 입력해주세요 : ");
						uname = sc.nextLine();
						
						System.out.print("현재 전화번호는 " + ad.getTel() + " 입니다 수정하실 이름을 입력해주세요 : ");
						utel = sc.nextLine();
						
						System.out.print("현재 주소는 " + ad.getJuso() + " 입니다 수정하실 이름을 입력해주세요 : ");
						ujuso = sc.nextLine();
						
						System.out.println("");
						
						System.out.println("수정하실 정보입니다.");
						System.out.println("이름 : " + uname);
						System.out.println("전화번호 : " + utel);
						System.out.println("주소 : " + ujuso);
						System.out.println("");
						
						System.out.print("정말로 수정하시겠습니까(예 : Y, 아니요 : N)? : ");
						sel = sc.nextLine();
						System.out.println("");
						
						if(sel.equals("Y") || sel.equals("y")) {
							if(!uname.equals("")) {
								ad.setName(uname);
							}
							if(!utel.equals("")) {
								ad.setTel(utel);
							}
							if(!ujuso.equals("")) {
								ad.setJuso(ujuso);
							}
							System.out.println("수정이 완료되었습니다.\n");
							break;
						}
						else if(sel.equals("N") || sel.equals("n")) {
							System.out.println("실행을 취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.\n");
				}
				break;
			case 4 : 
				System.out.println("안녕하세요. 사용자 삭제 탭입니다.");
				System.out.print("삭제하실 번호를 입력해주세요. : ");
				dno = sc.nextInt(); sc.nextLine();
				System.out.println("");
				
				find = false;
				
				for(Address ad:array) {
					if(dno == ad.getNo()) {
						find = true;
						System.out.println("이름 : " + ad.getName());
						System.out.println("전화번호 : " + ad.getTel());
						System.out.println("주소 : " + ad.getJuso());
						System.out.println("");
						
						System.out.print("정말로 삭제하시겠습니까(예 : Y, 아니요 : N)?");
						sel = sc.nextLine();
						System.out.println("");
						
						if(sel.equals("Y") || sel.equals("y")) {
							array.remove(ad);
							System.out.println("삭제가 완료되었습니다.\n");
							break;
						}
						else if(sel.equals("N") || sel.equals("n")) {
							System.out.println("실행을 취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.\n");
				}
				
				break;
			case 5 :
				System.out.println("안녕하세요. 목록 탭입니다.");
				System.out.printf("번호\t이름\t전화번호\t\t주소\n");
				for(Address ad:array) {
					System.out.printf("%d\t%s\t%s\t%s\n", ad.getNo(), ad.getName(), ad.getTel(), ad.getJuso());
				}
				System.out.println("");
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 입력해주세요.\n");
				break;
			}
		}
	}
}
