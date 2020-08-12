package com.datastructure.avl;



public class AVLTreeDemo {

	public static void main(String[] args) {
		//int[] arr = {4,3,6,5,7,8};
		//int[] arr = { 10, 12, 8, 9, 7, 6 };
		int[] arr = { 10, 11, 7, 6, 8, 9 };  
		//����һ�� AVLTree����
		AVLTree avlTree = new AVLTree();
		//��ӽ��
		for(int i=0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		
		//����
		System.out.println("�������");
		avlTree.infixOrder();
		
		System.out.println("��ƽ�⴦��~~");
		System.out.println("���ĸ߶�=" + avlTree.getRoot().height()); //3
		System.out.println("�����������߶�=" + avlTree.getRoot().leftHeight()); // 2
		System.out.println("�����������߶�=" + avlTree.getRoot().rightHeight()); // 2
		System.out.println("��ǰ�ĸ����=" + avlTree.getRoot());//8
		
		
	}

}

// ����AVLTree
class AVLTree {
	private Node root;

	public Node getRoot() {
		return root;
	}

	// ����Ҫɾ���Ľ��
	public Node search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	// ���Ҹ����
	public Node searchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	// ��д����:
	// 1. ���ص� ��node Ϊ�����Ķ�������������С����ֵ
	// 2. ɾ��node Ϊ�����Ķ�������������С���
	/**
	 * 
	 * @param node
	 *            ����Ľ��(���������������ĸ����)
	 * @return ���ص� ��node Ϊ�����Ķ�������������С����ֵ
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		// ѭ���Ĳ������ӽڵ㣬�ͻ��ҵ���Сֵ
		while (target.left != null) {
			target = target.left;
		}
		// ��ʱ target��ָ������С���
		// ɾ����С���
		delNode(target.value);
		return target.value;
	}

	// ɾ�����
	public void delNode(int value) {
		if (root == null) {
			return;
		} else {
			// 1.������ȥ�ҵ�Ҫɾ���Ľ�� targetNode
			Node targetNode = search(value);
			// ���û���ҵ�Ҫɾ���Ľ��
			if (targetNode == null) {
				return;
			}
			// ������Ƿ��ֵ�ǰ��Ŷ���������ֻ��һ�����
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}

			// ȥ�ҵ�targetNode�ĸ����
			Node parent = searchParent(value);
			// ���Ҫɾ���Ľ����Ҷ�ӽ��
			if (targetNode.left == null && targetNode.right == null) {
				// �ж�targetNode �Ǹ��������ӽ�㣬�������ӽ��
				if (parent.left != null && parent.left.value == value) { // �����ӽ��
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {// �����ӽ��
					parent.right = null;
				}
			} else if (targetNode.left != null && targetNode.right != null) { // ɾ�������������Ľڵ�
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;

			} else { // ɾ��ֻ��һ�������Ľ��
				// ���Ҫɾ���Ľ�������ӽ��
				if (targetNode.left != null) {
					if (parent != null) {
						// ��� targetNode �� parent �����ӽ��
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else { // targetNode �� parent �����ӽ��
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else { // ���Ҫɾ���Ľ�������ӽ��
					if (parent != null) {
						// ��� targetNode �� parent �����ӽ��
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else { // ��� targetNode �� parent �����ӽ��
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}

			}

		}
	}

	// ��ӽ��ķ���
	public void add(Node node) {
		if (root == null) {
			root = node;// ���rootΪ����ֱ����rootָ��node
		} else {
			root.add(node);
		}
	}

	// �������
	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("����������Ϊ�գ����ܱ���");
		}
	}
}

// ����Node���
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {

		this.value = value;
	}

	// �����������ĸ߶�
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// �����������ĸ߶�
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// ���� �Ըý��Ϊ���������ĸ߶�
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}
	
	//����ת����
	private void leftRotate() {
		
		//�����µĽ�㣬�Ե�ǰ������ֵ
		Node newNode = new Node(value);
		//���µĽ������������óɵ�ǰ����������
		newNode.left = left;
		//���µĽ������������óɴ����ȥ������������������
		newNode.right = right.left;
		//�ѵ�ǰ����ֵ�滻�����ӽ���ֵ
		value = right.value;
		//�ѵ�ǰ�������������óɵ�ǰ�����������������
		right = right.right;
		//�ѵ�ǰ����������(���ӽ��)���ó��µĽ��
		left = newNode;
		
		
	}
	
	//����ת
	private void rightRotate() {
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}

	// ����Ҫɾ���Ľ��
	/**
	 * 
	 * @param value
	 *            ϣ��ɾ���Ľ���ֵ
	 * @return ����ҵ����ظý�㣬���򷵻�null
	 */
	public Node search(int value) {
		if (value == this.value) { // �ҵ����Ǹý��
			return this;
		} else if (value < this.value) {// ������ҵ�ֵС�ڵ�ǰ��㣬���������ݹ����
			// ������ӽ��Ϊ��
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		} else { // ������ҵ�ֵ��С�ڵ�ǰ��㣬���������ݹ����
			if (this.right == null) {
				return null;
			}
			return this.right.search(value);
		}

	}

	// ����Ҫɾ�����ĸ����
	/**
	 * 
	 * @param value
	 *            Ҫ�ҵ��Ľ���ֵ
	 * @return ���ص���Ҫɾ���Ľ��ĸ���㣬���û�оͷ���null
	 */
	public Node searchParent(int value) {
		// �����ǰ������Ҫɾ���Ľ��ĸ���㣬�ͷ���
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// ������ҵ�ֵС�ڵ�ǰ����ֵ, ���ҵ�ǰ�������ӽ�㲻Ϊ��
			if (value < this.value && this.left != null) {
				return this.left.searchParent(value); // ���������ݹ����
			} else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value); // ���������ݹ����
			} else {
				return null; // û���ҵ������
			}
		}

	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// ��ӽ��ķ���
	// �ݹ����ʽ��ӽ�㣬ע����Ҫ���������������Ҫ��
	public void add(Node node) {
		if (node == null) {
			return;
		}

		// �жϴ���Ľ���ֵ���͵�ǰ�����ĸ�����ֵ��ϵ
		if (node.value < this.value) {
			// �����ǰ������ӽ��Ϊnull
			if (this.left == null) {
				this.left = node;
			} else {
				// �ݹ�������������
				this.left.add(node);
			}
		} else { // ��ӵĽ���ֵ���� ��ǰ����ֵ
			if (this.right == null) {
				this.right = node;
			} else {
				// �ݹ�������������
				this.right.add(node);
			}

		}
		
		//�������һ���������: (�������ĸ߶�-�������ĸ߶�) > 1 , ����ת
		if(rightHeight() - leftHeight() > 1) {
			//����������������������ĸ߶ȴ����������������������ĸ߶�
			if(right != null && right.leftHeight() > right.rightHeight()) {
				//�ȶ����ӽ���������ת
				right.rightRotate();
				//Ȼ���ڶԵ�ǰ����������ת
				leftRotate(); //����ת..
			} else {
				//ֱ�ӽ�������ת����
				leftRotate();
			}
			return ; //����Ҫ!!!
		}
		
		//�������һ��������� (�������ĸ߶� - �������ĸ߶�) > 1, ����ת
		if(leftHeight() - rightHeight() > 1) {
			//����������������������߶ȴ��������������ĸ߶�
			if(left != null && left.rightHeight() > left.leftHeight()) {
				//�ȶԵ�ǰ��������(������)->����ת
				left.leftRotate();
				//�ٶԵ�ǰ����������ת
				rightRotate();
			} else {
				//ֱ�ӽ�������ת����
				rightRotate();
			}
		}
	}

	// �������
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

}
