package Sale;

import java.util.Scanner;

public class Example2 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		SaleDAO dao = new SaleDAO();
		SaleVO vo = null;
		
		int menu;
		int sno;
		int dno;
		int uno;
		String price;
		String qnt;
		
		String name;
		
		
		boolean run = true;
		
		while(run) {
			System.out.println("안녕하세요 매출관리 시스템입니다.");
			System.out.println("--------------------------------------------------");
			System.out.println("1.등록 | 2.목록 | 3.검색 | 4.수정 | 5.삭제 | 0.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			
			switch(menu) {
			case 1 :
				int newNo = dao.getNo() + 100;
				vo = new SaleVO();
				System.out.println("상품번호는 " + newNo + " 입니다.");
				vo.setNo(newNo);
				System.out.print("등록하실 상품의 이름을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				System.out.print("등록하실 상품의 단가를 입력해주세요. : ");
				vo.setPrice(sc.nextInt()); sc.nextLine();
				System.out.print("등록하실 상품의 판매 수량을 입력해주세요. : ");
				vo.setQnt(sc.nextInt()); sc.nextLine();
				dao.insert(vo);
				System.out.println("새로운 상품이 등록되었습니다.\n");
				break;
			case 2 :
				for(SaleVO v:dao.list()) {
					System.out.println(v.toString());
				}
				System.out.println("");
				break;
			case 3 :
				System.out.print("검색할 상품의 번호를 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				
				vo = dao.read(sno);
				if(vo.getName() == null) {
					System.out.println("검색한 상품이 존재하지 않습니다.");
				}
				else {
					System.out.println(vo.toString());
				}
				System.out.println("");
				break;
			case 4 : 
				System.out.print("수정하실 상품의 번호를 입력해주세요. : ");
				uno = sc.nextInt(); sc.nextLine();
				vo = dao.read(uno);
				if(vo.getName() == null) {
					System.out.println("수정할 상품이 존재하지 않습니다.");
				}
				else {
					SaleVO v = new SaleVO();
					v.setNo(uno);
					System.out.print("현재 이름은 " + vo.getName() + " 입니다. 수정하실 상품명을 입력해주세요. : ");
					name = sc.nextLine();
					System.out.print("현재 판매 단가는 " + vo.getPrice() + " 입니다. 수정하실 판매 단가를 입력해주세요. : ");
					price = sc.nextLine();
					System.out.print("현재 판매 수량은 " + vo.getQnt() + " 입니다. 수정하실 판매 수량을 입력해주세요. : ");
					qnt = sc.nextLine();
					
					if(!name.equals("")) {
						vo.setName(name);
					}
					if(!price.equals("")) {
						vo.setPrice(Integer.parseInt(price));
					}
					if(!qnt.equals("")) {
						vo.setQnt(Integer.parseInt(qnt));
					}
					dao.update(vo);
					System.out.println("상품의 정보가 수정되었습니다.");
				}
				
				System.out.println("");
				break;
			case 5 : 
				System.out.print("삭제할 상품의 번호를 입력해주세요. : ");
				dno = sc.nextInt(); sc.nextLine();
				vo = dao.read(dno);
				if(vo.getName() == null) {
					System.out.println("삭제할 상품이 존재하지 않습니다.");
				}
				else {
					dao.delete(dno);
					System.out.println(vo.getName() + "의 삭제가 완료되었습니다.");
				}
				
				System.out.println("");
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.\n");
				break;
			default :
				System.out.println("메뉴를 다시 선택해주세요.\n");
				break;
			}
		}
	}
}
