package ex08;

import java.util.Date;

public class Account {
	private int no;
	private int balance;
	private String name;
	private Date openDate;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		if(balance < 0) {
			balance = 0;
		}
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
}
