package com.javaex.util;

public class WebPaging {
	private int totalSize=0; //리스트(레코드) 전체 갯수
	private int listSize=5; //페이지당 표시 행수
	private int blockSize=5; // 한블럭당 표시 페이지수
	
    private int totalPage=(int)Math.ceil(totalSize*1.0/listSize); //전제페이지
  	
	private int totalBlock=(int)Math.ceil(totalPage*1.0/blockSize); //전체 블럭수
	
	
	private int nowPage=1;  //현재 페이지
	private int nowBlock=1;  //현재 블럭
	
	private int startNo=0; //리스트목록의 시작위치
	private int endNo=0; //리스트목록의 마지막위치
		
	private int startPage=0; //한 블럭에 표시할 시작페이지 번호
	private int endPage=0; //한 블럭에 표시할 시작페이지 번호
	
	
	
	
}
