package ex17;

public class TypeVO {
	private String code;
	private String tcode;
	private String tname;
	private int qnt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getQnt() {
		return qnt;
	}

	public void setQnt(int qnt) {
		this.qnt = qnt;
	}

	@Override
	public String toString() {
		return "TypeVO [code=" + code + ", tcode=" + tcode + ", tname=" + tname + ", qnt=" + qnt + "]";
	}
	
}
