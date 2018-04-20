package com.javaex.vo;

public class BoardVO {

	private int no; //글번호
	private String title; //제목
	private String content; //글내용
	private int hit; //조회수
	private String reg_date; //글을쓴날자
    private int user_no; //사용자번호
	
    private String user_name; //글쓴사람
	

	public BoardVO() {

	}

	public BoardVO(String title, String content, int hit, String reg_date, int user_no) {
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
	}
	
	public BoardVO(String title, String content, int user_no) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
	}
    
	
	   
	public BoardVO(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}

	public BoardVO(int no, String title, String user_name, int hit, String reg_date, int user_no) {
		this.no = no;
		this.title = title;
		this.user_name=user_name;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
	}
	
	public BoardVO(int no, String title, int hit, String reg_date, int user_no, String user_name) {
		this.no = no;
		this.title = title;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
		this.user_name = user_name;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", user_no=" + user_no + ", user_name=" + user_name + "]";
	}
	
	
	
}
