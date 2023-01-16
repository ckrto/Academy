package ex08;

public class People {
	private String jno;
	private String name;
	
	public People() {
		
	}
	
	public People(String jno, String name) {
		super();
		this.jno = jno;
		this.name = name;
	}

	public String getJno() {
		return jno;
	}

	public void setJno(String jno) {
		this.jno = jno;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void print() {
		System.out.println("주민번호 : " + jno);
		System.out.println("이름 : " + name);
	}
	
}
