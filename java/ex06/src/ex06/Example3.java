package ex06;

import java.util.ArrayList;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Grade> grades = new ArrayList<>();
		
		String name;
		String nname;
		String dname;
		String uname;
		String kor;
		String eng;
		String mat;
		String sel;
		
		int menu;
		
		boolean find = false;
		boolean run = true;
		
		Grade grade = new Grade("홍길동", 99, 95, 97);
		grades.add(grade);
		
		grade = new Grade("심청이", 89, 88, 79);
		grades.add(grade);
		
		System.out.println("안녕하세요 성적 관리 시스템입니다.");
		
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
				System.out.println("안녕하세요. 사용자 등록 탭입니다.");
				System.out.print("이름을 입력해주세요 : ");
				name = sc.nextLine();
				
				System.out.print("국어 점수를 입력해주세요. : ");
				kor = sc.nextLine();
				
				System.out.print("영어 점수를 입력해주세요. : ");
				eng = sc.nextLine();
				
				System.out.print("수학 점수를 입력해주세요. : ");
				mat = sc.nextLine();
				
				grade = new Grade(name, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
				grades.add(grade);
				
				System.out.println("등록된 사용자의 수는 " + grades.size() + "명 입니다. \n");
				break;
			case 2 :
				find = false;
				System.out.println("안녕하세요. 사용자 검색 탭입니다.");
				System.out.println("안녕하세요. 사용자 검색 탭입니다.");
				System.out.print("검색하실 사용자의 이름을 입력하세요 : ");
				nname = sc.next(); sc.nextLine();
				
				for(Grade g:grades) {
					if(nname.equals(g.name)) {
						find = true;
						g.print();
						System.out.println("");
					}
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.");
				}
				break;
			case 3 : 
				System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
				for(Grade g:grades) {
					g.printList();
				}
				System.out.println("");
				break;
			case 4 :
				find = false;
				System.out.print("삭제할 이름을 입력해주세요 : ");
				dname = sc.nextLine();
				System.out.println("");
				
				for(Grade g:grades) {
					if(dname.equals(g.name)) {
						find = true;
						g.print();
						System.out.println("");
						System.out.print("진짜로 삭제하실건가요(예 : Y, 아니요 : N)?");
						sel = sc.nextLine();
						
						if(sel.equals("Y") || sel.equals("y")) {
							grades.remove(g);
							System.out.println("삭제가 완료되었습니다.\n");
							break;
						}
						if(sel.equals("N") || sel.equals("n")) {
							System.out.println("삭제를 취소했습니다.\n");
							break;
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.\n");
				}
				break;
			case 5 :
				find = false;
				System.out.print("수정하실 이름을 입력해주세요 : ");
				uname = sc.nextLine();
				for(Grade g:grades) {
					if(uname.equals(g.name)) {
						find = true;
						
						System.out.print("사용자의 이름은 " + g.name + " 입니다. 수정하실 이름을 입력해주세요 : ");
						name = sc.nextLine();
						
						System.out.print(g.name + "님의 국어 점수는 " + g.kor + " 입니다. 수정하실 국어 점수를 입력해주세요 : ");
						kor = sc.nextLine();
						
						System.out.print(g.name + "님의 영어 점수는 " + g.eng + " 입니다. 수정하실 영어 점수를 입력해주세요 : ");
						eng = sc.nextLine();
						
						System.out.print(g.name + "님의 수학 점수는 " + g.mat + " 입니다. 수정하실 수학 점수를 입력해주세요 : ");
						mat = sc.nextLine();
						
						System.out.print("수정하시겠습니까(예 : Y, 아니요 : N)?");
						sel = sc.nextLine();
						
						System.out.println("");
						
						if(sel.equals("Y") || sel.equals("y")) {
							if(!name.equals("")) {
								g.name = name;
							}
							if(!kor.equals("")) {
								g.kor = Integer.parseInt(kor);
							}
							if(!eng.equals("")) {
								g.eng = Integer.parseInt(eng);
							}
							if(!mat.equals("")) {
								g.mat = Integer.parseInt(mat);
							}
							
							System.out.println("------수정 결과------");
							g.print();
							System.out.println("수정이 완료되었습니다.");
							System.out.println("");
						}
					}
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.\n");
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
