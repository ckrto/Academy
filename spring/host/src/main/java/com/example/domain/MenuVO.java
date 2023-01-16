package com.example.domain;

public class MenuVO{
	private String s_code;
	private String m_name;
	private String m_price;
	private String m_photo;
	
	
	
	public String getS_code() {
		return s_code;
	}
	public void setS_code(String s_code) {
		this.s_code = s_code;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_price() {
		return m_price;
	}
	public void setM_price(String m_price) {
		this.m_price = m_price;
	}
	public String getM_photo() {
		return m_photo;
	}
	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}
	
	@Override
	public String toString() {
		return "MenuVO [s_code=" + s_code + ", m_name=" + m_name + ", m_price=" + m_price + ", m_photo=" + m_photo
				+ "]";
	}
	
}
