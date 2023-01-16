package ex05;

public class Calculator {
	//필드(속성)
	int num1 = 0;
	int num2 = 0;
	int sum;
	int sub;
	int mul;
	double div;
	
	//생성자
	
	//메소드
	public void execute() {
		sum = num1 + num2;
		sub = num1 + num2;
		mul = num1 * num2;
		div = num1 / (double) num2;
		
	}
	
	public void print() {
		System.out.println(num1 + " + " + num2 + " = " + sum);
		System.out.println(num1 + " - " + num2 + " = " + sub);
		System.out.println(num1 + " X " + num2 + " = " + mul);
		System.out.println(num1 + " / " + num2 + " = " + div);
		System.out.println("");
	}
} // 클래스 (객체를 생성하는 틀)
