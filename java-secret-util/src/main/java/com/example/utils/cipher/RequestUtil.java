package com.example.utils.cipher;

import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
* @author: Chenzhenyong
* @description: 请求参数处理工具类
* @date: Created in 10:20 2018/8/23
*/
public class RequestUtil {

	/**
	 * 
	 * @param requestData
	 * @return
	 */
	public static String getParamString(Map<String, String> requestData){
		StringBuilder paramBuilder = new StringBuilder();
		//String sign = getSignString(requestData, secret);
		for(String key : requestData.keySet()){
			String value = requestData.get(key);
			paramBuilder.append(key).append("=").append(value).append("&");
		}
		//paramBuilder.append("sign=").append(sign);
		
		return paramBuilder.toString();
	}
	
	/**
	 * 
	 * @param requestData
	 * @param secret
	 * @return
	 */
	public static String getSignString(Map<String, String> requestData, String secret){
		String encryptValue = generatePlainText(requestData);
		String signString = HmacSha1Util.bytesToHexString(HmacSha1Util.getHmacSHA1(encryptValue, secret));
		return signString;
	}

	/**
	 *
	 * @param requestData
	 * @param secret
	 * @return
	 */
	public static String getParamStr(Map<String, String> requestData, String secret){
		String encryptValue = getParamString(requestData);
		String paramStr = AESUtil.encrypt(encryptValue,secret);
		return paramStr;
	}

	/**
	 *
	 * @param priKey
	 * @param publicKeyStr
	 * @return
	 */
	public static String getParamKey(String publicKeyStr, String priKey){
		RSAPublicKey rsaPublicKey = null;
		String rsaContent = "";
		try{
		rsaPublicKey = PublicKeyUtil.loadPublicKey(publicKeyStr);
		//RSA加密后的内容
		 rsaContent = RSAUtil.encrypt(rsaPublicKey,priKey);
	//	System.out.println("RSA加密后的内容:" + rsaContent );
		}catch (Exception e){}

		return rsaContent;
	}

	
	/**
	 * 参数排序 jdk1.7版本
	 * @param returnData
	 * @return
	 */
	public static String generatePlainText(Map<String,String> returnData){
		//排序参数
		List<Map.Entry<String,String>> mappingList = new ArrayList<Map.Entry<String,String>>(returnData.entrySet());
		Collections.sort(mappingList,new Comparator<Map.Entry<String,String>>(){
			@Override
			public int compare(
				Map.Entry<String,String> mapping1,
				Map.Entry<String,String> mapping2){ 
				return mapping1.getKey().compareTo(mapping2.getKey()); 
			} 
		});
		StringBuilder plainText= new StringBuilder();
		for(Map.Entry<String,String> mapping:mappingList){		  
			plainText.append(mapping.getValue());
		}
		return plainText.toString();
	}

	/**
	 * 参数排序 jdk1.8版本
	 * @param returnData
	 * @return
	 */
	private static String generatePlainText2(Map<String,String> returnData){
		List<Map.Entry<String,String>> mappingList = new ArrayList<>(returnData.entrySet());
		mappingList.sort(Comparator.comparing(Map.Entry::getKey));
		StringBuilder plainText= new StringBuilder();
		for(Map.Entry<String,String> mapping:mappingList){
			plainText.append(mapping.getValue());
		}
		return plainText.toString();
	}
}
