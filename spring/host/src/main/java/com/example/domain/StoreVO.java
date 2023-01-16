package com.example.domain;

public class StoreVO {
	private String s_code;
	private String s_name;
	private String s_admin;
	private String s_location;
	private String s_tel;
	private String s_c_code;
	private int s_waiting;
	private int s_order;
	private double s_rating;
	private boolean s_status;
	private String s_photo;
	public String getS_code() {
		return s_code;
	}
	public void setS_code(String s_code) {
		this.s_code = s_code;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_admin() {
		return s_admin;
	}
	public void setS_admin(String s_admin) {
		this.s_admin = s_admin;
	}
	public String getS_location() {
		return s_location;
	}
	public void setS_location(String s_location) {
		this.s_location = s_location;
	}
	public String getS_tel() {
		return s_tel;
	}
	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}
	public String getS_c_code() {
		return s_c_code;
	}
	public void setS_c_code(String s_c_code) {
		this.s_c_code = s_c_code;
	}
	public int getS_waiting() {
		return s_waiting;
	}
	public void setS_waiting(int s_waiting) {
		this.s_waiting = s_waiting;
	}
	public int getS_order() {
		return s_order;
	}
	public void setS_order(int s_order) {
		this.s_order = s_order;
	}
	public double getS_rating() {
		return s_rating;
	}
	public void setS_rating(double s_rating) {
		this.s_rating = s_rating;
	}
	public boolean isS_status() {
		return s_status;
	}
	public void setS_status(boolean s_status) {
		this.s_status = s_status;
	}
	public String getS_photo() {
		return s_photo;
	}
	public void setS_photo(String s_photo) {
		this.s_photo = s_photo;
	}
	
	@Override
	public String toString() {
		return "StoreVO [s_code=" + s_code + ", s_name=" + s_name + ", s_admin=" + s_admin + ", s_location="
				+ s_location + ", s_tel=" + s_tel + ", s_c_code=" + s_c_code + ", s_waiting=" + s_waiting + ", s_order="
				+ s_order + ", s_rating=" + s_rating + ", s_status=" + s_status + ", s_photo=" + s_photo + "]";
	}
}
