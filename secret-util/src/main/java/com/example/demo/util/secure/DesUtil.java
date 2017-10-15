package com.example.demo.util.secure;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DesUtil {

	public static final String DEFAULT = "DES";
	public static final String DES = "DES";
	public static final String DESEDE = "DESede";
	public static final String AES = "AES";

	public DesUtil(){
	}

	/**
	 * 
	 * @param src
	 *            要加密的对象
	 * @param desKey
	 *            加密的密钥
	 * @param algorithm
	 *            加密算法,有"DESede","AES","DES" and etc.
	 * @return
	 */
	private static byte[] encrypt(byte[] src, String desKey, String algorithm) {

		try {
			SecretKey deskey = new SecretKeySpec(initKey(desKey.getBytes("UTF-8"), algorithm), algorithm);
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, deskey);
			return c.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param src
	 * @param desKey
	 * @param algorithm
	 * @return
	 */
	public static byte[] decrypt(byte[] src, String desKey, String algorithm) {
		try {
			SecretKey deskey = new SecretKeySpec(initKey(desKey.getBytes("UTF-8"), algorithm), algorithm);
			Cipher c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, deskey);
			return c.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] src, byte[] desKey, byte[] ivBs, String algorithm) {
		try {
			SecretKey deskey = new SecretKeySpec(desKey, algorithm);
			IvParameterSpec iv = new IvParameterSpec(ivBs);
			Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, deskey, iv);
			return c.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] src, byte[] desKey, String algorithm) {
		try {
			SecretKey deskey = new SecretKeySpec(desKey, algorithm);
			Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, deskey);
			return c.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param code
	 *            需要解密的字符串
	 * @param desKey
	 *            DES密钥
	 * @param algorithm
	 *            DES算法
	 * @return
	 */
	public static String deCode(String code, String desKey, String algorithm) {

		String result = null;
		try {
			byte[] t = decrypt(Coding.hex2byte(code, ""), desKey, algorithm);
			result = new String(t, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 加密
	 * 
	 * @param code
	 *            需要加密的字符串
	 * @param desKey
	 *            DES密钥
	 * @param algorithm
	 *            DES算法
	 * @return
	 */
	public static String enCode(String code, String desKey, String algorithm) {
		String result = null;
		try {
			byte[] src = code.getBytes("UTF-8");
			byte[] output = encrypt(src, desKey, algorithm);
			result = Coding.byte2hex(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static byte[] initKey(byte[] key, String algorithm) throws Exception {

		byte[] newkey = null;

		if (algorithm.equals(DES)) {
			newkey = new byte[8];
			int length = key.length;
			for (int i = 0; i < 8; i++) {
				if (i + 1 > length) {
					newkey[i] = new Byte("0");
				} else {
					newkey[i] = key[i];
				}
			}
		} else if (algorithm.equals(DESEDE)) {
			newkey = new byte[24];
			int length = key.length;
			for (int i = 0; i < 24; i++) {
				if (i + 1 > length) {
					newkey[i] = new Byte("0");
				} else {
					newkey[i] = key[i];
				}
			}
		} else if (algorithm.equals(AES)) {
			newkey = new byte[16];
			int length = key.length;
			for (int i = 0; i < 16; i++) {
				if (i + 1 > length) {
					newkey[i] = new Byte("0");
				} else {
					newkey[i] = key[i];
				}
			}
		} else {

		}
		return newkey;
	}

	public static void main(String[] args) {
		//String req = "1893aacdd17b87e8f5ffb3ec357af529135b5ffe8203029d9cfbd52c378092015b8ff29a2d8774fec106b0583a57b4acbfbc5d9a6814d0504536efaab8ab1201f2dd7825596e73bc";
		//String desKey = "coopchnseckey";
		//String s = deCode(req, desKey, DES);
		//System.out.println(s);

		//String req1 = "{\"mobileNum\": \"18018609133\",\"chn\": \"app\",\"reqTime\": \"1550132837329\",\"shorturi\": \"/index\"}";
		//String desKey1 = "illsecretandcoop";
		//String s1 = enCode(req1, desKey1, AES);
		//System.out.println(s1);

		//System.out.println("解密");
		//String req2 = "6ba962ed16b95f4076c6ead1254f4f1fda81e67b03e82216abf6380fca43a9bcb38a24454ae6d66df90dbd603cb9697da9a1d227ccb91f5daa12c4ca21929a03c7fb1aa83af0c81e0777c10ae5b9afffbbdac412fb3e3a1ec90657ef66186e16";
		//String s = deCode(req2, desKey1, AES);
		//System.out.println(s);

		String req = "cb56c6122a4728a5ee4c985db759fd00";
		String desKey = "xyf-wt-young";
		String s = deCode(req, desKey, DES);
		System.out.println(s);
	}
}
