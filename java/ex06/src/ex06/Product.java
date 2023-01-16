package ex06;

import java.text.DecimalFormat;

public class Product {
	DecimalFormat df = new DecimalFormat("#,###");
	String name;
	String grade = "";
	int price;
	int qnt;
	int sum;
	
	public Product(String name, int price, int qnt) {
		super();
		this.name = name;
		this.price = price;
		this.qnt = qnt;
	}
	
	public int sum() {
		sum = price * qnt;
		return sum;
	}
	
	public String grade() {
		if(sum() >= 1000000) {
			grade = "최우수";
		}
		else if(sum() >= 500000) {
			grade = "우수";
		}
		else {
			grade = "보통";
		}
		return grade;
	}
	
	public void print() {
		System.out.println("상품이름 : " + name);
		System.out.println("상품단가 : " + df.format(price));
		System.out.println("판매수량 : " + df.format(qnt));
		System.out.println("금액 : " + df.format(sum()));
		System.out.println("판매 등급 : " + grade());
	}
	
	public void printList() {
		System.out.println(name + "\t" + df.format(price) + "\t\t\t" + df.format(qnt) + "\t\t\t" + df.format(sum()) + "\t\t\t" + grade());
	}
}
