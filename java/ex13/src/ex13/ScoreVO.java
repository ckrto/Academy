package ex13;

public class ScoreVO {
	private int no;
	private String type;
	private int kor;
	private int eng;
	private int mat;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		this.kor = kor;
	}
	
	public int getEng() {
		return eng;
	}
	
	public void setEng(int eng) {
		this.eng = eng;
	}
	
	public int getMat() {
		return mat;
	}
	
	public void setMat(int mat) {
		this.mat = mat;
	}

	@Override
	public String toString() {
		return "ScoreVO [no=" + no + ", type=" + type + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + "]";
	}
	
	public void print() {
		int tot = kor + eng + mat;
		double avg = tot/3.;
		System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\n", type, kor, eng, mat, tot, avg);
	}
}
