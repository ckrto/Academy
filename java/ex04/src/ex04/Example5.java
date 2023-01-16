package ex04;

public class Example5 {
	public static void execute() {
		// 배열 (하나의 변수명에 여러개의 데이터를 저장)
		
		int[] a= {10, 21, 13, 44, 88, 57, 61, 4};
		int sum = 0;
		
		System.out.println("3번째 : " + a[2]);
		System.out.println("6번째 : " + a[5]);
		
		System.out.println("----------------------");
		
		a[7] = 40;
		
		for(int i = 0; i < a.length; i++) { // a.length : 배열의 크기
			sum = sum + a[i];
			System.out.println(i+1 + "번째 : " + a[i]);
		}
		
		System.out.println("----------------------");
		System.out.println("총점 : " + sum);
		
		double avg = sum / (double) a.length;
		System.out.println("평균 :" + avg);
	}
}