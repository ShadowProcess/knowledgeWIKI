package com.example.util.freelogin;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
* @author: Chenzhenyong
* @description: AES工具类
* @date: Created in 15:18 2018/7/25
*/
public class AESUtil {

	private static byte[] iv = "0000000000000000".getBytes();


	/**
	 * AES加密字符串
	 *
	 * @param content
	 *            需要被加密的字符串
	 * @param password
	 *            加密需要的密码
	 * @return 密文
	 */
	public static String encrypt(String content, String password) {
		try {

			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key,ivspec);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密

			return ByteFormat.bytesToHexString(result);

		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密AES加密过的字符串
	 *
	 * @param content
	 *            AES加密过过的内容
	 * @param password
	 *            加密时的密码
	 * @return 明文
	 */
	public static String decrypt(String content, String password) {
		try {
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			SecretKeySpec secretKey = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			byte[] contentByte = ByteFormat.hexToBytes(content);
			byte[] result = cipher.doFinal(contentByte);
			return new String(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		//原字符串
		String content = "test";
		//加密字符串
		String encryptContent = "A631A65615BCDF";
		//加密秘钥
		String key = "1234567890098765";

		String encStr = AESUtil.encrypt(content,key);

		System.out.println("原字符串："+content);
		System.out.println("加密后："+encStr);

		String decStr = AESUtil.decrypt(encStr,key);

		System.out.println("原字符串："+encStr);
		System.out.println("解密后："+decStr);
	}

}
