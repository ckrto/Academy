package ex18;

public class StatementVO {
	private int ano;
	private String odate;
	private String type;
	private int amount;

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "StatementDAO [ano=" + ano + ", sdate=" + odate + ", type=" + type + ", amount=" + amount + "]";
	}
	
}
