package ex07;

public class Address {
	
	private String name;
	private String tel;
	private String juso;
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
	
	public String getJuso() {
		return juso;
	}
	public void setJuso(String juso) {
		this.juso = juso;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "Address [name=" + name + ", tel=" + tel + ", juso=" + juso + ", no=" + no + "]";
	}
	
	
}
