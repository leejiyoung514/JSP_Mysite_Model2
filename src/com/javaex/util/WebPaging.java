package com.javaex.util;

import java.util.ArrayList;

import com.javaex.vo.BoardVO;

public class WebPaging {
	private int totalSize; // 리스트 전체 갯수
	private int listSize=5; // 페이지당 표시행수: 5개
	private int blockSize=5; // 한블럭당 표시 페이지수: 5개
	private int totalPage; // 전체 페이지수:
	private int totalBlock;// 전체 블럭수

	private int nowPage = 1; // 현재 페이지
	private int nowBlock = 1; // 현재 블럭

	private int startNo = 0; // 리스트 목록의 시작위치
	private int endNo = 0; // 리스트 목록의 마지막 위치

	private int startPage; // 한블럭에 표시할 시작 페이지번호
	private int endPage; //한 블럭에 표시할 마지막 페이지번호

	public WebPaging() {
	}





	public int getTotalSize() {
		return totalSize;
	}





	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}





	public int getListSize() {
		return listSize;
	}





	public void setListSize(int listSize) {
		this.listSize = listSize;
	}





	public int getBlockSize() {
		return blockSize;
	}





	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}





	public int getTotalPage() {
		return totalPage;
	}





	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}





	public int getTotalBlock() {
		return totalBlock;
	}





	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}





	public int getNowPage() {
		return nowPage;
	}





	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}





	public int getNowBlock() {
		return nowBlock;
	}





	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}





	public int getStartNo() {
		return startNo;
	}





	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}





	public int getEndNo() {
		return endNo;
	}





	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}





	public int getStartPage() {
		return startPage;
	}





	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}





	public int getEndPage() {
		return endPage;
	}





	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}





	public void makePage(int listSize, int blockSize) {
		totalPage = (int) Math.ceil(totalSize * 1.0 / listSize);
		totalBlock = (int) Math.ceil(totalPage * 1.0 / blockSize);
        endNo=nowPage*listSize;
           if(endNo>totalSize) {
        	   endNo=totalSize;
           }
        startNo=endNo-listSize+1;
        
        endPage=nowBlock*blockSize;
           if(endPage>totalPage) {
        	   endPage=totalPage;
           }
        startPage=endPage-blockSize+1;
	}
        
        
        





	
	
	
}
