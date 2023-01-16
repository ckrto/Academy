package ex15;

public class DeptVO {
	private String code;
	private String dname;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	@Override
	public String toString() {
		return "DeptVO [code=" + code + ", dname=" + dname + "]";
	}
}
