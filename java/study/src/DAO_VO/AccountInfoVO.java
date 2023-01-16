package DAO_VO;

public class AccountInfoVO {
	
	//field
	private String accountTypeNo;
	private String accountTypeName;
	private String accountType;
	
	
	//getter setter toString method
	public String getAccountTypeNo() {
		return accountTypeNo;
	}
	public void setAccountTypeNo(String accountTypeNo) {
		this.accountTypeNo = accountTypeNo;
	}
	public String getAccountTypeName() {
		return accountTypeName;
	}
	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Override
	public String toString() {
		return "AccountInfoVO [accountTypeNo=" + accountTypeNo + ", accountTypeName=" + accountTypeName
				+ ", accountType=" + accountType + "]";
	}
	
}
