package ex08;

public class Score extends Stu {
	private int grade;
	private String type;
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override // 부모에서 지정한 서식을 자식에서 새롭게 정의하는 것
	public void print() {
		super.print();
		System.out.println("구분 : " + type);
		System.out.println("점수 : " + grade);
	}
}
