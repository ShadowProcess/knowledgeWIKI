package com.arithmetic.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

	public static void main(String[] args) {
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		//�ڽӾ���
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;// ��ʾ����������
		matrix[0]=new int[]{N,5,7,N,N,N,2};  
        matrix[1]=new int[]{5,N,N,9,N,N,3};  
        matrix[2]=new int[]{7,N,N,N,8,N,N};  
        matrix[3]=new int[]{N,9,N,N,N,4,N};  
        matrix[4]=new int[]{N,N,8,N,N,5,4};  
        matrix[5]=new int[]{N,N,N,4,5,N,6};  
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //���� Graph����
        Graph graph = new Graph(vertex, matrix);
        //����, ����ͼ���ڽӾ����Ƿ�ok
        graph.showGraph();
        //���ԵϽ�˹�����㷨
        graph.dsj(2);//C
        graph.showDijkstra();
        
        
	}

}

class Graph {
	private char[] vertex; // ��������
	private int[][] matrix; // �ڽӾ���
	private VisitedVertex vv; //�Ѿ����ʵĶ���ļ���

	// ������
	public Graph(char[] vertex, int[][] matrix) {
		this.vertex = vertex;
		this.matrix = matrix;
	}
	
	//��ʾ���
	public void showDijkstra() {
		vv.show();
	}

	// ��ʾͼ
	public void showGraph() {
		for (int[] link : matrix) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	//�Ͻ�˹�����㷨ʵ��
	/**
	 * 
	 * @param index ��ʾ���������Ӧ���±�
	 */
	public void dsj(int index) {
		vv = new VisitedVertex(vertex.length, index);
		update(index);//����index���㵽��Χ����ľ����ǰ������
		for(int j = 1; j <vertex.length; j++) {
			index = vv.updateArr();// ѡ�񲢷����µķ��ʶ���
			update(index); // ����index���㵽��Χ����ľ����ǰ������
		} 
	}
	
	
	
	//����index�±궥�㵽��Χ����ľ������Χ�����ǰ������,
	private void update(int index) {
		int len = 0;
		//���ݱ������ǵ��ڽӾ����  matrix[index]��
		for(int j = 0; j < matrix[index].length; j++) {
			// len ������ : �������㵽index����ľ��� + ��index���㵽j����ľ���ĺ� 
			len = vv.getDis(index) + matrix[index][j];
			// ���j����û�б����ʹ������� len С�ڳ������㵽j����ľ��룬����Ҫ����
			if(!vv.in(j) && len < vv.getDis(j)) {
				vv.updatePre(j, index); //����j�����ǰ��Ϊindex����
				vv.updateDis(j, len); //���³������㵽j����ľ���
			}
		}
	}
}

// �ѷ��ʶ��㼯��
class VisitedVertex {
	// ��¼���������Ƿ���ʹ� 1��ʾ���ʹ�,0δ����,�ᶯ̬����
	public int[] already_arr;
	// ÿ���±��Ӧ��ֵΪǰһ�������±�, �ᶯ̬����
	public int[] pre_visited;
	// ��¼�������㵽�������ж���ľ���,����GΪ�������㣬�ͻ��¼G����������ľ��룬�ᶯ̬���£������̾���ͻ��ŵ�dis
	public int[] dis;
	
	//������
	/**
	 * 
	 * @param length :��ʾ����ĸ��� 
	 * @param index: ���������Ӧ���±�, ����G���㣬�±����6
	 */
	public VisitedVertex(int length, int index) {
		this.already_arr = new int[length];
		this.pre_visited = new int[length];
		this.dis = new int[length];
		//��ʼ�� dis����
		Arrays.fill(dis, 65535);
		this.already_arr[index] = 1; //���ó������㱻���ʹ�
		this.dis[index] = 0;//���ó�������ķ��ʾ���Ϊ0
				
	}
	/**
	 * ����: �ж�index�����Ƿ񱻷��ʹ�
	 * @param index
	 * @return ������ʹ����ͷ���true, �������false
	 */
	public boolean in(int index) {
		return already_arr[index] == 1;
	}
	
	/**
	 * ����: ���³������㵽index����ľ���
	 * @param index
	 * @param len
	 */
	public void updateDis(int index, int len) {
		dis[index] = len;
	}
	/**
	 * ����: ����pre��������ǰ������Ϊindex����
	 * @param pre
	 * @param index
	 */
	public void updatePre(int pre, int index) {
		pre_visited[pre] = index;
	}
	/**
	 * ����:���س������㵽index����ľ���
	 * @param index
	 */
	public int getDis(int index) {
		return dis[index];
	}
	
	
	/**
	 * ����ѡ�񲢷����µķ��ʶ��㣬 ���������G ��󣬾��� A����Ϊ�µķ��ʶ���(ע�ⲻ�ǳ�������)
	 * @return
	 */
	public int updateArr() {
		int min = 65535, index = 0;
		for(int i = 0; i < already_arr.length; i++) {
			if(already_arr[i] == 0 && dis[i] < min ) {
				min = dis[i];
				index = i;
			}
		}
		//���� index ���㱻���ʹ�
		already_arr[index] = 1;
		return index;
	}
	
	//��ʾ���Ľ��
	//�������������������
	public void show() {
		
		System.out.println("==========================");
		//���already_arr
		for(int i : already_arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		//���pre_visited
		for(int i : pre_visited) {
			System.out.print(i + " ");
		}
		System.out.println();
		//���dis
		for(int i : dis) {
			System.out.print(i + " ");
		}
		System.out.println();
		//Ϊ�˺ÿ�������̾��룬���Ǵ���
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int count = 0;
		for (int i : dis) {
			if (i != 65535) {
				System.out.print(vertex[count] + "("+i+") ");
			} else {
				System.out.println("N ");
			}
			count++;
		}
		System.out.println();
		
	}

}

