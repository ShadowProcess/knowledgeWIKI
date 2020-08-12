package com.datastructure.search;

import java.util.ArrayList;
import java.util.List;

//ע�⣺ʹ�ö��ֲ��ҵ�ǰ���� �������������.
public class BinarySearch {

	public static void main(String[] args) {
		//int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
		

		//
//		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//		System.out.println("resIndex=" + resIndex);
		
		List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1);
		System.out.println("resIndexList=" + resIndexList);
	}

	// ���ֲ����㷨
	/**
	 * 
	 * @param arr
	 *            ����
	 * @param left
	 *            ��ߵ�����
	 * @param right
	 *            �ұߵ�����
	 * @param findVal
	 *            Ҫ���ҵ�ֵ
	 * @return ����ҵ��ͷ����±꣬���û���ҵ����ͷ��� -1
	 */
	public static int binarySearch(int[] arr, int left, int right, int findVal) {
		

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
	
	//���һ���κ�˼����:
	/*
	 * �κ�˼���⣺ {1,8, 10, 89, 1000, 1000��1234} ��һ�����������У�
	 * �ж����ͬ����ֵʱ����ν����е���ֵ�����ҵ������������ 1000
	 * 
	 * ˼·����
	 * 1. ���ҵ�mid ����ֵ����Ҫ���Ϸ���
	 * 2. ��mid ����ֵ�����ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
	 * 3. ��mid ����ֵ���ұ�ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
	 * 4. ��Arraylist����
	 */

	public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

		System.out.println("hello~");
		// �� left > right ʱ��˵���ݹ��������飬����û���ҵ�
		if (left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // �� �ҵݹ�
			return binarySearch2(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) { // ����ݹ�
			return binarySearch2(arr, left, mid - 1, findVal);
		} else {
//			 * ˼·����
//			 * 1. ���ҵ�mid ����ֵ����Ҫ���Ϸ���
//			 * 2. ��mid ����ֵ�����ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
//			 * 3. ��mid ����ֵ���ұ�ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
//			 * 4. ��Arraylist����
			
			List<Integer> resIndexlist = new ArrayList<Integer>();
			//��mid ����ֵ�����ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
			int temp = mid - 1;
			while(true) {
				if (temp < 0 || arr[temp] != findVal) {//�˳�
					break;
				}
				//���򣬾�temp ���뵽 resIndexlist
				resIndexlist.add(temp);
				temp -= 1; //temp����
			}
			resIndexlist.add(mid);  //
			
			//��mid ����ֵ���ұ�ɨ�裬���������� 1000�� ��Ԫ�ص��±꣬���뵽����ArrayList
			temp = mid + 1;
			while(true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {//�˳�
					break;
				}
				//���򣬾�temp ���뵽 resIndexlist
				resIndexlist.add(temp);
				temp += 1; //temp����
			}
			
			return resIndexlist;
		}

	}
}
