package com.datastructure.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		
		//����ѹ���ļ�
//		String srcFile = "d://Uninstall.xml";
//		String dstFile = "d://Uninstall.zip";
//		
//		zipFile(srcFile, dstFile);
//		System.out.println("ѹ���ļ�ok~~");
		
		
		//���Խ�ѹ�ļ�
		String zipFile = "d://Uninstall.zip";
		String dstFile = "d://Uninstall2.xml";
		unZipFile(zipFile, dstFile);
		System.out.println("��ѹ�ɹ�!");
		
		/*
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length); //40
		
		byte[] huffmanCodesBytes= huffmanZip(contentBytes);
		System.out.println("ѹ����Ľ����:" + Arrays.toString(huffmanCodesBytes) + " ����= " + huffmanCodesBytes.length);
		
		
		//����һ��byteToBitString����
		//System.out.println(byteToBitString((byte)1));
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
		
		System.out.println("ԭ�����ַ���=" + new String(sourceBytes)); // "i like like like java do you like a java"
		*/
		
		
		
		//��ν� ���ݽ��н�ѹ(����)  
		//�ֲ�����
		/*
		List<Node> nodes = getNodes(contentBytes);
		System.out.println("nodes=" + nodes);
		
		//����һ�ѣ������ĺշ�����
		System.out.println("�շ�����");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("ǰ�����");
		huffmanTreeRoot.preOrder();
		
		//����һ���Ƿ������˶�Ӧ�ĺշ�������
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("~���ɵĺշ��������= " + huffmanCodes);
		
		//����
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));//17
		
		//����huffmanCodeBytes ���� */
		
		
	}
	
	//��дһ����������ɶ�ѹ���ļ��Ľ�ѹ
	/**
	 * 
	 * @param zipFile ׼����ѹ���ļ�
	 * @param dstFile ���ļ���ѹ���ĸ�·��
	 */
	public static void unZipFile(String zipFile, String dstFile) {
		
		//�����ļ�������
		InputStream is = null;
		//����һ������������
		ObjectInputStream ois = null;
		//�����ļ��������
		OutputStream os = null;
		try {
			//�����ļ�������
			is = new FileInputStream(zipFile);
			//����һ����  is�����Ķ���������
			ois = new ObjectInputStream(is);
			//��ȡbyte����  huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//��ȡ�շ��������
			Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
			
			//����
			byte[] bytes = decode(huffmanCodes, huffmanBytes);
			//��bytes ����д�뵽Ŀ���ļ�
			os = new FileOutputStream(dstFile);
			//д���ݵ� dstFile �ļ�
			os.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			
			try {
				os.close();
				ois.close();
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
			
		}
	}
	
	//��д��������һ���ļ�����ѹ��
	/**
	 * 
	 * @param srcFile �㴫���ϣ��ѹ�����ļ���ȫ·��
	 * @param dstFile ����ѹ����ѹ���ļ��ŵ��ĸ�Ŀ¼
	 */
	public static void zipFile(String srcFile, String dstFile) {
		
		//���������
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//�����ļ���������
		FileInputStream is = null;
		try {
			//�����ļ���������
			is = new FileInputStream(srcFile);
			//����һ����Դ�ļ���Сһ����byte[]
			byte[] b = new byte[is.available()];
			//��ȡ�ļ�
			is.read(b);
			//ֱ�Ӷ�Դ�ļ�ѹ��
			byte[] huffmanBytes = huffmanZip(b);
			//�����ļ��������, ���ѹ���ļ�
			os = new FileOutputStream(dstFile);
			//����һ�����ļ������������ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//�� �շ����������ֽ�����д��ѹ���ļ�
			oos.writeObject(huffmanBytes); //�����ǰ�
			//���������Զ������ķ�ʽд�� �շ������룬��Ϊ���Ժ����ǻָ�Դ�ļ�ʱʹ��
			//ע��һ��Ҫ�Ѻշ������� д��ѹ���ļ�
			oos.writeObject(huffmanCodes);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
				oos.close();
				os.close();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	//������ݵĽ�ѹ
	//˼·
	//1. ��huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	//   ��д��ת�� �շ��������Ӧ�Ķ����Ƶ��ַ��� "1010100010111..."
	//2.  �շ��������Ӧ�Ķ����Ƶ��ַ��� "1010100010111..." =�� ���� �շ�������  =�� "i like like like java do you like a java"
	
	
	//��дһ����������ɶ�ѹ�����ݵĽ���
	/**
	 * 
	 * @param huffmanCodes �շ�������� map
	 * @param huffmanBytes �շ�������õ����ֽ�����
	 * @return ����ԭ�����ַ�����Ӧ������
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		
		//1. �ȵõ� huffmanBytes ��Ӧ�� �����Ƶ��ַ��� �� ��ʽ 1010100010111...
		StringBuilder stringBuilder = new StringBuilder();
		//��byte����ת�ɶ����Ƶ��ַ���
		for(int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//�ж��ǲ������һ���ֽ�
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}
		//���ַ�����װָ���ĺշ���������н���
		//�Ѻշ����������е�������Ϊ�����ѯ a->100 100->a
		Map<String, Byte>  map = new HashMap<String,Byte>();
		for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		//����Ҫ�����ϣ����byte
		List<Byte> list = new ArrayList<>();
		//i �������ɾ�������,ɨ�� stringBuilder 
		for(int  i = 0; i < stringBuilder.length(); ) {
			int count = 1; // С�ļ�����
			boolean flag = true;
			Byte b = null;
			
			while(flag) {
				//1010100010111...
				//������ȡ�� key 1 
				String key = stringBuilder.substring(i, i+count);//i ��������count�ƶ���ָ��ƥ�䵽һ���ַ�
				b = map.get(key);
				if(b == null) {//˵��û��ƥ�䵽
					count++;
				}else {
					//ƥ�䵽
					flag = false;
				}
			}
			list.add(b);
			i += count;//i ֱ���ƶ��� count	
		}
		//��forѭ������������list�оʹ�������е��ַ�  "i like like like java do you like a java"
		//��list �е����ݷ��뵽byte[] ������
		byte b[] = new byte[list.size()];
		for(int i = 0;i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
		
	}
 	
	/**
	 * ��һ��byte ת��һ�������Ƶ��ַ���, ��������������Բο��ҽ���Java���� �����Ƶ�ԭ�룬���룬����
	 * @param b ����� byte
	 * @param flag ��־�Ƿ���Ҫ����λ�����true ����ʾ��Ҫ����λ�������false��ʾ����, ��������һ���ֽڣ����貹��λ
	 * @return �Ǹ�b ��Ӧ�Ķ����Ƶ��ַ�������ע���ǰ����뷵�أ�
	 */
	private static String byteToBitString(boolean flag, byte b) {
		//ʹ�ñ������� b
		int temp = b; //�� b ת�� int
		//������������ǻ����ڲ���λ
		if(flag) {
			temp |= 256; //��λ�� 256  1 0000 0000  | 0000 0001 => 1 0000 0001
		}
		String str = Integer.toBinaryString(temp); //���ص���temp��Ӧ�Ķ����ƵĲ���
		if(flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}
	
	//ʹ��һ����������ǰ��ķ�����װ�������������ǵĵ���.
	/**
	 * 
	 * @param bytes ԭʼ���ַ�����Ӧ���ֽ�����
	 * @return �Ǿ��� �շ������봦�����ֽ�����(ѹ���������)
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		//���� nodes �����ĺշ�����
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		//��Ӧ�ĺշ�������(���� �շ�����)
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		//�������ɵĺշ������룬ѹ���õ�ѹ����ĺշ��������ֽ�����
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	
	
	//��дһ�����������ַ�����Ӧ��byte[] ���飬ͨ�����ɵĺշ������������һ���շ������� ѹ�����byte[]
	/**
	 * 
	 * @param bytes ��ʱԭʼ���ַ�����Ӧ�� byte[]
	 * @param huffmanCodes ���ɵĺշ�������map
	 * @return ���غշ������봦���� byte[] 
	 * ������ String content = "i like like like java do you like a java"; =�� byte[] contentBytes = content.getBytes();
	 * ���ص��� �ַ��� "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
	 * => ��Ӧ�� byte[] huffmanCodeBytes  ���� 8λ��Ӧһ�� byte,���뵽 huffmanCodeBytes
	 * huffmanCodeBytes[0] =  10101000(����) => byte  [�Ƶ�  10101000=> 10101000 - 1 => 10100111(����)=> 11011000= -88 ]
	 * huffmanCodeBytes[1] = -88
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		
		//1.���� huffmanCodes ��  bytes ת��  �շ��������Ӧ���ַ���
		StringBuilder stringBuilder = new StringBuilder();
		//����bytes ���� 
		for(byte b: bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		
		//System.out.println("���� stringBuilder~~~=" + stringBuilder.toString());
		
		//�� "1010100010111111110..." ת�� byte[]
		
		//ͳ�Ʒ���  byte[] huffmanCodeBytes ����
		//һ�仰 int len = (stringBuilder.length() + 7) / 8;
		int len;
		if(stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		//���� �洢ѹ����� byte����
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;//��¼�ǵڼ���byte
		for (int i = 0; i < stringBuilder.length(); i += 8) { //��Ϊ��ÿ8λ��Ӧһ��byte,���Բ��� +8
				String strByte;
				if(i+8 > stringBuilder.length()) {//����8λ
					strByte = stringBuilder.substring(i);
				}else{
					strByte = stringBuilder.substring(i, i + 8);
				}	
				//��strByte ת��һ��byte,���뵽 huffmanCodeBytes
				huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
				index++;
		}
		return huffmanCodeBytes;
	}
	
	//���ɺշ�������Ӧ�ĺշ�������
	//˼·:
	//1. ���շ������������ Map<Byte,String> ��ʽ
	//   ���ɵĺշ��������{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
	static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
	//2. �����ɺշ��������ʾ����Ҫȥƴ��·��, ����һ��StringBuilder �洢ĳ��Ҷ�ӽ���·��
	static StringBuilder stringBuilder = new StringBuilder();
	
	
	//Ϊ�˵��÷��㣬�������� getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if(root == null) {
			return null;
		}
		//����root��������
		getCodes(root.left, "0", stringBuilder);
		//����root��������
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}
	
	/**
	 * ���ܣ��������node��������Ҷ�ӽ��ĺշ�������õ��������뵽huffmanCodes����
	 * @param node  ������
	 * @param code  ·���� ���ӽ���� 0, ���ӽ�� 1
	 * @param stringBuilder ����ƴ��·��
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//��code ���뵽 stringBuilder2
		stringBuilder2.append(code);
		if(node != null) { //���node == null������
			//�жϵ�ǰnode ��Ҷ�ӽ�㻹�Ƿ�Ҷ�ӽ��
			if(node.data == null) { //��Ҷ�ӽ��
				//�ݹ鴦��
				//����ݹ�
				getCodes(node.left, "0", stringBuilder2);
				//���ҵݹ�
				getCodes(node.right, "1", stringBuilder2);
			} else { //˵����һ��Ҷ�ӽ��
				//�ͱ�ʾ�ҵ�ĳ��Ҷ�ӽ������
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
	
	//ǰ������ķ���
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("�շ�����Ϊ��");
		}
	}
	
	/**
	 * 
	 * @param bytes �����ֽ�����
	 * @return ���صľ��� List ��ʽ   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
	 */
	private static List<Node> getNodes(byte[] bytes) {
		
		//1����һ��ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//���� bytes , ͳ�� ÿһ��byte���ֵĴ���->map[key,value]
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) { // Map��û������ַ�����,��һ��
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		
		//��ÿһ����ֵ��ת��һ��Node ���󣬲����뵽nodes����
		//����map
		for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
		
	}
	
	//����ͨ��List ������Ӧ�ĺշ�����
	private static Node createHuffmanTree(List<Node> nodes) {
		
		while(nodes.size() > 1) {
			//����, ��С����
			Collections.sort(nodes);
			//ȡ����һ����С�Ķ�����
			Node leftNode = nodes.get(0);
			//ȡ���ڶ�����С�Ķ�����
			Node rightNode = nodes.get(1);
			//����һ���µĶ�����,���ĸ��ڵ� û��data, ֻ��Ȩֵ
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			//���Ѿ���������Ŷ�������nodesɾ��
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//���µĶ����������뵽nodes
			nodes.add(parent);
			
		}
		//nodes ���Ľ�㣬���Ǻշ������ĸ����
		return nodes.get(0);
		
	}
	

}



//����Node ,�����ݺ�Ȩֵ
class Node implements Comparable<Node>  {
	Byte data; // �������(�ַ�)��������'a' => 97 ' ' => 32
	int weight; //Ȩֵ, ��ʾ�ַ����ֵĴ���
	Node left;//
	Node right;
	public Node(Byte data, int weight) {
		
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		// ��С��������
		return this.weight - o.weight;
	}
	
	public String toString() {
		return "Node [data = " + data + " weight=" + weight + "]";
	}
	
	//ǰ�����
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
}
