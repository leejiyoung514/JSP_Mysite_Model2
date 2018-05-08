package com.javaex.util;

public class PageMaker {

	private int totalcount; // 전체 게시물 개수
	private int pagenum; // 현재 페이지 번호
	private int contentnum=10; // 한 페이지에 몇개 글을 표시할지
	private int startPage=1; // 현재 페이지 블록의 시작 페이지
	private int endPage=5; // 현재 페이지 블록의 마지막 페이지
	private boolean prev=false; // 이전 페이지로 가는 화살표 //처음 1페이지일때는 보이지 않기 때문에 false로 
	private boolean next; // 다음 페이지로 가는 화살표
	private int currentblock; // 현재 페이지 블록
	private int lastblock; // 마지막 페이지 블록
	
	public void prevnext(int pagenum) {
		if(pagenum>0 && pagenum<6) { //현재 페이지가 첫번째 블록 안에 있으면
			setPrev(false);
		    setNext(true);
		}else if(getLastblock()==getCurrentblock()) {
			setPrev(true);
			setNext(false);
		}else {
			setPrev(true);
			setNext(true);
		}
	}
	
	

	public int calcpage(int totalcount, int contentum) { // 전체 페이지를 계산하는 함수

		// 125/10=12.5 --->13페이지
		// 50/10=5 --->5페이지
		int totalpage = totalcount / contentnum;
		if (totalcount % contentnum > 0) {
			totalcount++;
		}
		return totalpage;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getContentnum() {
		return contentnum;
	}

	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int currentblock) { // 시작페이지는 블록을 가지고 구할 수 있음
		this.startPage = (currentblock * 5) - 4;
		/* this.currentblock = (currentblock*10)-9; */ // 한페이지 블록당 10개씩 페이지가 보이도록 함
		// 한페이지 블록마다 5개씩 페이지가 보이도록 함
		// 1//1 2 3 4 5
		// 2//6 7 8 9 10
		// 3//11 12 13
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int getlastblock, int getcurrentblock) {
		if (getlastblock == getcurrentblock) { //마지막페이지블록과 현재 페이지블록, 현재 페이지블록이 마지막 페이지블록일 경우 
			this.endPage = calcpage(getTotalcount(), getContentnum());
		} else {
            this.endPage=getStartPage()+4;
		}
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCurrentblock() {
		return currentblock;
	}

	public void setCurrentblock(int pagenum) { 
		//현재 페이지 블록은 페이지 번호를 통해서 구한다.
		//페이지번호 / 페이지그룹 안에 페이지 갯수
		//1p 1/5 ===> 0.2 ==> 0+1 =1 ==>페이지블록 1  변수를 int로 했기 때문에 자바에서는 결과값이 0으로 인식- 0은 페이지번호블럭이 될 수 없어서 (+1)
		//3p 3/5 ===> 0.xx==> 0+1 =1 ==>페이지블록 1   
 		//8p 8/5 ===> 1.6 ==> 1+1 =2 ==>페이지블록 2  형변환이 일어나면서 결과가 1이 됨 
		this.currentblock = pagenum/5;
		if(pagenum%5>0) {
			this.currentblock++;
		}
	}

	public int getLastblock() {
		return lastblock;
	}

	public void setLastblock(int totalcount) { // 마지막 페이지 블록
		//10, 5 => 10*5 =>50
		//125/50 =2.5=3
		this.lastblock = totalcount/(5*this.contentnum);
		if(totalcount%(5*this.contentnum)>0) {
			this.lastblock++;
		}
	}

}
