package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TradeVO extends AccountVO{
	private int id;
	private String ano;
	private String type;
	private String tno;
	private double amount;
	private String famount;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date tradeDate;
	
	public String getFamount() {
		return famount;
	}
	public void setFamount(String famount) {
		this.famount = famount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	@Override
	public String toString() {
		return "TradeVO [id=" + id + ", ano=" + ano + ", type=" + type + ", tno=" + tno + ", amount=" + amount
				+ ", tradeDate=" + tradeDate + "]";
	}
}
