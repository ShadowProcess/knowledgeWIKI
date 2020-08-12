package com.datastructure.tree.threadedbinarytree;

import java.util.concurrent.SynchronousQueue;

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
		//����һ�����������������Ĺ���
		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "dim");
		
		//����������������Ҫ�ݹ鴴��, ���ڼ򵥴���ʹ���ֶ�����
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		//��������������
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();
		
		//����: ��10�Žڵ����
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("10�Ž���ǰ������� ="  + leftNode); //3
		System.out.println("10�Ž��ĺ�̽����="  + rightNode); //1
		
		//��������������������ʹ��ԭ���ı�������
		//threadedBinaryTree.infixOrder();
		System.out.println("ʹ���������ķ�ʽ���� ������������");
		threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
		
	}

}




//����ThreadedBinaryTree ʵ�������������ܵĶ�����
class ThreadedBinaryTree {
	private HeroNode root;
	
	//Ϊ��ʵ������������Ҫ����Ҫ��ָ��ǰ����ǰ������ָ��
	//�ڵݹ����������ʱ��pre ���Ǳ���ǰһ�����
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//����һ��threadedNodes����
	public void threadedNodes() {
		this.threadedNodes(root);
	}
	
	//�����������������ķ���
	public void threadedList() {
		//����һ���������洢��ǰ�����Ľ�㣬��root��ʼ
		HeroNode node = root;
		while(node != null) {
			//ѭ�����ҵ�leftType == 1�Ľ�㣬��һ���ҵ�����8���
			//�������ű������仯,��Ϊ��leftType==1ʱ��˵���ý���ǰ���������
			//��������Ч���
			while(node.getLeftType() == 0) {
				node = node.getLeft();
			}
			
			//��ӡ��ǰ������
			System.out.println(node);
			//�����ǰ������ָ��ָ����Ǻ�̽��,��һֱ���
			while(node.getRightType() == 1) {
				//��ȡ����ǰ���ĺ�̽��
				node = node.getRight();
				System.out.println(node);
			}
			//�滻��������Ľ��
			node = node.getRight();
			
		}
	}
	
	//��д�Զ��������������������ķ���
	/**
	 * 
	 * @param node ���ǵ�ǰ��Ҫ�������Ľ��
	 */
	public void threadedNodes(HeroNode node) {
		
		//���node==null, ����������
		if(node == null) {
			return;
		}
		
		//(һ)��������������
		threadedNodes(node.getLeft());
		//(��)��������ǰ���[���Ѷ�]
		
		//����ǰ����ǰ�����
		//��8��������
		//8����.left = null , 8����.leftType = 1
		if(node.getLeft() == null) {
			//�õ�ǰ������ָ��ָ��ǰ����� 
			node.setLeft(pre); 
			//�޸ĵ�ǰ������ָ�������,ָ��ǰ�����
			node.setLeftType(1);
		}
		
		//�����̽��
		if (pre != null && pre.getRight() == null) {
			//��ǰ��������ָ��ָ��ǰ���
			pre.setRight(node);
			//�޸�ǰ��������ָ������
			pre.setRightType(1);
		}
		//!!! ÿ����һ�������õ�ǰ�������һ������ǰ�����
		pre = node;
		
		//(��)��������������
		threadedNodes(node.getRight());
		
		
	}
	
	//ɾ�����
	public void delNode(int no) {
		if(root != null) {
			//���ֻ��һ��root���, ���������ж�root�ǲ��Ǿ���Ҫɾ�����
			if(root.getNo() == no) {
				root = null;
			} else {
				//�ݹ�ɾ��
				root.delNode(no);
			}
		}else{
			System.out.println("����������ɾ��~");
		}
	}
	//ǰ�����
	public void preOrder() {
		if(this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}
	
	//�������
	public void infixOrder() {
		if(this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}
	//�������
	public void postOrder() {
		if(this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}
	
	//ǰ�����
	public HeroNode preOrderSearch(int no) {
		if(root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}
	//�������
	public HeroNode infixOrderSearch(int no) {
		if(root != null) {
			return root.infixOrderSearch(no);
		}else {
			return null;
		}
	}
	//�������
	public HeroNode postOrderSearch(int no) {
		if(root != null) {
			return this.root.postOrderSearch(no);
		}else {
			return null;
		}
	}
}

//�ȴ���HeroNode ���
class HeroNode {
	private int no;
	private String name;
	private HeroNode left; //Ĭ��null
	private HeroNode right; //Ĭ��null
	//˵��
	//1. ���leftType == 0 ��ʾָ�����������, ��� 1 ���ʾָ��ǰ�����
	//2. ���rightType == 0 ��ʾָ����������, ��� 1��ʾָ���̽��
	private int leftType;
	private int rightType;
	
	
	
	public int getLeftType() {
		return leftType;
	}
	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}
	public int getRightType() {
		return rightType;
	}
	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroNode getLeft() {
		return left;
	}
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	public HeroNode getRight() {
		return right;
	}
	public void setRight(HeroNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	//�ݹ�ɾ�����
	//1.���ɾ���Ľڵ���Ҷ�ӽڵ㣬��ɾ���ýڵ�
	//2.���ɾ���Ľڵ��Ƿ�Ҷ�ӽڵ㣬��ɾ��������
	public void delNode(int no) {
		
		//˼·
		/*
		 * 	1. ��Ϊ���ǵĶ������ǵ���ģ������������жϵ�ǰ�����ӽ���Ƿ���Ҫɾ����㣬������ȥ�жϵ�ǰ�������ǲ�����Ҫɾ�����.
			2. �����ǰ�������ӽ�㲻Ϊ�գ��������ӽ�� ����Ҫɾ����㣬�ͽ�this.left = null; ���Ҿͷ���(�����ݹ�ɾ��)
			3. �����ǰ�������ӽ�㲻Ϊ�գ��������ӽ�� ����Ҫɾ����㣬�ͽ�this.right= null ;���Ҿͷ���(�����ݹ�ɾ��)
			4. �����2�͵�3��û��ɾ����㣬��ô���Ǿ���Ҫ�����������еݹ�ɾ��
			5.  �����4��Ҳû��ɾ����㣬��Ӧ�������������еݹ�ɾ��.

		 */
		//2. �����ǰ�������ӽ�㲻Ϊ�գ��������ӽ�� ����Ҫɾ����㣬�ͽ�this.left = null; ���Ҿͷ���(�����ݹ�ɾ��)
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		//3.�����ǰ�������ӽ�㲻Ϊ�գ��������ӽ�� ����Ҫɾ����㣬�ͽ�this.right= null ;���Ҿͷ���(�����ݹ�ɾ��)
		if(this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//4.���Ǿ���Ҫ�����������еݹ�ɾ��
		if(this.left != null) {
			this.left.delNode(no);
		}
		//5.��Ӧ�������������еݹ�ɾ��
		if(this.right != null) {
			this.right.delNode(no);
		}
	}
	
	//��дǰ������ķ���
	public void preOrder() {
		System.out.println(this); //����������
		//�ݹ���������ǰ�����
		if(this.left != null) {
			this.left.preOrder();
		}
		//�ݹ���������ǰ�����
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	//�������
	public void infixOrder() {
		
		//�ݹ����������������
		if(this.left != null) {
			this.left.infixOrder();
		}
		//��������
		System.out.println(this);
		//�ݹ����������������
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	//�������
	public void postOrder() {
		if(this.left != null) {
			this.left.postOrder();
		}
		if(this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	//ǰ���������
	/**
	 * 
	 * @param no ����no
	 * @return ����ҵ��ͷ��ظ�Node ,���û���ҵ����� null
	 */
	public HeroNode preOrderSearch(int no) {
		System.out.println("����ǰ�����");
		//�Ƚϵ�ǰ����ǲ���
		if(this.no == no) {
			return this;
		}
		//1.���жϵ�ǰ�������ӽڵ��Ƿ�Ϊ�գ������Ϊ�գ���ݹ�ǰ�����
		//2.�����ݹ�ǰ����ң��ҵ���㣬�򷵻�
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {//˵�������������ҵ�
			return resNode;
		}
		//1.��ݹ�ǰ����ң��ҵ���㣬�򷵻أ�������жϣ�
		//2.��ǰ�Ľ������ӽڵ��Ƿ�Ϊ�գ�������գ���������ҵݹ�ǰ�����
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	//�����������
	public HeroNode infixOrderSearch(int no) {
		//�жϵ�ǰ�������ӽڵ��Ƿ�Ϊ�գ������Ϊ�գ���ݹ��������
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("�����������");
		//����ҵ����򷵻أ����û���ҵ����ͺ͵�ǰ���Ƚϣ�������򷵻ص�ǰ���
		if(this.no == no) {
			return this;
		}
		//������������ҵݹ���������
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
		
	}
	
	//�����������
	public HeroNode postOrderSearch(int no) {
		
		//�жϵ�ǰ�������ӽڵ��Ƿ�Ϊ�գ������Ϊ�գ���ݹ�������
		HeroNode resNode = null;
		if(this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if(resNode != null) {//˵�����������ҵ�
			return resNode;
		}
		
		//���������û���ҵ��������������ݹ���к����������
		if(this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		System.out.println("����������");
		//�������������û���ҵ����ͱȽϵ�ǰ����ǲ���
		if(this.no == no) {
			return this;
		}
		return resNode;
	}
	
}
