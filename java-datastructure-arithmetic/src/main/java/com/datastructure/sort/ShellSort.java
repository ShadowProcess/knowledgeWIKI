package com.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {
		//int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		
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
		
		//shellSort(arr); //����ʽ
		shellSort2(arr);//��λ��ʽ
		
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("����ǰ��ʱ����=" + date2Str);
		
		//System.out.println(Arrays.toString(arr));
	}

	// ʹ�����Ƶ��ķ�ʽ����дϣ������
	// ϣ������ʱ�� �����������ڲ���ʱ���ý�����, 
	// ˼·(�㷨) ===> ����
	public static void shellSort(int[] arr) {
		
		int temp = 0;
		int count = 0;
		// ����ǰ����𲽷�����ʹ��ѭ������
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				// �������������е�Ԫ��(��gap�飬ÿ���и�Ԫ��), ����gap
				for (int j = i - gap; j >= 0; j -= gap) {
					// �����ǰԪ�ش��ڼ��ϲ�������Ǹ�Ԫ�أ�˵������
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
			//System.out.println("ϣ�������" + (++count) + "�� =" + Arrays.toString(arr));
		}
		
		/*
		
		// ϣ������ĵ�1������
		// ��Ϊ��1�������ǽ�10�����ݷֳ��� 5��
		for (int i = 5; i < arr.length; i++) {
			// �������������е�Ԫ��(��5�飬ÿ����2��Ԫ��), ����5
			for (int j = i - 5; j >= 0; j -= 5) {
				// �����ǰԪ�ش��ڼ��ϲ�������Ǹ�Ԫ�أ�˵������
				if (arr[j] > arr[j + 5]) {
					temp = arr[j];
					arr[j] = arr[j + 5];
					arr[j + 5] = temp;
				}
			}
		}
		
		System.out.println("ϣ������1�ֺ�=" + Arrays.toString(arr));//
		
		
		// ϣ������ĵ�2������
		// ��Ϊ��2�������ǽ�10�����ݷֳ��� 5/2 = 2��
		for (int i = 2; i < arr.length; i++) {
			// �������������е�Ԫ��(��5�飬ÿ����2��Ԫ��), ����5
			for (int j = i - 2; j >= 0; j -= 2) {
				// �����ǰԪ�ش��ڼ��ϲ�������Ǹ�Ԫ�أ�˵������
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
				}
			}
		}

		System.out.println("ϣ������2�ֺ�=" + Arrays.toString(arr));//

		// ϣ������ĵ�3������
		// ��Ϊ��3�������ǽ�10�����ݷֳ��� 2/2 = 1��
		for (int i = 1; i < arr.length; i++) {
			// �������������е�Ԫ��(��5�飬ÿ����2��Ԫ��), ����5
			for (int j = i - 1; j >= 0; j -= 1) {
				// �����ǰԪ�ش��ڼ��ϲ�������Ǹ�Ԫ�أ�˵������
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		System.out.println("ϣ������3�ֺ�=" + Arrays.toString(arr));//
		*/
	}
	
	//�Խ���ʽ��ϣ����������Ż�->��λ��
	public static void shellSort2(int[] arr) {
		
		// ����gap, ���𲽵���С����
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// �ӵ�gap��Ԫ�أ�����������ڵ������ֱ�Ӳ�������
			for (int i = gap; i < arr.length; i++) {
				int j = i;
				int temp = arr[j];
				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						//�ƶ�
						arr[j] = arr[j-gap];
						j -= gap;
					}
					//���˳�while�󣬾͸�temp�ҵ������λ��
					arr[j] = temp;
				}

			}
		}
	}

}
