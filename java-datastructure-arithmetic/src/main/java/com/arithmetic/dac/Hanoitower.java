package com.arithmetic.dac;

public class Hanoitower {

	public static void main(String[] args) {
		hanoiTower(10, 'A', 'B', 'C');
	}
	
	//��ŵ�����ƶ��ķ���
	//ʹ�÷����㷨
	
	public static void hanoiTower(int num, char a, char b, char c) {
		//���ֻ��һ����
		if(num == 1) {
			System.out.println("��1���̴� " + a + "->" + c);
		} else {
			//��������� n >= 2 ������������ǿ��Կ����������� 1.���±ߵ�һ���� 2. �����������
			//1. �Ȱ� ������������� A->B�� �ƶ����̻�ʹ�õ� c
			hanoiTower(num - 1, a, c, b);
			//2. �����±ߵ��� A->C
			System.out.println("��" + num + "���̴� " + a + "->" + c);
			//3. ��B���������� �� B->C , �ƶ�����ʹ�õ� a��  
			hanoiTower(num - 1, b, a, c);
			
		}
	}

}
