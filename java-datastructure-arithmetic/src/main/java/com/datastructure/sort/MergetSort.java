package com.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergetSort {

	public static void main(String[] args) {
		//int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 }; //
		
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
		
		int temp[] = new int[arr.length]; //�鲢������Ҫһ������ռ�
 		mergeSort(arr, 0, arr.length - 1, temp);
 		
 		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("����ǰ��ʱ����=" + date2Str);
 		
 		//System.out.println("�鲢�����=" + Arrays.toString(arr));
	}
	
	
	//��+�Ϸ���
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right) / 2; //�м�����
			//����ݹ���зֽ�
			mergeSort(arr, left, mid, temp);
			//���ҵݹ���зֽ�
			mergeSort(arr, mid + 1, right, temp);
			//�ϲ�
			merge(arr, left, mid, right, temp);
			
		}
	}
	
	//�ϲ��ķ���
	/**
	 * 
	 * @param arr �����ԭʼ����
	 * @param left ����������еĳ�ʼ����
	 * @param mid �м�����
	 * @param right �ұ�����
	 * @param temp ����ת������
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		
		int i = left; // ��ʼ��i, ����������еĳ�ʼ����
		int j = mid + 1; //��ʼ��j, �ұ��������еĳ�ʼ����
		int t = 0; // ָ��temp����ĵ�ǰ����
		
		//(һ)
		//�Ȱ���������(����)�����ݰ��չ�����䵽temp����
		//ֱ���������ߵ��������У���һ�ߴ������Ϊֹ
		while (i <= mid && j <= right) {//����
			//�����ߵ��������еĵ�ǰԪ�أ�С�ڵ����ұ��������еĵ�ǰԪ��
			//������ߵĵ�ǰԪ�أ���䵽 temp���� 
			//Ȼ�� t++, i++
			if(arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else { //��֮,���ұ��������еĵ�ǰԪ�أ���䵽temp����
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}
		
		//(��)
		//����ʣ�����ݵ�һ�ߵ���������ȫ����䵽temp
		while( i <= mid) { //��ߵ��������л���ʣ���Ԫ�أ���ȫ����䵽temp
			temp[t] = arr[i];
			t += 1;
			i += 1;	
		}
		
		while( j <= right) { //�ұߵ��������л���ʣ���Ԫ�أ���ȫ����䵽temp
			temp[t] = arr[j];
			t += 1;
			j += 1;	
		}
		
		
		//(��)
		//��temp�����Ԫ�ؿ�����arr
		//ע�⣬������ÿ�ζ���������
		t = 0;
		int tempLeft = left; // 
		//��һ�κϲ� tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
		//���һ�� tempLeft = 0  right = 7
		while(tempLeft <= right) { 
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
		
	}

}
