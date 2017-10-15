package com.example.demo.util.freelogin;

import java.io.UnsupportedEncodingException;


/**
* @author: Chenzhenyong
* @description: 字符串处理工具类
* @date: Created in 10:27 2018/8/23
*/
public class StringUtil {

	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		int strLen;
		if (str != null && (strLen = str.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}

	/**
	 *
	 * @param data
	 * @return
	 */
	public static byte[] getBytes(String data){
		byte [] bytes = new byte[]{};
		try {
			bytes = data.getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return  bytes;
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);

		for(int i = 0; i < bArray.length; ++i) {
			String sTemp = Integer.toHexString(255 & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}

			sb.append(sTemp.toUpperCase());
		}

		return sb.toString();
	}



}
