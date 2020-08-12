package com.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		
		
		//��ɽ�һ����׺���ʽת�ɺ�׺���ʽ�Ĺ���
		//˵��
		//1. 1+((2+3)��4)-5 => ת��  1 2 3 + 4 �� + 5 �C
		//2. ��Ϊֱ�Ӷ�str ���в����������㣬��� �Ƚ�  "1+((2+3)��4)-5" =�� ��׺�ı��ʽ��Ӧ��List
		//   �� "1+((2+3)��4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
		//3. ���õ�����׺���ʽ��Ӧ��List => ��׺���ʽ��Ӧ��List
		//   �� ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =�� ArrayList [1,2,3,+,4,*,+,5,�C]
		
		String expression = "1+((2+3)*4)-5";//ע����ʽ 
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("��׺���ʽ��Ӧ��List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
		List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
		System.out.println("��׺���ʽ��Ӧ��List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,�C] 
		
		System.out.printf("expression=%d", calculate(suffixExpreesionList)); // ?
		
		
		
		/*
		
		//�ȶ�����沨�����ʽ
		//(30+4)��5-6  => 30 4 + 5 �� 6 - => 164
		// 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / + 
		//���� 
		//˵��Ϊ�˷��㣬�沨�����ʽ �����ֺͷ���ʹ�ÿո����
		//String suffixExpression = "30 4 + 5 * 6 -";
		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
		//˼·
		//1. �Ƚ� "3 4 + 5 �� 6 - " => �ŵ�ArrayList��
		//2. �� ArrayList ���ݸ�һ������������ ArrayList ���ջ ��ɼ���
		
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);
		int res = calculate(list);
		System.out.println("����Ľ����=" + res);
		
		*/
	}
	
	
	
	//�� ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =�� ArrayList [1,2,3,+,4,*,+,5,�C]
	//���������õ�����׺���ʽ��Ӧ��List => ��׺���ʽ��Ӧ��List
	public static List<String> parseSuffixExpreesionList(List<String> ls) {
		//��������ջ
		Stack<String> s1 = new Stack<String>(); // ����ջ
		//˵������Ϊs2 ���ջ��������ת�������У�û��pop���������Һ������ǻ���Ҫ�������
		//��˱Ƚ��鷳���������ǾͲ��� Stack<String> ֱ��ʹ�� List<String> s2
		//Stack<String> s2 = new Stack<String>(); // �����м�����ջs2
		List<String> s2 = new ArrayList<String>(); // �����м�����Lists2
		
		//����ls
		for(String item: ls) {
			//�����һ����������s2
			if(item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				//����������š�)���������ε���s1ջ�������������ѹ��s2��ֱ������������Ϊֹ����ʱ����һ�����Ŷ���
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//!!! �� ( ���� s1ջ�� ����С����
			} else {
				//��item�����ȼ�С�ڵ���s1ջ�������, ��s1ջ������������������뵽s2�У��ٴ�ת��(4.1)��s1���µ�ջ���������Ƚ�
				//���⣺����ȱ��һ���Ƚ����ȼ��ߵ͵ķ���
				while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
					s2.add(s1.pop());
				}
				//����Ҫ��itemѹ��ջ
				s1.push(item);
			}
		}
		
		//��s1��ʣ�����������ε���������s2
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}

		return s2; //ע����Ϊ�Ǵ�ŵ�List, ��˰�˳��������Ƕ�Ӧ�ĺ�׺���ʽ��Ӧ��List
		
	}
	
	//�������� ��׺���ʽת�ɶ�Ӧ��List
	//  s="1+((2+3)��4)-5";
	public static List<String> toInfixExpressionList(String s) {
		//����һ��List,�����׺���ʽ ��Ӧ������
		List<String> ls = new ArrayList<String>();
		int i = 0; //��ʱ��һ��ָ�룬���ڱ��� ��׺���ʽ�ַ���
		String str; // �Զ�λ����ƴ��
		char c; // ÿ������һ���ַ����ͷ��뵽c
		do {
			//���c��һ�������֣�����Ҫ���뵽ls
			if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) {
				ls.add("" + c);
				i++; //i��Ҫ����
			} else { //�����һ��������Ҫ���Ƕ�λ��
				str = ""; //�Ƚ�str �ó�"" '0'[48]->'9'[57]
				while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
					str += c;//ƴ��
					i++;
				}
				ls.add(str);
			}
		}while(i < s.length());
		return ls;//����
	}
	
	//��һ���沨�����ʽ�� ���ν����ݺ������ ���뵽 ArrayList��
	public static List<String> getListString(String suffixExpression) {
		//�� suffixExpression �ָ�
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele: split) {
			list.add(ele);
		}
		return list;
		
	}
	
	//��ɶ��沨�����ʽ������
	/*
	 * 1)��������ɨ�裬��3��4ѹ���ջ��
		2)����+���������˵���4��3��4Ϊջ��Ԫ�أ�3Ϊ�ζ�Ԫ�أ��������3+4��ֵ����7���ٽ�7��ջ��
		3)��5��ջ��
		4)�������ǡ����������˵���5��7�������7��5=35����35��ջ��
		5)��6��ջ��
		6)�����-������������35-6��ֵ����29���ɴ˵ó����ս��
	 */
	
	public static int calculate(List<String> ls) {
		// ������ջ, ֻ��Ҫһ��ջ����
		Stack<String> stack = new Stack<String>();
		// ���� ls
		for (String item : ls) {
			// ����ʹ��������ʽ��ȡ����
			if (item.matches("\\d+")) { // ƥ����Ƕ�λ��
				// ��ջ
				stack.push(item);
			} else {
				// pop���������������㣬 ����ջ
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num1 - num2;
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num1 / num2;
				} else {
					throw new RuntimeException("���������");
				}
				//��res ��ջ
				stack.push("" + res);
			}
			
		}
		//�������stack�е�������������
		return Integer.parseInt(stack.pop());
	}

}

//��дһ���� Operation ���Է���һ������� ��Ӧ�����ȼ�
class Operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	
	//дһ�����������ض�Ӧ�����ȼ�����
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("�����ڸ������" + operation);
			break;
		}
		return result;
	}
	
}
