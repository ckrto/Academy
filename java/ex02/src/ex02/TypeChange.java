package ex02;

public class TypeChange {
	public static void change() {
		
		int intValue = 65; // 4byte
		char charValue = 'b'; // 2byte
		
		intValue = charValue;
		
		System.out.println(intValue);
		System.out.println(charValue);
		
		intValue = 5;
		double dubValue = 3.14; // 8byte
		dubValue = intValue;
		
		System.out.println(dubValue);
		
		intValue = 5;
		dubValue = 3.14;
		intValue = (int) dubValue;
		System.out.println(intValue);
		
		intValue = 34;
		charValue = (char) intValue;
		System.out.println(charValue);
		
		dubValue = 3.14;
		charValue = (char) dubValue;
		System.out.println(charValue);
		
		double number = (double)7/3;
		System.out.println(number);
		
	}

	
}
