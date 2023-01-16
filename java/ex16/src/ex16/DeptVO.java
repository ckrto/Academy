package ex16;

public class DeptVO {
	private String dcode;
	private String dname;
	
	public String getDcode() {
		return dcode;
	}
	
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	
	public String getDname() {
		return dname;
	}
	
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	@Override
	public String toString() {
		return "DeptVO [dcode=" + dcode + ", dname=" + dname + "]";
	}
	
}
