package ex13;

public class SaleVO {
	private int no;
	private String date;
	private int sale;
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getSale() {
		return sale;
	}
	
	public void setSale(int sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "SaleVO [no=" + no + ", date=" + date + ", sale=" + sale + "]";
	}
	
	
}
