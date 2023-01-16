package ex06;

import java.util.ArrayList;
import java.util.Scanner;

public class Example4 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		ArrayList<Product> products = new ArrayList<>();
		
		String name;
		String sname;
		String dname;
		String nname;
		String uname;
		String price;
		String nprice;
		String qnt;
		String nqnt;
		String sel;
		
		Product product = new Product("세탁기", 500000, 50);
		products.add(product);
		
		product = new Product("냉장고", 900000, 20);
		products.add(product);
		
		product = new Product("선풍기", 50000, 100);
		products.add(product);
		
		int menu;
		
		boolean find = false;
		boolean run = true;
		
		System.out.println("안녕하세요. 상품관리 시스템입니다.");
		
		while(run) {
			System.out.println("아래의 항목 중 한가지를 선택해주세요.");
			System.out.println("-------------------------------");
			System.out.println("1.등록 | 2.검색 | 3.목록 | 4.삭제 | 5.수정 | 0.종료");
			System.out.println("-------------------------------");
			System.out.print("선택할 항목 : ");
			menu = sc.nextInt(); sc.nextLine();
	
			System.out.println("");
			switch(menu) {
			case 1 :
				System.out.println("안녕하세요. 상품 등록 탭입니다.");
				System.out.print("이름을 입력해주세요 : ");
				name = sc.nextLine();
				
				System.out.print("상품 단가를 입력해주세요. : ");
				price = sc.nextLine();
				
				System.out.print("판매 수량을 입력해주세요. : ");
				qnt = sc.nextLine();
				
				product = new Product(name, Integer.parseInt(price), Integer.parseInt(qnt));
				products.add(product);
				
				System.out.println("등록된 상품의 수는 " + products.size() + "개 입니다. \n");
				
				break;
			case 2 :
				find = false;
				
				System.out.print("검색하실 상품의 상품명을 입력하세요 : ");
				sname = sc.next(); sc.nextLine();
				
				for(Product p:products) {
					if(sname.equals(p.name)) {
						find = true;
						p.print();
						System.out.println("");
					}
				}
				if(find == false) {
					System.out.println("등록된 상품이 아닙니다.");
				}
				break;
			case 3 :
				System.out.println("상품명\t상품단가\t\t\t판매수량\t\t\t판매긍액\t\t\t\t등급");
				for(Product p:products) {
					p.printList();
				}
				System.out.println("");
				break;
			case 4 : 
				System.out.print("삭제할 상품의 상품명을 입력해주세요. : ");
				dname = sc.next(); sc.nextLine();
				
				for(Product p:products) {
					if(dname.equals(p.name)) {
						find = true;
						p.print();
						
						System.out.print("정말 삭제하실 건가요(예 : Y, 아니오 : N)? : ");
						sel = sc.next(); sc.nextLine();
						
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							products.remove(p);
							System.out.println("삭제가 완료되었습니다.\n");
							break;
						}
						else if (sel.equals("N") || sel.equals("n") || sel.equals("ㅜ")) {
							System.out.println("취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 상품이 아닙니다.");
				}
				break;
			case 5 :
				System.out.print("수정할 상품의 상품명을 입력해주세요. : ");
				uname = sc.next(); sc.nextLine();
				
				for(Product p:products) {
					if(uname.equals(p.name)) {
						find = true;
						System.out.print("상품명 : " + p.name + "을(를) 무엇으로 고치실건가요? : ");
						name = sc.nextLine();
						
						System.out.print("상품단가 : " + p.price + "을(를) 무엇으로 고치실건가요? : ");
						price = sc.nextLine();
						
						System.out.print("판매수량 : " + p.qnt + "을(를) 무엇으로 고치실건가요? : ");
						qnt = sc.nextLine();
						
						System.out.println("상품명 : " + name);
						System.out.println("상품단가 : " + price);
						System.out.println("판매수량 : " + qnt);
						System.out.println("");
						System.out.print("위의 값으로 수정하실건가요 (예 : Y , 아니요 : N)? : ");
						sel = sc.next(); sc.nextLine();
						
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							if(!name.equals("")) {
								p.name = name;
							}
							if(!price.equals("")) { 
								p.price = Integer.parseInt(price);
							}
							if(!qnt.equals("")) {
								p.qnt = Integer.parseInt(qnt);
							}
							System.out.println("");
							p.print();
							System.out.println("수정이 완료되었습니다.\n");
							break;
						}
						else if (sel.equals("N") || sel.equals("n") || sel.equals("ㅜ")) {
							System.out.println("취소하였습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 차량이 아닙니다.");
				}
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.");
				break;
			}
		}
	}
}
