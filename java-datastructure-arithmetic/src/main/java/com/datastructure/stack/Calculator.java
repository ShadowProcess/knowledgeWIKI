package com.datastructure.stack;

public class Calculator {

	public static void main(String[] args) {
		//����ǰ����ʦ˼·����ɱ��ʽ������
		String expression = "7*2*2-5+1-5+3-4"; // 15//��δ����λ�������⣿
		//��������ջ����ջ��һ������ջ
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		//������Ҫ����ر���
		int index = 0;//����ɨ��
		int num1 = 0; 
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' '; //��ÿ��ɨ��õ�char���浽ch
		String keepNum = ""; //����ƴ�� ��λ��
		//��ʼwhileѭ����ɨ��expression
		while(true) {
			//���εõ�expression ��ÿһ���ַ�
			ch = expression.substring(index, index+1).charAt(0);
			//�ж�ch��ʲô��Ȼ������Ӧ�Ĵ���
			if(operStack.isOper(ch)) {//����������
				//�жϵ�ǰ�ķ���ջ�Ƿ�Ϊ��
				if(!operStack.isEmpty()) {
					//�������ջ�в��������ͽ��бȽ�,�����ǰ�Ĳ����������ȼ�С�ڻ��ߵ���ջ�еĲ�����,����Ҫ����ջ��pop��������,
					//�ڴӷ���ջ��pop��һ�����ţ��������㣬���õ����������ջ��Ȼ�󽫵�ǰ�Ĳ����������ջ
					if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//������Ľ������ջ
						numStack.push(res);
						//Ȼ�󽫵�ǰ�Ĳ����������ջ
						operStack.push(ch);
					} else {
						//�����ǰ�Ĳ����������ȼ�����ջ�еĲ������� ��ֱ�������ջ.
						operStack.push(ch);
					}
				}else {
					//���Ϊ��ֱ�������ջ..
					operStack.push(ch); // 1 + 3
				}
			} else { //�����������ֱ������ջ
				
				//numStack.push(ch - 48); //? "1+3" '1' => 1
				//����˼·
				//1. �������λ��ʱ�����ܷ�����һ������������ջ����Ϊ�������Ƕ�λ��
				//2. �ڴ���������Ҫ��expression�ı��ʽ��index ���ٿ�һλ,��������ͽ���ɨ�裬����Ƿ��Ų���ջ
				//3. ���������Ҫ����һ������ �ַ���������ƴ��
				
				//�����λ��
				keepNum += ch;
				
				//���ch�Ѿ���expression�����һλ����ֱ����ջ
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				}else{
				
					//�ж���һ���ַ��ǲ������֣���������֣��ͼ���ɨ�裬����������������ջ
					//ע���ǿ���һλ������index++
					if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
						//�����һλ�������������ջ keepNum = "1" ���� "123"
						numStack.push(Integer.parseInt(keepNum));
						//��Ҫ��!!!!!!, keepNum���
						keepNum = "";
						
					}
				}
			}
			//��index + 1, ���ж��Ƿ�ɨ�赽expression���.
			index++;
			if (index >= expression.length()) {
				break;
			}
		}
		
		//�����ʽɨ����ϣ���˳��Ĵ� ��ջ�ͷ���ջ��pop����Ӧ�����ͷ��ţ�������.
		while(true) {
			//�������ջΪ�գ�����㵽���Ľ��, ��ջ��ֻ��һ�����֡������
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);//��ջ
		}
		//����ջ���������pop�������ǽ��
		int res2 = numStack.pop();
		System.out.printf("���ʽ %s = %d", expression, res2);
	}

}

//�ȴ���һ��ջ,ֱ��ʹ��ǰ�洴����
//����һ�� ArrayStack2 ��ʾջ, ��Ҫ��չ����
class ArrayStack2 {
	private int maxSize; // ջ�Ĵ�С
	private int[] stack; // ���飬����ģ��ջ�����ݾͷ��ڸ�����
	private int top = -1;// top��ʾջ������ʼ��Ϊ-1
	
	//������
	public ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	
	//����һ�����������Է��ص�ǰջ����ֵ, ���ǲ���������pop
	public int peek() {
		return stack[top];
	}
	
	//ջ��
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//ջ��
	public boolean isEmpty() {
		return top == -1;
	}
	//��ջ-push
	public void push(int value) {
		//���ж�ջ�Ƿ���
		if(isFull()) {
			System.out.println("ջ��");
			return;
		}
		top++;
		stack[top] = value;
	}
	//��ջ-pop, ��ջ�������ݷ���
	public int pop() {
		//���ж�ջ�Ƿ��
		if(isEmpty()) {
			//�׳��쳣
			throw new RuntimeException("ջ�գ�û������~");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//��ʾջ�����[����ջ]�� ����ʱ����Ҫ��ջ����ʼ��ʾ����
	public void list() {
		if(isEmpty()) {
			System.out.println("ջ�գ�û������~~");
			return;
		}
		//��Ҫ��ջ����ʼ��ʾ����
		for(int i = top; i >= 0 ; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}
	//��������������ȼ������ȼ��ǳ���Ա��ȷ��, ���ȼ�ʹ�����ֱ�ʾ
	//����Խ�������ȼ���Խ��.
	public int priority(int oper) {
		if(oper == '*' || oper == '/'){
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1; // �ٶ�Ŀǰ�ı��ʽֻ�� +, - , * , /
		}
	}
	//�ж��ǲ���һ�������
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	//���㷽��
	public int cal(int num1, int num2, int oper) {
		int res = 0; // res ���ڴ�ż���Ľ��
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;// ע��˳��
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
	
}
