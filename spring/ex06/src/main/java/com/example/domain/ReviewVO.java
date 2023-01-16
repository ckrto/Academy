package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReviewVO {
	private String s_code;
	private String u_code;
	private String r_content;
	private String r_photo;
	private String r_rating;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone ="Asia/Seoul")
	private Date r_date;

	public String getS_code() {
		return s_code;
	}

	public void setS_code(String s_code) {
		this.s_code = s_code;
	}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public String getR_photo() {
		return r_photo;
	}

	public void setR_photo(String r_photo) {
		this.r_photo = r_photo;
	}

	public String getR_rating() {
		return r_rating;
	}

	public void setR_rating(String r_rating) {
		this.r_rating = r_rating;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	@Override
	public String toString() {
		return "ReviewVO [s_code=" + s_code + ", u_code=" + u_code + ", r_content=" + r_content + ", r_photo=" + r_photo
				+ ", r_rating=" + r_rating + ", r_date=" + r_date + "]";
	}
	
}
