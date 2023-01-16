package ex17;

public class FacilityVO {
	private String code;
	private String fcode;
	private String fname;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public String toString() {
		return "FacilityVO [code=" + code + ", fcode=" + fcode + ", fname=" + fname + "]";
	}
	
}
