package ex06;

import java.text.DecimalFormat;

public class Grade {
	String name;
	int kor;
	int eng;
	int mat;
	int tot;
	double avg;
	
	public Grade() {
		
	}
	
	public Grade(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}

	public int total() {
		tot = kor + eng + mat;
		
		return tot;
	}
	public String average() {
		DecimalFormat df = new DecimalFormat("###.00");
		avg = total() / (double) 3;

		return df.format(avg);
	}
	
	public void print() {
		System.out.println("이름 : " + name);
		System.out.println("국어 : " + kor);
		System.out.println("영어 : " + eng);
		System.out.println("수학 : " + mat);
		System.out.println("총점 : " + total());
		System.out.println("평균 : " +average());
	}
	public void printList() {
		System.out.print(name + "\t" + kor + "\t" + eng + "\t" + mat + "\t" + total() + "\t" + average());
		System.out.println("");
	}

}
