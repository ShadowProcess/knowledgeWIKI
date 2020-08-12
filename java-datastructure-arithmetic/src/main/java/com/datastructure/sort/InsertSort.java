package com.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {
		//int[] arr = {101, 34, 119, 1, -1, 89}; 
		// ����Ҫ��80000�������������
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
		}

		System.out.println("��������ǰ");
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);
		
		insertSort(arr); //���ò��������㷨
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("����ǰ��ʱ����=" + date2Str);
		
		//System.out.println(Arrays.toString(arr));
		
		
		
		
	}
	
	//��������
	public static void insertSort(int[] arr) {
		int insertVal = 0;
		int insertIndex = 0;
		//ʹ��forѭ�����Ѵ����
		for(int i = 1; i < arr.length; i++) {
			//������������
			insertVal = arr[i];
			insertIndex = i - 1; // ��arr[1]��ǰ����������±�
	
			// ��insertVal �ҵ������λ��
			// ˵��
			// 1. insertIndex >= 0 ��֤�ڸ�insertVal �Ҳ���λ�ã���Խ��
			// 2. insertVal < arr[insertIndex] �������������û���ҵ�����λ��
			// 3. ����Ҫ�� arr[insertIndex] ����
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
				insertIndex--;
			}
			// ���˳�whileѭ��ʱ��˵�������λ���ҵ�, insertIndex + 1
			// ��������ⲻ�ˣ�����һ�� debug
			//���������ж��Ƿ���Ҫ��ֵ
			if(insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}
	
			//System.out.println("��"+i+"�ֲ���");
			//System.out.println(Arrays.toString(arr));
		}
		
		
		/*
		
		
		//ʹ�����Ƶ��ķ�ʽ�����⣬�������
		//��1�� {101, 34, 119, 1};  => {34, 101, 119, 1}
		
		
		//{101, 34, 119, 1}; => {101,101,119,1}
		//������������
		int insertVal = arr[1];
		int insertIndex = 1 - 1; //��arr[1]��ǰ����������±�
		
		//��insertVal �ҵ������λ��
		//˵��
		//1. insertIndex >= 0 ��֤�ڸ�insertVal �Ҳ���λ�ã���Խ��
		//2. insertVal < arr[insertIndex] �������������û���ҵ�����λ��
		//3. ����Ҫ�� arr[insertIndex] ����
		while(insertIndex >= 0 && insertVal < arr[insertIndex] ) {
			arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
			insertIndex--;
		}
		//���˳�whileѭ��ʱ��˵�������λ���ҵ�, insertIndex + 1
		//��������ⲻ�ˣ�����һ�� debug
		arr[insertIndex + 1] = insertVal;
		
		System.out.println("��1�ֲ���");
		System.out.println(Arrays.toString(arr));
		
		//��2��
		insertVal = arr[2];
		insertIndex = 2 - 1; 
		
		while(insertIndex >= 0 && insertVal < arr[insertIndex] ) {
			arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
			insertIndex--;
		}
		
		arr[insertIndex + 1] = insertVal;
		System.out.println("��2�ֲ���");
		System.out.println(Arrays.toString(arr));
		
		
		//��3��
		insertVal = arr[3];
		insertIndex = 3 - 1;

		while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
			insertIndex--;
		}

		arr[insertIndex + 1] = insertVal;
		System.out.println("��3�ֲ���");
		System.out.println(Arrays.toString(arr)); */
		
	}

}
