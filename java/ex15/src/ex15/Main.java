package ex15;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Example1.execute();

	}
	
	//숫자입력받기
	public static int input(String title) {
		Scanner s=new Scanner(System.in);
		String value="";
		boolean isNumber=false;
		do {
			System.out.print(title + ">");
			value=s.nextLine();
			isNumber=value.matches("-?\\d+(\\.\\d+)?");
			if(isNumber==false) System.out.println("숫자로입력하세요!");
		}while(isNumber==false);
		
		return Integer.parseInt(value);
	}
}
