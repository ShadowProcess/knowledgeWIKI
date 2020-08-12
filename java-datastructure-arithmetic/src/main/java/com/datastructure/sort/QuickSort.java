package com.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {
		//int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
		
		//���Կ��ŵ�ִ���ٶ�
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
		
		quickSort(arr, 0, arr.length-1);
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("����ǰ��ʱ����=" + date2Str);
		//System.out.println("arr=" + Arrays.toString(arr));
	}

	public static void quickSort(int[] arr,int left, int right) {
		int l = left; //���±�
		int r = right; //���±�
		//pivot ����ֵ
		int pivot = arr[(left + right) / 2];
		int temp = 0; //��ʱ��������Ϊ����ʱʹ��
		//whileѭ����Ŀ�����ñ�pivot ֵС�ŵ����
		//��pivot ֵ��ŵ��ұ�
		while( l < r) { 
			//��pivot�����һֱ��,�ҵ����ڵ���pivotֵ,���˳�
			while( arr[l] < pivot) {
				l += 1;
			}
			//��pivot���ұ�һֱ��,�ҵ�С�ڵ���pivotֵ,���˳�
			while(arr[r] > pivot) {
				r -= 1;
			}
			//���l >= r˵��pivot ����������ֵ���Ѿ��������ȫ����
			//С�ڵ���pivotֵ���ұ�ȫ���Ǵ��ڵ���pivotֵ
			if( l >= r) {
				break;
			}
			
			//����
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			
			//���������󣬷������arr[l] == pivotֵ ��� r--�� ǰ��
			if(arr[l] == pivot) {
				r -= 1;
			}
			//���������󣬷������arr[r] == pivotֵ ��� l++�� ����
			if(arr[r] == pivot) {
				l += 1;
			}
		}
		
		// ��� l == r, ����l++, r--, ����Ϊ����ջ���
		if (l == r) {
			l += 1;
			r -= 1;
		}
		//����ݹ�
		if(left < r) {
			quickSort(arr, left, r);
		}
		//���ҵݹ�
		if(right > l) {
			quickSort(arr, l, right);
		}
		
		
	}
}
