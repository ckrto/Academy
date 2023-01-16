package ex10;

public class StudentVO { // Value Object 데이터값을 저장
	private String name;
	private String tel;
	private String address;
	private int no;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "StudentVO [no = " + no + ", name = " + name + ", tel = " + tel + ", address = " + address + "]";
	}
	
	
}
