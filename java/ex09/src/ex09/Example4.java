package ex09;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Example4 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		File file = new File("c:/data/java/address.txt");
		
		String line = "";
		String strLine = "";
		String sname;
		String dname;
		String sel;
		
		int menu;
		
		boolean find = false;
		boolean run = true;
		
		System.out.println("안녕하세요 주소관리 시스템입니다.\n");
		
		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1.주소등록 | 2.주소검색 | 3.주소목록 | 4.주소삭제 | 0.종료");
			System.out.println("---------------------------------------------------");
			System.out.print("실행 하실를 선택해주세요 : ");
			menu = sc.nextInt(); sc.nextLine();	
			System.out.println("");
			
			switch(menu) {
			case 1 :
				Address add = new Address();
				System.out.print("이름을 입력해주세요  : ");
				add.setName(sc.nextLine());
				System.out.print("전화번호를 입력해주세요 : ");
				add.setTel(sc.nextLine());
				System.out.print("주소를 입력해주세요 : ");
				add.setJuso(sc.nextLine());
				System.out.println("");
				
				try {
					FileWriter writer = new FileWriter(file, true);
					writer.write(add.getName() + "|" + add.getTel() + "|" +  add.getJuso() + "\n");
					writer.flush();
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 2 :
				find = false;
				
				System.out.print("검색하실 이름을 입력해주세요 : ");
				sname = sc.nextLine();
				
				System.out.println("");
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					while((line = reader.readLine()) != null) {
						StringTokenizer st = new StringTokenizer(line, "|");
						add = new Address();						
						add.setName(st.nextToken());
						add.setTel(st.nextToken());
						add.setJuso(st.nextToken());
						if(sname.equals(add.getName())) {
							find = true;
							add.printList();	
						}
					}
					reader.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(find == false) {
					System.out.println("등록된 사용자가 아닙니다.");
				}
				System.out.println("");
				break;
			case 3 :
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					while((line = reader.readLine()) != null) {
						StringTokenizer st = new StringTokenizer(line, "|");
						add = new Address();						
						add.setName(st.nextToken());
						add.setTel(st.nextToken());
						add.setJuso(st.nextToken());
						add.printList();
					}
					reader.close();
					
				} catch (Exception e) {
					
				}
				System.out.println("");
				break;
			case 4 :
				find = false;
				
				System.out.print("삭제할 이름을 입력해주세요 : ");
				dname = sc.nextLine();
				System.out.println("");
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					while((line = reader.readLine()) != null) {
						StringTokenizer st = new StringTokenizer(line, "|");
						add = new Address();
						add.setName(st.nextToken());
						add.setTel(st.nextToken());
						add.setJuso(st.nextToken());
						if(dname.equals(add.getName())) {	
							find = true;
						}
						else {
							strLine += add.getName() + "|";
							strLine += add.getTel() + "|";
							strLine += add.getJuso() + "\n";	
						}
					}
					if(find == false) {
						System.out.println("등록된 사용자가 아닙니다.");
					} else {
						System.out.print("정말로 삭제하시겠습니까(예 : Y, 아니요 : N)? ");
						sel = sc.nextLine();
						System.out.println("");
						
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							FileWriter writer = new FileWriter(file, false);
							writer.write(strLine);
							writer.flush();
							writer.close();
							
							System.out.println(dname + "님이 삭제 되었습니다.\n");
							break;
						}
						else if(sel.equals("N") || sel.equals("n") || sel.equals("ㅜ")) {
							System.out.println("실행을 취소하였습니다.\n");
							break;
						}
					}
				} catch (Exception e) {
					
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
