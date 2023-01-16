package ex10;

public class ScoreVO {
	private int no;
	private String name;
	private int grade;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "ScoreVO [no=" + no + ", name=" + name + ", grade=" + grade + "]";
	}
	
}
