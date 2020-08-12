package com.datastructure.search;

import java.util.Arrays;

public class InsertValueSearch {

	public static void main(String[] args) {
		
//		int [] arr = new int[100];
//		for(int i = 0; i < 100; i++) {
//			arr[i] = i + 1;
//		}
		
		int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
		
		int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
		//int index = binarySearch(arr, 0, arr.length, 1);
		System.out.println("index = " + index);
		
		//System.out.println(Arrays.toString(arr));
	}
	
	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		System.out.println("���ֲ��ұ�����~");
		// �� left > right ʱ��˵���ݹ��������飬����û���ҵ�
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // �� �ҵݹ�
			return binarySearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // ����ݹ�
			return binarySearch(arr, left, mid - 1, findVal);
		} else {

			return mid;
		}

	}

	//��д��ֵ�����㷨
	//˵������ֵ�����㷨��ҲҪ�������������
	/**
	 * 
	 * @param arr ����
	 * @param left �������
	 * @param right �ұ�����
	 * @param findVal ����ֵ
	 * @return ����ҵ����ͷ��ض�Ӧ���±꣬���û���ҵ�������-1
	 */
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) { 

		System.out.println("��ֵ���Ҵ���~~");
		
		//ע�⣺findVal < arr[0]  ��  findVal > arr[arr.length - 1] ������Ҫ
		//�������ǵõ��� mid ����Խ��
		if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}

		// ���mid, ����Ӧ
		int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
		int midVal = arr[mid];
		if (findVal > midVal) { // ˵��Ӧ�����ұߵݹ�
			return insertValueSearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // ˵������ݹ����
			return insertValueSearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}

	}
}
