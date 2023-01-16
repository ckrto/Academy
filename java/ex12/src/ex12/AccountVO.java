package ex12;

public class AccountVO {
	private int no;
	private String name;
	private int balance;
	
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
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", name=" + name + ", balance=" + balance + "]";
	}
	
}
