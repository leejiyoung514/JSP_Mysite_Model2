package com.javaex.util;

public class WebPaging {
	private int totalSize=0; //����Ʈ(���ڵ�) ��ü ����
	private int listSize=5; //�������� ǥ�� ���
	private int blockSize=5; // �Ѻ��� ǥ�� ��������
	
    private int totalPage=(int)Math.ceil(totalSize*1.0/listSize); //����������
  	
	private int totalBlock=(int)Math.ceil(totalPage*1.0/blockSize); //��ü ����
	
	
	private int nowPage=1;  //���� ������
	private int nowBlock=1;  //���� ��
	
	private int startNo=0; //����Ʈ����� ������ġ
	private int endNo=0; //����Ʈ����� ��������ġ
		
	private int startPage=0; //�� ���� ǥ���� ���������� ��ȣ
	private int endPage=0; //�� ���� ǥ���� ���������� ��ȣ
	
	
	
	
}
