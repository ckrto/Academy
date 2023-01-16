package ex09;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Example5 {
	public static void execute() {
		Scanner sc = new Scanner(System.in);
		File file = new File("c:/data/java/score.txt");
		
		String sname;
		String dname;
		String line = "";
		String strLine = "";
		String sel;
		
		int menu;
		
		boolean find =  true;
		boolean run = true;
		
		System.out.println("안녕하세요. 성적 관리 시스템입니다. 아래 항목 중 선택해주세요.");
		
		while(run) {	
			System.out.println("------------------------------------------------");
			System.out.println("1.성적등록 | 2.성적검색 | 3.성적목록 | 4.성적삭제 | 0.종료");
			System.out.println("------------------------------------------------");
			System.out.print("선택해주세요. : ");
			menu = sc.nextInt(); sc.nextLine();
			System.out.println("");
			
			switch(menu) {
			case 1 :
				try {
					FileWriter writer = new FileWriter(file, true);
					Score score = new Score();
					
					System.out.print("이름을 입력해주세요 : ");
					score.setName(sc.nextLine());
					
					System.out.print("국어 점수를 입력해주세요 : ");
					score.setKor(sc.nextInt()); sc.nextLine();
					
					System.out.print("영어 점수를 입력해주세요 : ");
					score.setEng(sc.nextInt()); sc.nextLine();
					
					System.out.print("수학 점수를 입력해주세요 : ");
					score.setMat(sc.nextInt()); sc.nextLine();
					
					writer.write(score.getName() + "|");
					writer.write(score.getKor() + "|");
					writer.write(score.getEng() + "|");
					writer.write(score.getMat() + "\n");
					writer.flush();
					writer.close();
				
					System.out.println("성적이 등록되었습니다.\n");
					
				} catch(Exception e) {
					
				}
				break;
			case 2 :
				find = false;
				
				System.out.print("이름을 입력해주세요. : ");
				sname = sc.nextLine();
				
				System.out.println("");
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					
					
					while((line=reader.readLine()) != null) {
						StringTokenizer st = new StringTokenizer(line, "|");
						Score score = new Score();
						score.setName(st.nextToken());
						score.setKor(Integer.parseInt(st.nextToken()));
						score.setEng(Integer.parseInt(st.nextToken()));
						score.setMat(Integer.parseInt(st.nextToken()));
						if(sname.equals(score.getName())) {
							find = true;
							System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
							score.printList();
						}
					}
					if(find == false) {
						System.out.println("등록된 학생이 아닙니다.");
					}
					reader.close();
					
				} catch(Exception e) {
					
				}
				System.out.println("");
				break;
			case 3 : 
				try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					System.out.println("이름\t국어\t영어\t수학\t총점\t평균");
					
					while((line=reader.readLine()) != null){
						StringTokenizer st = new StringTokenizer(line, "|");
						Score score = new Score();
						score.setName(st.nextToken());
						score.setKor(Integer.parseInt(st.nextToken()));
						score.setEng(Integer.parseInt(st.nextToken()));
						score.setMat(Integer.parseInt(st.nextToken()));
						score.printList();
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
						Score score = new Score();
						score.setName(st.nextToken());
						score.setKor(Integer.parseInt(st.nextToken()));
						score.setEng(Integer.parseInt(st.nextToken()));
						score.setMat(Integer.parseInt(st.nextToken()));
						if(dname.equals(score.getName())) {	
							find = true;
						}
						else {
							strLine += score.getName() + "|";
							strLine += score.getKor() + "|";
							strLine += score.getEng() + "|";
							strLine += score.getMat() + "\n";	
						}
					}
					if(find == false) {
						System.out.println("등록된 학생이 아닙니다.");
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
