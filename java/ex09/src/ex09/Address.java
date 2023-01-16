package ex09;

public class Address {
	String name;
	String tel;
	String juso;
	
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
	
	@Override
	public String toString() {
		return "Address [name=" + name + ", tel=" + tel + ", juso=" + juso + "]";
	}
	
	public void printList() {
		System.out.printf("%s\t%s\t%s", name, tel, juso);
		System.out.println("");
	}
	
}
