package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountVO {
	private String ano;
	private String aname;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date openDate;
	
	private double balance;
	private String fbalance;
	
	public String getFbalance() {
		return fbalance;
	}
	public void setFbalance(String fbalance) {
		this.fbalance = fbalance;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountVO [ano=" + ano + ", aname=" + aname + ", openDate=" + openDate + ", balance=" + balance + "]";
	}
}
