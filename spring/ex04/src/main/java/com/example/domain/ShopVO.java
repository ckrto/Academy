package com.example.domain;

public class ShopVO {
	private String code;
	private String title;
	private String image;
	private double price;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ShopVO [code=" + code + ", title=" + title + ", image=" + image + ", price=" + price + "]";
	}
}
