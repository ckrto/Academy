package ex08;

public class Student extends People {
	private String sno;
	
	public Student() {
		
	}

	public Student(String jno, String name, String sno) {
		super(jno, name);
		this.sno = sno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}
	
	@Override
	public void print() {
		super.print();
		System.out.println("ÇÐ¹ø : " + sno);
	}
}
