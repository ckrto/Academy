package Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Example1 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAOImpl();
		StudentVO vo = null;
		
		int sno;
		int dno;
		int uno;
		int menu;
		
		boolean run = true;
		
		while(run) {
			System.out.println("안녕하세요 학생관리 시스템입니다.");
			System.out.println("--------------------------------------------------");
			System.out.println("1.등록 | 2.목록 | 3.검색 | 4.수정 | 5.삭제 | 0.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("실행할 작업을 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			
			switch(menu) {
			case 1 : 
				int newCode = dao.getCode() + 100;
				vo = new StudentVO();
				
				System.out.println("귀하의 번호는 " + newCode + " 입니다.");
				vo.setNo(newCode);
				
				System.out.print("이름을 입력해주세요. : ");
				vo.setName(sc.nextLine());
				
				System.out.print("전화번호를 입력해주세요. : ");
				vo.setTel(sc.nextLine());
				
				System.out.print("주소를 입력해주세요. : ");
				vo.setAddress(sc.nextLine());
				
				dao.insert(vo);
				System.out.println("등록이 완료되었습니다.\n");
				break;
			case 2 :
				ArrayList<StudentVO> array = dao.list();
				for(StudentVO v : array) {
					System.out.printf("%d\t%s\t%s\t%s\n", v.getNo(), v.getName(), v.getTel(), v.getAddress());
				}
				System.out.println("");
				
				System.out.println("전체데이터 갯수 : " + array.size() + "\n");
				break;
			case 3 :
				System.out.print("검색할 번호를 입력해주세요. : ");
				sno = sc.nextInt(); sc.nextLine();
				
				vo = dao.read(sno);
				
				if(vo.getName() == null) {
					System.out.println("검색한 학생이 존재하지 않습니다.\n");
				}
				else {
					System.out.println(vo.toString());
				}
				System.out.println("");
				break;
			case 4 :
				System.out.print("수정하실 번호를 입력해주세요. : ");
				uno = sc.nextInt(); sc.nextLine();
				vo = dao.read(uno);
				System.out.println("");
				
				if(vo.getName() == null) {
					System.out.println("수정하실 학생이 존재하지 않습니다.\n");
				}
				else {
					StudentVO v = new StudentVO();
					v.setNo(uno);
					System.out.print("현재 이름은 " + vo.getName() + " 입니다. 수정하실 이름을 입력해주세요. : ");
					String name = sc.nextLine();
					System.out.print("현재 전화번호는 " + vo.getTel() + " 입니다. 수정하실 전화번호를 입력해주세요. : ");
					String tel = sc.nextLine();
					System.out.print("현재 이름은 " + vo.getAddress() + " 입니다. 수정하실 주소를 입력해주세요. : ");
					String address = sc.nextLine();
					
					if(!name.equals("")) {
						vo.setName(name);
					}
					
					if(!tel.equals("")) {
						vo.setTel(tel);
					}
					
					if(!address.equals("")) {
						vo.setAddress(address);
					}
					
					dao.update(vo);
					System.out.println(vo.getName() + "님의 정보가 수정이 완료되었습니다.");
				}
				break;
			case 5 :
				System.out.print("삭제할 번호를 입력해주세요. : ");
				dno = sc.nextInt(); sc.nextLine();
				vo = dao.read(dno);
				if(vo.getName() == null) {
					System.out.println("삭제할 학생이 존재하지 않습니다.\n");
				}
				else {
					dao.delete(dno);
					System.out.println(vo.getName() + "이(가) 삭제가 완료되었습니다.\n");
				}
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
