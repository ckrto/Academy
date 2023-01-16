package DAO_VO;

public class PasswordVO extends AccountVO{
	
	//field
	private String accountNo;
	private String password;
	private String passwordTypeNo;
	private String passwordAnswer;
	
	
	//getter setter toString method
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordTypeNo() {
		return passwordTypeNo;
	}
	public void setPasswordTypeNo(String passwordTypeNo) {
		this.passwordTypeNo = passwordTypeNo;
	}
	public String getPasswordAnswer() {
		return passwordAnswer;
	}
	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}
	@Override
	public String toString() {
		return "PasswordVO [accountNo=" + accountNo + ", password=" + password + ", passwordTypeNo=" + passwordTypeNo
				+ ", passwordAnswer=" + passwordAnswer + "]";
	}
}
