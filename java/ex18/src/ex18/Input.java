package ex18;

import java.util.Scanner;

public class Input {
	public static int input(String title) {
		Scanner sc =new Scanner(System.in);
		String value="";
		boolean isNumber=false;
		do {
			System.out.print(title);
			value=sc.nextLine();
			isNumber=value.matches("-?\\d+(\\.\\d+)?");
			if(isNumber==false) System.out.println("숫자로입력하세요!");
		}while(isNumber==false);
		
		return Integer.parseInt(value);
	}
}
