package com.datastructure.hashtab;

import java.util.Scanner;

public class HashTabDemo {

	public static void main(String[] args) {
		
		//������ϣ��
		HashTab hashTab = new HashTab(7);
		
		//дһ���򵥵Ĳ˵�
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("add:  ��ӹ�Ա");
			System.out.println("list: ��ʾ��Ա");
			System.out.println("find: ���ҹ�Ա");
			System.out.println("exit: �˳�ϵͳ");
			
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("����id");
				int id = scanner.nextInt();
				System.out.println("��������");
				String name = scanner.next();
				//���� ��Ա
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.println("������Ҫ���ҵ�id");
				id = scanner.nextInt();
				hashTab.findEmpById(id);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
		
	}

}

//����HashTab �����������
class HashTab {
	private EmpLinkedList[] empLinkedListArray;
	private int size; //��ʾ�ж���������
	
	//������
	public HashTab(int size) {
		this.size = size;
		//��ʼ��empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		//����һ����, ��ʱ��Ҫ�ֱ��ʼ��ÿ������
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}
	
	//��ӹ�Ա
	public void add(Emp emp) {
		//����Ա����id ,�õ���Ա��Ӧ����ӵ���������
		int empLinkedListNO = hashFun(emp.id);
		//��emp ��ӵ���Ӧ��������
		empLinkedListArray[empLinkedListNO].add(emp);
		
	}
	//�������е�����,����hashtab
	public void list() {
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}
	
	//���������id,���ҹ�Ա
	public void findEmpById(int id) {
		//ʹ��ɢ�к���ȷ���������������
		int empLinkedListNO = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp != null) {//�ҵ�
			System.out.printf("�ڵ�%d���������ҵ� ��Ա id = %d\n", (empLinkedListNO + 1), id);
		}else{
			System.out.println("�ڹ�ϣ���У�û���ҵ��ù�Ա~");
		}
	}
	
	//��дɢ�к���, ʹ��һ����ȡģ��
	public int hashFun(int id) {
		return id % size;
	}
	
	
}

//��ʾһ����Ա
class Emp {
	public int id;
	public String name;
	public Emp next; //next Ĭ��Ϊ null
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

//����EmpLinkedList ,��ʾ����
class EmpLinkedList {
	//ͷָ�룬ִ�е�һ��Emp,���������������head ��ֱ��ָ���һ��Emp
	private Emp head; //Ĭ��null
	
	//��ӹ�Ա������
	//˵��
	//1. �ٶ�������ӹ�Աʱ��id ������������id�ķ������Ǵ�С����
	//   ������ǽ��ù�Աֱ�Ӽ��뵽���������󼴿�
	public void add(Emp emp) {
		//�������ӵ�һ����Ա
		if(head == null) {
			head = emp;
			return;
		}
		//������ǵ�һ����Ա����ʹ��һ��������ָ�룬������λ�����
		Emp curEmp = head;
		while(true) {
			if(curEmp.next == null) {//˵�����������
				break;
			}
			curEmp = curEmp.next; //����
		}
		//�˳�ʱֱ�ӽ�emp ��������
		curEmp.next = emp;
	}
	
	//��������Ĺ�Ա��Ϣ
	public void list(int no) {
		if(head == null) { //˵������Ϊ��
			System.out.println("�� "+(no+1)+" ����Ϊ��");
			return;
		}
		System.out.print("�� "+(no+1)+" �������ϢΪ");
		Emp curEmp = head; //����ָ��
		while(true) {
			System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
			if(curEmp.next == null) {//˵��curEmp�Ѿ��������
				break;
			}
			curEmp = curEmp.next; //���ƣ�����
		}
		System.out.println();
	}
	
	//����id���ҹ�Ա
	//������ҵ����ͷ���Emp, ���û���ҵ����ͷ���null
	public Emp findEmpById(int id) {
		//�ж������Ƿ�Ϊ��
		if(head == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		//����ָ��
		Emp curEmp = head;
		while(true) {
			if(curEmp.id == id) {//�ҵ�
				break;//��ʱcurEmp��ָ��Ҫ���ҵĹ�Ա
			}
			//�˳�
			if(curEmp.next == null) {//˵��������ǰ����û���ҵ��ù�Ա
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;//�Ժ�
		}
		
		return curEmp;
	}
	
}

