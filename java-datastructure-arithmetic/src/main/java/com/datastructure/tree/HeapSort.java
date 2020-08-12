package com.datastructure.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		//Ҫ�����������������
		//int arr[] = {4, 6, 8, 5, 9};
		// ����Ҫ��80000�������������
		int[] arr = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
		}

		System.out.println("����ǰ");
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ����=" + date1Str);
		
		heapSort(arr);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("����ǰ��ʱ����=" + date2Str);
		//System.out.println("�����=" + Arrays.toString(arr));
	}

	//��дһ��������ķ���
	public static void heapSort(int arr[]) {
		int temp = 0;
		System.out.println("������!!");
		
//		//�ֲ����
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("��һ��" + Arrays.toString(arr)); // 4, 9, 8, 5, 6
//		
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("��2��" + Arrays.toString(arr)); // 9,6,8,5,4
		
		//����������մ���
		//���������й�����һ���ѣ���������������ѡ��󶥶ѻ�С����
		for(int i = arr.length / 2 -1; i >=0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		
		/*
		 * 2).���Ѷ�Ԫ����ĩβԪ�ؽ����������Ԫ��"��"������ĩ��;
����			3).���µ����ṹ��ʹ������Ѷ��壬Ȼ����������Ѷ�Ԫ���뵱ǰĩβԪ�أ�����ִ�е���+�������裬ֱ��������������
		 */
		for(int j = arr.length-1;j >0; j--) {
			//����
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j); 
		}
		
		//System.out.println("����=" + Arrays.toString(arr)); 
		
	}
	
	//��һ������(������), ������һ���󶥶�
	/**
	 * ���ܣ� ��� �� �� i ��Ӧ�ķ�Ҷ�ӽ����������ɴ󶥶�
	 * ����  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => �õ� {4, 9, 8, 5, 6}
	 * ��������ٴε���  adjustHeap ������� i = 0 => �õ� {4, 9, 8, 5, 6} => {9,6,8,5, 4}
	 * @param arr ������������
	 * @param i ��ʾ��Ҷ�ӽ��������������
	 * @param lenght ��ʾ�Զ��ٸ�Ԫ�ؼ��������� length �����𽥵ļ���
	 */
	public  static void adjustHeap(int arr[], int i, int lenght) {
		
		int temp = arr[i];//��ȡ����ǰԪ�ص�ֵ����������ʱ����
		//��ʼ����
		//˵��
		//1. k = i * 2 + 1 k �� i�������ӽ��
		for(int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
			if(k+1 < lenght && arr[k] < arr[k+1]) { //˵�����ӽ���ֵС�����ӽ���ֵ
				k++; // k ָ�����ӽ��
			}
			if(arr[k] > temp) { //����ӽ����ڸ����
				arr[i] = arr[k]; //�ѽϴ��ֵ������ǰ���
				i = k; //!!! i ָ�� k,����ѭ���Ƚ�
			} else {
				break;//!
			}
		}
		//��for ѭ�������������Ѿ�����i Ϊ�������������ֵ�������� �(�ֲ�)
		arr[i] = temp;//��tempֵ�ŵ��������λ��
	}
	
}
