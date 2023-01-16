package ex07;

import java.util.Scanner;

public class Example2 {
	public static void execute() {
		
		Scanner sc = new Scanner(System.in);
		Area a = new Area();
		
		int x;
		int y;
		double z;
		int menu;
		
		boolean run = true;
		
		System.out.println("안녕하세요 도형의 면적을 구하는 시스템입니다. 아래의 항목 중 선택해주세요.");
		
		while(run) {
			System.out.println("---------------------------------------");
			System.out.println("1.정사각형 | 2.직사각형 | 3.원 | 0.종료");
			System.out.println("---------------------------------------");
			System.out.print("선택해주세요. : ");
			menu = sc.nextInt();
			System.out.println("");
			
			switch(menu) {
			case 1 : 
				System.out.println("정사각형의 넓이를 구하는 탭입니다.");
				System.out.print("한 변의 값을 입력해주세요 : ");
				x = sc.nextInt();
				System.out.printf("한변의 길이가 %d인 정사각형의 넓이는 %d 입니다.\n", x, a.area(x));
				System.out.println("");
				break;
			case 2 : 
				System.out.println("직사각형의 넓이를 구하는 탭입니다.");
				System.out.print("가로의 길이를 입력해주세요. : ");
				x = sc.nextInt();
				System.out.print("세로의 길이를 입력해주세요. : ");
				y = sc.nextInt();
				System.out.printf("가로 %d, 세로 %d인 직사각형의 넓이는 %d 입니다.\n", x, y, a.area(x, y));
				System.out.println("");
				break;
			case 3 :
				System.out.println("원의 넓이를 구하는 탭입니다.");
				System.out.print("반지름을 입력해주세요. : ");
				z = sc.nextInt();
				System.out.printf("반지름이 %.0f인 원의 넓이는 %.2f 입니다.\n", z, a.area(z));
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
