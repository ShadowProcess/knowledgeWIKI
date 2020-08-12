package com.arithmetic.kruskal;

import java.util.Arrays;

public class KruskalCase {

	private int edgeNum; //�ߵĸ���
	private char[] vertexs; //��������
	private int[][] matrix; //�ڽӾ���
	//ʹ�� INF ��ʾ�������㲻����ͨ
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		//��³˹�����㷨���ڽӾ���  
	      int matrix[][] = {
	      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
	/*A*/ {   0,  12, INF, INF, INF,  16,  14},
	/*B*/ {  12,   0,  10, INF, INF,   7, INF},
	/*C*/ { INF,  10,   0,   3,   5,   6, INF},
	/*D*/ { INF, INF,   3,   0,   4, INF, INF},
	/*E*/ { INF, INF,   5,   4,   0,   2,   8},
	/*F*/ {  16,   7,   6, INF,   2,   0,   9},
	/*G*/ {  14, INF, INF, INF,   8,   9,   0}}; 
	      //��ҿ�����ȥ�����������ڽӾ��󣬽�������Եõ���С������.
	      
	      //����KruskalCase ����ʵ��
	      KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
	      //���������
	      kruskalCase.print();
	      kruskalCase.kruskal();
	      
	}
	
	//������
	public KruskalCase(char[] vertexs, int[][] matrix) {
		//��ʼ���������ͱߵĸ���
		int vlen = vertexs.length;
		
		//��ʼ������, ���ƿ����ķ�ʽ
		this.vertexs = new char[vlen];
		for(int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}
		
		//��ʼ����, ʹ�õ��Ǹ��ƿ����ķ�ʽ
		this.matrix = new int[vlen][vlen];
		for(int i = 0; i < vlen; i++) {
			for(int j= 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
		//ͳ�Ʊߵ�����
		for(int i =0; i < vlen; i++) {
			for(int j = i+1; j < vlen; j++) {
				if(this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
		
	}
	public void kruskal() {
		int index = 0; //��ʾ��������������
		int[] ends = new int[edgeNum]; //���ڱ���"������С������" �е�ÿ����������С�������е��յ�
		//�����������, ����������С������
		EData[] rets = new EData[edgeNum];
		
		//��ȡͼ�� ���еıߵļ��� �� һ����12��
		EData[] edges = getEdges();
		System.out.println("ͼ�ıߵļ���=" + Arrays.toString(edges) + " ��"+ edges.length); //12
		
		//���ձߵ�Ȩֵ��С��������(��С����)
		sortEdges(edges);
		
		//����edges ���飬������ӵ���С��������ʱ���ж���׼������ı߷��γ��˻�·�����û�У��ͼ��� rets, �����ܼ���
		for(int i=0; i < edgeNum; i++) {
			//��ȡ����i���ߵĵ�һ������(���)
			int p1 = getPosition(edges[i].start); //p1=4
			//��ȡ����i���ߵĵ�2������
			int p2 = getPosition(edges[i].end); //p2 = 5
			
			//��ȡp1���������������С�������е��յ�
			int m = getEnd(ends, p1); //m = 4
			//��ȡp2���������������С�������е��յ�
			int n = getEnd(ends, p2); // n = 5
			//�Ƿ񹹳ɻ�·
			if(m != n) { //û�й��ɻ�·
				ends[m] = n; // ����m ��"������С������"�е��յ� <E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
				rets[index++] = edges[i]; //��һ���߼��뵽rets����
			}
		}
		//<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>��
		//ͳ�Ʋ���ӡ "��С������", ���  rets
		System.out.println("��С������Ϊ");
		for(int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
		
		
	}
	
	//��ӡ�ڽӾ���
	public void print() {
		System.out.println("�ڽӾ���Ϊ: \n");
		for(int i = 0; i < vertexs.length; i++) {
			for(int j=0; j < vertexs.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println();//����
		}
	}

	/**
	 * ���ܣ��Ա߽���������, ð������
	 * @param edges �ߵļ���
	 */
	private void sortEdges(EData[] edges) {
		for(int i = 0; i < edges.length - 1; i++) {
			for(int j = 0; j < edges.length - 1 - i; j++) {
				if(edges[j].weight > edges[j+1].weight) {//����
					EData tmp = edges[j];
					edges[j] = edges[j+1];
					edges[j+1] = tmp;
				}
			}
 		}
	}
	/**
	 * 
	 * @param ch �����ֵ������'A','B'
	 * @return ����ch�����Ӧ���±꣬����Ҳ���������-1
	 */
	private int getPosition(char ch) {
		for(int i = 0; i < vertexs.length; i++) {
			if(vertexs[i] == ch) {//�ҵ�
				return i;
			}
		}
		//�Ҳ���,����-1
		return -1;
	}
	/**
	 * ����: ��ȡͼ�бߣ��ŵ�EData[] �����У�����������Ҫ����������
	 * ��ͨ��matrix �ڽӾ�������ȡ
	 * EData[] ��ʽ [['A','B', 12], ['B','F',7], .....]
	 * @return
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for(int i = 0; i < vertexs.length; i++) {
			for(int j=i+1; j <vertexs.length; j++) {
				if(matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}
	/**
	 * ����: ��ȡ�±�Ϊi�Ķ�����յ�(), ���ں����ж�����������յ��Ƿ���ͬ
	 * @param ends �� ������Ǽ�¼�˸��������Ӧ���յ����ĸ�,ends �������ڱ��������У����γ�
	 * @param i : ��ʾ����Ķ����Ӧ���±�
	 * @return ���صľ��� �±�Ϊi����������Ӧ���յ���±�, һ���ͷ���������
	 */
	private int getEnd(int[] ends, int i) { // i = 4 [0,0,0,0,5,0,0,0,0,0,0,0]
		while(ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}
 
}

//����һ����EData �����Ķ���ʵ���ͱ�ʾһ����
class EData {
	char start; //�ߵ�һ����
	char end; //�ߵ�����һ����
	int weight; //�ߵ�Ȩֵ
	//������
	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	//��дtoString, �����������Ϣ
	@Override
	public String toString() {
		return "EData [<" + start + ", " + end + ">= " + weight + "]";
	}
	
	
}
