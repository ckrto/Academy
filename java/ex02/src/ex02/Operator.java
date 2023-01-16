package ex02;

public class Operator {
	public static void operator() {
		
		// 단항연산자
		int x = 10;
		
		int z = x++;
		System.out.println(z);
		System.out.println(x);
		
		x = x + 1;
		System.out.println(x);
		
		x--;
		System.out.println(x);
		
		x--;
		System.out.println(x);
		
		int y = 10;
		int k = y--;
		System.out.println(k);
		System.out.println(y);
		
		// 산술연산자
		int a = 9;
		int b = 4;
		int c = a % b;
		System.out.println(c);
		
		//문자열 연결
		String name = "홍길동\n";
		String school = "인천일보 아카데미";
		String info = name + school;
		System.out.println(info);
		
		//비교 연산자
		int num1 = 9;
		int num2 = 10;
		
		boolean result = num1 > num2;
		System.out.println(result);
		
		result = num1 < num2;
		System.out.println(result);
		
		result = num1 == num2;
		System.out.println(result);
		
		result = num1 != num2;
		System.out.println(result);
		
		//논리 연산자
		boolean value1 = true;
		boolean value2 = false;
		
		result = value1 && value2;
		System.out.println(result);
		
		result = value1 || value2;
		System.out.println(result);
		
		result = value1 =! value2;
		System.out.println(result);
		
		int n1 = 9;
		int n2 = 10;
		result = (n1 > n2) && (n1 == n2);
		
		//20세 이상의 남자만 입장가능
		char gender = 'M';
		int age = 33;
		result = (gender == 'M') && (age >= 20);
		System.out.println("입장가능(true), 불가능(flase) : " + result);
		
		//삼항 연산자
		String strResult = result == true ? "입장가능" : "입장불가능";
		System.out.println(strResult);
	}
}
