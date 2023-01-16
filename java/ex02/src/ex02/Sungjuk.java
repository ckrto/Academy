package ex02;

public class Sungjuk { // 클래스
	public static void grade() { // 메소드
		
		char ban1 = 'A', ban2 = 'B', ban3 = 'C';
		
		String name1 = "홍길동", name2 = "심청이", name3 = "강감찬";
		
		int kor1 = 90, kor2 = 99, kor3 = 88;
		int eng1 = 88, eng2 = 80, eng3 = 79;
		int mat1 = 88, mat2 = 80, mat3 = 55;
		
		int tot1 = kor1 + eng1 + mat1;
		int tot2 = kor2 + eng2 + mat2;
		int tot3 = kor3 + eng3 + mat3;
		
		double avg1 = tot1/3.;
		double avg2 = tot2/3.;
		double avg3 = tot3/3.;
		
		
		System.out.println("********************** 성 적 표 **********************");
		System.out.println(" 반\t이름\t국어\t영어\t수학\t총점\t평균\t");
		System.out.println( ban1 + "\t" + name1 + "\t" + kor1 + "\t" + eng1 + "\t" + mat1 + "\t" + tot1 + "\t" + avg1);
		System.out.println( ban2 + "\t" + name2 + "\t" + kor2 + "\t" + eng2 + "\t" + mat2 + "\t" + tot2 + "\t" + avg2);
		System.out.println( ban3 + "\t" + name3 + "\t" + kor3 + "\t" + eng3 + "\t" + mat3 + "\t" + tot3 + "\t" + avg3);
	}
}
