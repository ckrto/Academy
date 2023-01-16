package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportVO extends UserVO{
	private String r_code;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date r_date;
	
	private String r_result;
	private String r_state;
	private String r_type;
	
	public String getR_code() {
		return r_code;
	}
	public void setR_code(String r_code) {
		this.r_code = r_code;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getR_result() {
		return r_result;
	}
	public void setR_result(String r_result) {
		this.r_result = r_result;
	}
	public String getR_state() {
		return r_state;
	}
	public void setR_state(String r_state) {
		this.r_state = r_state;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	
	@Override
	public String toString() {
		return "ReportVO [r_code=" + r_code + ", r_date=" + r_date + ", r_result=" + r_result + ", r_state=" + r_state
				+ ", r_type=" + r_type + "]";
	}
}
