package ex10;

import java.util.ArrayList;
import java.util.Scanner;

public class Example2 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		ScoreDAO dao = new ScoreDAOImpl();
		ArrayList<ScoreVO> array = new ArrayList<>();
		ScoreVO vo = null;
		
		int no = 400;
		int dno;
		int uno;
		int menu;
		
		String sel;
		String newName;
		String newGrade;
			
		boolean run = true;
		
		vo = new ScoreVO();
		vo.setNo(100);
		vo.setName("홍길동");
		vo.setGrade(90);
		array.add(vo);
		
		vo = new ScoreVO();
		vo.setNo(200);
		vo.setName("심청이");
		vo.setGrade(100);
		array.add(vo);
		
		vo = new ScoreVO();
		vo.setNo(300);
		vo.setName("강감찬");
		vo.setGrade(100);
		array.add(vo);
		
		while(run) {
			System.out.println("1.등록 | 2.검색 | 3.목록 | 4.수정 | 5.삭제 |0.종료");
			System.out.print("메뉴를 선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 : 
				System.out.println("귀하의 번호는 " + no + " 번 입니다.");
				vo = new ScoreVO();
				vo.setNo(no);
				
				System.out.print("이름을 입력해주세요 : ");
				vo.setName(sc.nextLine());
				
				System.out.print("점수를 입력해주세요 : ");
				vo.setGrade(sc.nextInt()); sc.nextLine();
				
				dao.insert(vo, array);
				
				System.out.println(vo);
				System.out.println(array);
				System.out.println("성적 등록이 완료되었습니다.");
				no += 100;
				System.out.println("");
				break;
			case 2 : 
				System.out.print("검색하실 번호를 입력해주세요. : ");
				no = sc.nextInt(); sc.nextLine();
				vo = dao.read(no, array);
				
				if(vo.getName() == null) {
					System.out.println("등록된 사용자가 아닙니다.");
				}
				else {
					System.out.println(vo.toString());
				}
				
				System.out.println("");
				break;
			case 3 :
				System.out.println("시스템 사용자 리스트입니다.");

				for(ScoreVO v : array) {
					System.out.println(v.toString());
				}
				System.out.println("");
				break;
			case 4 :
				System.out.println("수정하실 번호를 입력해주세요 : ");
				uno = sc.nextInt(); sc.nextLine();
				vo = dao.read(no, array);
				
				if(vo.getName() == null) {
					System.out.println("등록된 사용자가 아닙니다.\n");
				}
				else {
					System.out.println(vo.getName() + "을 무엇으로 바꿀까요? : ");
					newName = sc.nextLine();
					System.out.println(vo.getGrade() + "을 무엇으로 바꿀까요? : ");
					newGrade = sc.nextLine();
					
					if(!newName.equals(vo.getName())) {
						vo.setName(newName);
					}
					if(!newName.equals(vo.getGrade())) {
						vo.setGrade(Integer.parseInt(newGrade));
					}
					dao.update(vo, array);
					System.out.println("수정이 완료되었습니다.");
				}
				
				System.out.println("");
			break;
			case 5 : 
				System.out.print("삭제하실 번호를 입력해주세요. : ");
				dno = sc.nextInt(); sc.nextLine();
				
				for(ScoreVO v : array) {
					if(dno == v.getNo()) {
						dao.delete(dno, array);
						System.out.println("삭제가 완료되었습니다.");
						break;
					}
					else {
						System.out.println("등록된 사용자가 아닙니다.");
						break;
					}
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
