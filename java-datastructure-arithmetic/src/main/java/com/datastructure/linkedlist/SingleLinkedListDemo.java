package com.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//���в���
		//�ȴ����ڵ�
		HeroNode hero1 = new HeroNode(1, "�ν�", "��ʱ��");
		HeroNode hero2 = new HeroNode(2, "¬����", "������");
		HeroNode hero3 = new HeroNode(3, "����", "�Ƕ���");
		HeroNode hero4 = new HeroNode(4, "�ֳ�", "����ͷ");
		
		//����Ҫ������
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		
		
		//����
		singleLinkedList.add(hero1);
		singleLinkedList.add(hero4);
		singleLinkedList.add(hero2);
		singleLinkedList.add(hero3);

		// ����һ�µ�����ķ�ת����
		System.out.println("ԭ����������~~");
		singleLinkedList.list();
		
//		System.out.println("��ת������~~");
//		reversetList(singleLinkedList.getHead());
//		singleLinkedList.list();
		
		System.out.println("���������ӡ������, û�иı�����Ľṹ~~");
		reversePrint(singleLinkedList.getHead());
		
/*		
		//���밴�ձ�ŵ�˳��
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		
		//��ʾһ��
		singleLinkedList.list();
		
		//�����޸Ľڵ�Ĵ���
		HeroNode newHeroNode = new HeroNode(2, "С¬", "������~~");
		singleLinkedList.update(newHeroNode);
		
		System.out.println("�޸ĺ���������~~");
		singleLinkedList.list();
		
		//ɾ��һ���ڵ�
		singleLinkedList.del(1);
		singleLinkedList.del(4);
		System.out.println("ɾ������������~~");
		singleLinkedList.list();
		
		//����һ�� ����������Ч�ڵ�ĸ���
		System.out.println("��Ч�Ľڵ����=" + getLength(singleLinkedList.getHead()));//2
		
		//����һ�¿����Ƿ�õ��˵�����K���ڵ�
		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
		System.out.println("res=" + res);
*/		
		
	}
	
	//��ʽ2��
	//��������ջ������ݽṹ���������ڵ�ѹ�뵽ջ�У�Ȼ������ջ���Ƚ�������ص㣬��ʵ���������ӡ��Ч��
	public static void reversePrint(HeroNode head) {
		if(head.next == null) {
			return;//���������ܴ�ӡ
		}
		//����Ҫ��һ��ջ���������ڵ�ѹ��ջ
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		//����������нڵ�ѹ��ջ
		while(cur != null) {
			stack.push(cur);
			cur = cur.next; //cur���ƣ������Ϳ���ѹ����һ���ڵ�
		}
		//��ջ�еĽڵ���д�ӡ,pop ��ջ
		while (stack.size() > 0) {
			System.out.println(stack.pop()); //stack���ص����Ƚ����
		}
	}
	
	//��������ת
	public static void reversetList(HeroNode head) {
		//�����ǰ����Ϊ�գ�����ֻ��һ���ڵ㣬���跴ת��ֱ�ӷ���
		if(head.next == null || head.next.next == null) {
			return ;
		}
		
		//����һ��������ָ��(����)���������Ǳ���ԭ��������
		HeroNode cur = head.next;
		HeroNode next = null;// ָ��ǰ�ڵ�[cur]����һ���ڵ�
		HeroNode reverseHead = new HeroNode(0, "", "");
		//����ԭ��������ÿ����һ���ڵ㣬�ͽ���ȡ�����������µ�����reverseHead ����ǰ��
		//���Խ�
		while(cur != null) { 
			next = cur.next;//����ʱ���浱ǰ�ڵ����һ���ڵ㣬��Ϊ������Ҫʹ��
			cur.next = reverseHead.next;//��cur����һ���ڵ�ָ���µ��������ǰ��
			reverseHead.next = cur; //��cur ���ӵ��µ�������
			cur = next;//��cur����
		}
		//��head.next ָ�� reverseHead.next , ʵ�ֵ�����ķ�ת
		head.next = reverseHead.next;
	}
	
	//���ҵ������еĵ�����k����� �����������⡿
	//˼·
	//1. ��дһ������������head�ڵ㣬ͬʱ����һ��index 
	//2. index ��ʾ�ǵ�����index���ڵ�
	//3. �Ȱ������ͷ��β�������õ�������ܵĳ��� getLength
	//4. �õ�size �����Ǵ�����ĵ�һ����ʼ���� (size-index)�����Ϳ��Եõ�
	//5. ����ҵ��ˣ��򷵻ظýڵ㣬���򷵻�nulll
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		//�ж��������Ϊ�գ�����null
		if(head.next == null) {
			return null;//û���ҵ�
		}
		//��һ�������õ�����ĳ���(�ڵ����)
		int size = getLength(head);
		//�ڶ��α���  size-index λ�ã��������ǵ����ĵ�K���ڵ�
		//����һ��index��У��
		if(index <=0 || index > size) {
			return null; 
		}
		//��������������� for ѭ����λ��������index
		HeroNode cur = head.next; //3 // 3 - 1 = 2
		for(int i =0; i< size - index; i++) {
			cur = cur.next;
		}
		return cur;
		
	}
	
	//��������ȡ��������Ľڵ�ĸ���(����Ǵ�ͷ������������ͳ��ͷ�ڵ�)
	/**
	 * 
	 * @param head �����ͷ�ڵ�
	 * @return ���صľ�����Ч�ڵ�ĸ���
	 */
	public static int getLength(HeroNode head) {
		if(head.next == null) { //������
			return 0;
		}
		int length = 0;
		//����һ�������ı���, ��������û��ͳ��ͷ�ڵ�
		HeroNode cur = head.next;
		while(cur != null) {
			length++;
			cur = cur.next; //����
		}
		return length;
	}

}


//����SingleLinkedList �������ǵ�Ӣ��
class SingleLinkedList {
	//�ȳ�ʼ��һ��ͷ�ڵ�, ͷ�ڵ㲻Ҫ��, ����ž��������
	private HeroNode head = new HeroNode(0, "", "");
	
	
	//����ͷ�ڵ�
	public HeroNode getHead() {
		return head;
	}

	//��ӽڵ㵽��������
	//˼·���������Ǳ��˳��ʱ
	//1. �ҵ���ǰ��������ڵ�
	//2. ���������ڵ��next ָ�� �µĽڵ�
	public void add(HeroNode heroNode) {
		
		//��Ϊhead�ڵ㲻�ܶ������������Ҫһ���������� temp
		HeroNode temp = head;
		//���������ҵ����
		while(true) {
			//�ҵ���������
			if(temp.next == null) {//
				break;
			}
			//���û���ҵ����, ����temp����
			temp = temp.next;
		}
		//���˳�whileѭ��ʱ��temp��ָ������������
		//���������ڵ��next ָ�� �µĽڵ�
		temp.next = heroNode;
	}
	
	//�ڶ��ַ�ʽ�����Ӣ��ʱ������������Ӣ�۲��뵽ָ��λ��
	//(�������������������ʧ�ܣ���������ʾ)
	public void addByOrder(HeroNode heroNode) {
		//��Ϊͷ�ڵ㲻�ܶ������������Ȼͨ��һ������ָ��(����)�������ҵ���ӵ�λ��
		//��Ϊ��������Ϊ�����ҵ�temp ��λ�� ���λ�õ�ǰһ���ڵ㣬������벻��
		HeroNode temp = head;
		boolean flag = false; // flag��־��ӵı���Ƿ���ڣ�Ĭ��Ϊfalse
		while(true) {
			if(temp.next == null) {//˵��temp�Ѿ�����������
				break; //
			} 
			if(temp.next.no > heroNode.no) { //λ���ҵ�������temp�ĺ������
				break;
			} else if (temp.next.no == heroNode.no) {//˵��ϣ����ӵ�heroNode�ı����Ȼ����
				
				flag = true; //˵����Ŵ���
				break;
			}
			temp = temp.next; //���ƣ�������ǰ����
		}
		//�ж�flag ��ֵ
		if(flag) { //������ӣ�˵����Ŵ���
			System.out.printf("׼�������Ӣ�۵ı�� %d �Ѿ�������, ���ܼ���\n", heroNode.no);
		} else {
			//���뵽������, temp�ĺ���
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	//�޸Ľڵ����Ϣ, ����no������޸ģ���no��Ų��ܸ�.
	//˵��
	//1. ���� newHeroNode �� no ���޸ļ���
	public void update(HeroNode newHeroNode) {
		//�ж��Ƿ��
		if(head.next == null) {
			System.out.println("����Ϊ��~");
			return;
		}
		//�ҵ���Ҫ�޸ĵĽڵ�, ����no���
		//����һ����������
		HeroNode temp = head.next;
		boolean flag = false; //��ʾ�Ƿ��ҵ��ýڵ�
		while(true) {
			if (temp == null) {
				break; //�Ѿ�����������
			}
			if(temp.no == newHeroNode.no) {
				//�ҵ�
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//����flag �ж��Ƿ��ҵ�Ҫ�޸ĵĽڵ�
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else { //û���ҵ�
			System.out.printf("û���ҵ� ��� %d �Ľڵ㣬�����޸�\n", newHeroNode.no);
		}
	}
	
	//ɾ���ڵ�
	//˼·
	//1. head ���ܶ������������Ҫһ��temp�����ڵ��ҵ���ɾ���ڵ��ǰһ���ڵ�
	//2. ˵�������ڱȽ�ʱ����temp.next.no ��  ��Ҫɾ���Ľڵ��no�Ƚ�
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false; // ��־�Ƿ��ҵ���ɾ���ڵ��
		while(true) {
			if(temp.next == null) { //�Ѿ�����������
				break;
			}
			if(temp.next.no == no) {
				//�ҵ��Ĵ�ɾ���ڵ��ǰһ���ڵ�temp
				flag = true;
				break;
			}
			temp = temp.next; //temp���ƣ�����
		}
		//�ж�flag
		if(flag) { //�ҵ�
			//����ɾ��
			temp.next = temp.next.next;
		}else {
			System.out.printf("Ҫɾ���� %d �ڵ㲻����\n", no);
		}
	}
	
	//��ʾ����[����]
	public void list() {
		//�ж������Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		//��Ϊͷ�ڵ㣬���ܶ������������Ҫһ����������������
		HeroNode temp = head.next;
		while(true) {
			//�ж��Ƿ��������
			if(temp == null) {
				break;
			}
			//����ڵ����Ϣ
			System.out.println(temp);
			//��temp���ƣ� һ��С��
			temp = temp.next;
		}
	}
}

//����HeroNode �� ÿ��HeroNode �������һ���ڵ�
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next; //ָ����һ���ڵ�
	//������
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//Ϊ����ʾ��������������toString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
	
}
