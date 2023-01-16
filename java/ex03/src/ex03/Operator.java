package ex03;

public class Operator {
	public static void execute() {
		System.out.println("---------- 연산자 ----------\n");
		
		System.out.println("1.산술연산자");
		int a = 11;
		int b = 4;
		int c = a + b;
		int d = a - b;
		int e = a * b;
		double f = (double)a / b;
		int g = a % b;
		
		System.out.println(a + " + " + b + " = " + c);
		System.out.println(a + " - " + b + " = " + d);
		System.out.println(a + " x " + b + " = " + e);
		System.out.println(a + " / " + b + " = " + f);
		System.out.println(a + " / " + b + "의 나머지 = " + g);
		
		System.out.println("2.비교연산자");
		boolean result = (a >= b);
		System.out.println(a + " >= " + b + " = " + result);
		result = (a != b);
		System.out.println(a + " == " + b + " = " + result);
		
		System.out.println("3.논리연산자");
		result = (a > 5) && (b > 5);
		System.out.println("결과 : " + result);
		result = (a > 5) || (b > 5);
		System.out.println("결과 : " + result);
		result = (a >= 10) && (b <= 5);
		System.out.println("결과 : " + result);
		result = (a <= 10) || (b >= 5);
		System.out.println("결과 : " + result);
		
		String gender = "남자";
		int age = 22;
		
		// 성별이 여자이고 나이가 20세 이상입니까?
		result = (gender == "여자") && (age >= 20);
		System.out.println("결과 : " + result);
		
		// 성별이 남자이거나 나이가 50세 이상입니까?
		result = (gender == "남자") || (age >= 50);
		System.out.println("결과 : " + result);
		
	}
}
