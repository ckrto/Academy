package ex07;

public class Score extends Student {
	private int no;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private double avg;
	private String type;
	
	public int getTot() {
		tot = kor + eng + mat;
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	
	public double getAvg() {
		avg = tot / 3.;
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Score [no=" + no + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", avg=" + avg
				+ ", type=" + type + "]";
	}
	
}
