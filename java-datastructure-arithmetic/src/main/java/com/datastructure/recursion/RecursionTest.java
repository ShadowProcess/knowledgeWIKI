package com.datastructure.recursion;

public class RecursionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ͨ����ӡ���⣬�ع˵ݹ���û���
		test(4);
		
		//int res = factorial(3);
		//System.out.println("res=" + res);
	}
	//��ӡ����. 
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		} //else {
			System.out.println("n=" + n);
		// }
	}
	//�׳�����
	public static int factorial(int n) {
		if (n == 1) { 
			return 1;
		} else {
			return factorial(n - 1) * n; // 1 * 2 * 3
		}
	}


}
