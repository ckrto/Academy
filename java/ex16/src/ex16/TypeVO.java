package ex16;

public class TypeVO {
	private String tcode;
	private String tname;
	private String tdate;
	
	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getTcode() {
		return tcode;
	}
	
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "TypeVO [tcode=" + tcode + ", tname=" + tname + ", tdate=" + tdate + "]";
	}
	
	
}
