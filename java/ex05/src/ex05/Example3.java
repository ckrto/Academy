package ex05;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Example3 {
	public static void execute() {
		DecimalFormat df = new DecimalFormat("#,###,###");
		Scanner sc = new Scanner(System.in);
		
		String[] name = new String[100];
		String search = "";
		
		int[] price = new int[100];
		int[] qnt = new int[100];
		int sel;
		int count = 0;
		int sum = 0;
		
		boolean find = false;
		boolean run = true;
		
		System.out.println("안녕하세요 매출현황 알림 시스템입니다.");
		
		while(run) {
			System.out.println("-----------------------------------------");
			System.out.println("1.상품등록 | 2.상품검색 | 3.매출현황 | 0. 종료");
			System.out.println("-----------------------------------------");
			System.out.print("실행할 작업을 선택해주세요 : ");
			sel = sc.nextInt();
			System.out.println("");
			
			switch(sel) {
			case 1 : 
				System.out.println("상품 등록 탭입니다.");
				System.out.print("입력할 상품의 갯수를 입력해주세요. : ");
				count = sc.nextInt();
				System.out.println("");
				
				for(int i = 0; i < count; i++) {
					System.out.print("상품명을 적어주세요. : ");
					name[i] = sc.next();
					
					System.out.print(name[i] + "의 단가를 적어주세요 : ");
					price[i] = sc.nextInt();
					
					System.out.print(name[i] + "의 판매 수량을 적어주세요 : ");
					qnt[i] = sc.nextInt();
					
					System.out.println("");
				}
				System.out.println(count +"개의 상품이 등록되었습니다.");
				break;
				
			case 2 :
				System.out.println("상품 검색 탭입니다.");
				System.out.print("검색하실 상품의 상품명을 입력해주세요. : ");
				search = sc.next();
				
				for(int i = 0; i < count; i++) {
					sum = price[i] * qnt[i];
					
					if(search.equals(name[i])) {
						find = true;
						System.out.println(name[i] + "의 단가는 " + df.format(price[i]) + "원 입니다.");
						System.out.println(name[i] + "의 남은 수량은 " + df.format(qnt[i]) + "개 입니다.");
					}
				}
				if (find == false) {
					System.out.println("등록된 상품이 아닙니다.");
				}
				System.out.println("");
				break;
				
			case 3 :
				System.out.println("매출 현황 탭입니다.");
				System.out.print("검색하실 상품의 상품명을 입력해주세요. : ");
				search = sc.next();
				
				for(int i = 0; i < count; i++) {
					if(search.equals(name[i])) {
						System.out.println("검색하신 "+ name[i] + "의 단가는 " + df.format(price[i]) + "원 등록된 수량은 " + df.format(qnt[i]) + "개 금액은 " + df.format(sum) + "원 입니다.");
					}
				}
				System.out.println("");
				break;
			case 0 :
				run = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
