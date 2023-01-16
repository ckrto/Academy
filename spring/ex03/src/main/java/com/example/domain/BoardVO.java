package com.example.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BoardVO {
	private int replycnt;
	private int viewcnt;
	private int bno;
	private String title;
	private String content;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone ="Asia/Seoul")
	private Date regdate;
	
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone ="Asia/Seoul")
	private Date updatedate;
	private String writer;

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() { 
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "BoardVO [replycnt=" + replycnt + ", viewcnt=" + viewcnt + ", bno=" + bno + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", updatedate=" + updatedate + ", writer=" + writer
				+ "]";
	}
	
}
