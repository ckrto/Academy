package ex03;

import java.util.Random;

public class Condition1 {
	public static void execute() {
		
		//조건문 (switch case)
		int[] count = new int[7];
		Random random = new Random();
		
		for(int i=0; i<10; i++)
		{
			switch((int)(Math.random()*6)+1) {
			case 1:
				System.out.println("1번이 나왔습니다.");
				count[1]++;
				break;
			case 2:
				System.out.println("2번이 나왔습니다.");
				count[2]++;
				break;
			case 3:
				System.out.println("3번이 나왔습니다.");
				count[3]++;
				break;
			case 4:
				System.out.println("4번이 나왔습니다.");
				count[4]++;
				break;
			case 5:
				System.out.println("5번이 나왔습니다.");
				count[5]++;
				break;
			case 6:
				System.out.println("6번이 나왔습니다.");
				count[6]++;
				break;
			default:
				break;
			}
		}
		for(int j = 1; j <= 6; j++)
			System.out.println(j +"번이 " + count[j] + "번 나왔습니다.");

	}
}
