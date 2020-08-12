package com.arithmetic.horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {

	private static int X; // ���̵�����
	private static int Y; // ���̵�����
	//����һ�����飬������̵ĸ���λ���Ƿ񱻷��ʹ�
	private static boolean visited[];
	//ʹ��һ�����ԣ�����Ƿ����̵�����λ�ö�������
	private static boolean finished; // ���Ϊtrue,��ʾ�ɹ�
	
	public static void main(String[] args) {
		System.out.println("��ʿ�����㷨����ʼ����~~");
		//������ʿ�����㷨�Ƿ���ȷ
		X = 8;
		Y = 8;
		int row = 1; //�����ʼλ�õ��У���1��ʼ���
		int column = 1; //�����ʼλ�õ��У���1��ʼ���
		//��������
		int[][] chessboard = new int[X][Y];
		visited = new boolean[X * Y];//��ʼֵ����false
		//����һ�º�ʱ
		long start = System.currentTimeMillis();
		traversalChessboard(chessboard, row - 1, column - 1, 1);
		long end = System.currentTimeMillis();
		System.out.println("����ʱ: " + (end - start) + " ����");
		
		//������̵�������
		for(int[] rows : chessboard) {
			for(int step: rows) {
				System.out.print(step + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * �����ʿ����������㷨
	 * @param chessboard ����
	 * @param row �����ǰ��λ�õ��� ��0��ʼ 
	 * @param column �����ǰ��λ�õ���  ��0��ʼ
	 * @param step �ǵڼ��� ,��ʼλ�þ��ǵ�1�� 
	 */
	public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
		chessboard[row][column] = step;
		//row = 4 X = 8 column = 4 = 4 * 8 + 4 = 36
		visited[row * X + column] = true; //��Ǹ�λ���Ѿ�����
		//��ȡ��ǰλ�ÿ����ߵ���һ��λ�õļ��� 
		ArrayList<Point> ps = next(new Point(column, row));
		//��ps��������,����Ĺ�����Ƕ�ps�����е�Point�������һ����λ�õ���Ŀ�����зǵݼ�����
		sort(ps);
		//���� ps
		while(!ps.isEmpty()) {
			Point p = ps.remove(0);//ȡ����һ�������ߵ�λ��
			//�жϸõ��Ƿ��Ѿ����ʹ�
			if(!visited[p.y * X + p.x]) {//˵����û�з��ʹ�
				traversalChessboard(chessboard, p.y, p.x, step + 1);
			}
		}
		//�ж�����Ƿ����������ʹ��   step ��Ӧ���ߵĲ����Ƚ� �� 
		//���û�дﵽ���������ʾû��������񣬽�����������0
		//˵��: step < X * Y  ���������������
		//1. ���̵�Ŀǰλ��,��Ȼû������
		//2. ���̴���һ�����ݹ���
		if(step < X * Y && !finished ) {
			chessboard[row][column] = 0;
			visited[row * X + column] = false;
		} else {
			finished = true;
		}
		
	}
	
	/**
	 * ���ܣ� ���ݵ�ǰλ��(Point����)�����������������Щλ��(Point)�������뵽һ��������(ArrayList), �����8��λ��
	 * @param curPoint
	 * @return
	 */
	public static ArrayList<Point> next(Point curPoint) {
		//����һ��ArrayList
		ArrayList<Point> ps = new ArrayList<Point>();
		//����һ��Point
		Point p1 = new Point();
		//��ʾ���������5���λ��
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
			ps.add(new Point(p1));
		}
		//�ж����������6���λ��
		if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
			ps.add(new Point(p1));
		}
		//�ж����������7���λ��
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		//�ж����������0���λ��
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		//�ж����������1���λ��
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		//�ж����������2���λ��
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		//�ж����������3���λ��
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		//�ж����������4���λ��
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}

	//���ݵ�ǰ���һ�������е���һ����ѡ��λ�ã����зǵݼ�����, ���ٻ��ݵĴ���
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				//��ȡ��o1����һ��������λ�ø���
				int count1 = next(o1).size();
				//��ȡ��o2����һ��������λ�ø���
				int count2 = next(o2).size();
				if(count1 < count2) {
					return -1;
				} else if (count1 == count2) {
					return 0;
				} else {
					return 1;
				}
			}
			
		});
	}
}
