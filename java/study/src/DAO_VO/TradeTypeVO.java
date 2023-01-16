package DAO_VO;

public class TradeTypeVO {
	private String tradeTypeNo;
	private String tradeType;
	
	public String getTradeTypeNo() {
		return tradeTypeNo;
	}
	public void setTradeTypeNo(String tradeTypeNo) {
		this.tradeTypeNo = tradeTypeNo;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	@Override
	public String toString() {
		return "TradeTypeVO [tradeTypeNo=" + tradeTypeNo + ", tradeType=" + tradeType + "]";
	}
	
	
}

