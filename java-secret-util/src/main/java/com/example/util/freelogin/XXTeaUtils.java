package com.example.util.freelogin;

import java.io.UnsupportedEncodingException;

public class XXTeaUtils {

	private static final String charset = "UTF-8";

	public static byte[] encrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(encrypt(toIntArray(data, true), toIntArray(key, false)), false);
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		if (data.length == 0) {
			return data;
		}
		return toByteArray(decrypt(toIntArray(data, false), toIntArray(key, false)), true);
	}

	/**
	 * Encrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] encrypt(int[] v, int[] k) {
		int n = v.length - 1;
		if (n < 1) {
			return v;
		}
		if (k.length < 4) {
			int[] key = new int[4];
			System.arraycopy(k, 0, key, 0, k.length);
			k = key;
		}
		int z = v[n], y = v[0], delta = 0x9E3779B9, sum = 0, e;
		int p, q = 6 + 52 / (n + 1);
		while (q-- > 0) {
			sum = sum + delta;
			e = sum >>> 2 & 3;
			for (p = 0; p < n; p++) {
				y = v[p + 1];
				z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
			}
			y = v[0];
			z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
		}
		return v;
	}

	/**
	 * Decrypt data with key.
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	public static int[] decrypt(int[] v, int[] k) {
		int n = v.length - 1;
		if (n < 1) {
			return v;
		}
		if (k.length < 4) {
			int[] key = new int[4];
			System.arraycopy(k, 0, key, 0, k.length);
			k = key;
		}
		int z = v[n], y = v[0], delta = 0x9E3779B9, sum, e;
		int p, q = 6 + 52 / (n + 1);
		sum = q * delta;
		while (sum != 0) {
			e = sum >>> 2 & 3;
			for (p = n; p > 0; p--) {
				z = v[p - 1];
				y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
			}
			z = v[n];
			y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
			sum = sum - delta;
		}
		return v;
	}

	/**
	 * Convert byte array to int array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static int[] toIntArray(byte[] data, boolean includeLength) {
		int n = (((data.length & 3) == 0) ? (data.length >>> 2) : ((data.length >>> 2) + 1));
		int[] result;
		if (includeLength) {
			result = new int[n + 1];
			result[n] = data.length;
		} else {
			result = new int[n];
		}
		n = data.length;
		for (int i = 0; i < n; i++) {
			result[i >>> 2] |= (0x000000ff & data[i]) << ((i & 3) << 3);
		}
		return result;
	}

	/**
	 * Convert int array to byte array.
	 * 
	 * @param data
	 * @param includeLength
	 * @return
	 */
	private static byte[] toByteArray(int[] data, boolean includeLength) {
		int n;
		if (includeLength) {
			n = data[data.length - 1];
		} else {
			n = data.length << 2;
		}

		byte[] result = new byte[n];
		for (int i = 0; i < n; i++) {
			result[i] = (byte) (data[i >>> 2] >>> ((i & 3) << 3));
		}
		return result;
	}

	/**
	 * 字节数组转换为hex字符串
	 * 
	 * @param bArray
	 * @return
	 */
	private static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);

		for (int i = 0; i < bArray.length; ++i) {
			String sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * hex字符串转换为字节数组
	 * 
	 * @param str
	 * @return
	 */
	private static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		}

		char[] hex = str.toCharArray();

		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; ++i) {
			int high = Character.digit(hex[(i * 2)], 16);
			int low = Character.digit(hex[(i * 2 + 1)], 16);
			int value = high << 4 | low;
			if (value > 127)
				value -= 256;
			raw[i] = (byte) value;
		}
		return raw;
	}

	/**
	 * 使用XXTea 算法加密字符串
	 * 
	 * @param plain   被加密的字符串
	 * @param key     密钥
	 * @return 加密之后的hex 字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptStr(String plain, String key) throws Exception {
		if (plain == null || charset == null || key == null) {
			return null;
		}
		byte[] bytes = encrypt(plain.getBytes(charset), key.getBytes(charset));

		return bytesToHexString(bytes).toLowerCase();
	}

	/**
	 * 使用XXTea 算法解密字符串
	 * 
	 * @param hexStr  加密之后的hex 字符串
	 * @param key     密钥
	 * @return 解密之后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String decryptStr(String hexStr, String key) throws Exception {
		if (hexStr == null || key == null) {
			return null;
		}
		byte[] bytes = decrypt(hexToBytes(hexStr), key.getBytes(charset));

		return new String(bytes, charset);
	}

	public static void main(String[] args) throws Exception {
		String secretKey = "sAecMFcAlIXes93VaWXgr3jgMup4Y0a6";
		System.out.println(encryptStr("a=1&b=2&c=3", secretKey));
		System.out.println(XXTeaUtils.decryptStr("f6c45d934cde581e908d02487720161d", secretKey));
	}

}
