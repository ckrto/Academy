package ex09;

public class Score {
	
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	private double avg;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		if(kor < 0 || kor > 100) {
			kor = 0;
		}
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		if(eng < 0 || eng > 100) {
			eng = 0;
		}
		this.eng = eng;
	}
	
	public int getMat() {
		return mat;
	}
	
	public void setMat(int mat) {
		if(mat < 0 || mat > 100) {
			mat = 0;
		}
		this.mat = mat;
	}
	
	public void printList() {
		sum = kor + eng + mat;
		avg = sum/3.;
		System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\n", name, kor, eng, mat, sum, avg);
	}
}
