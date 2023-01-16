package DAO_VO;

public class TradeVO extends TradeTypeVO {
	private String accountNo;
	private String tradeTypeNo;
	private int tradeBalance;
	private String tradeDate;
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getTradeTypeNo() {
		return tradeTypeNo;
	}
	
	public void setTradeTypeNo(String tradeTypeNo) {
		this.tradeTypeNo = tradeTypeNo;
	}
	
	public int getTradeBalance() {
		return tradeBalance;
	}
	
	public void setTradeBalance(int tradeBalance) {
		this.tradeBalance = tradeBalance;
	}
	
	public String getTradeDate() {
		return tradeDate;
	}
	
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		return "TradeVO [accountNo=" + accountNo + ", tradeTypeNo=" + tradeTypeNo + ", tradeBalance=" + tradeBalance
				+ ", tradeDate=" + tradeDate + "]";
	}
}