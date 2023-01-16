package ex13;

public class StudentVO {
	private int no;
	private String name;
	private String address;
	
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StudentVO [no=" + no + ", name=" + name + ", address=" + address + "]";
	}
	
	
}
