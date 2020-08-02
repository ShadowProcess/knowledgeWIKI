package com.example.demo.util.freelogin;

import java.util.Locale;

/**
* @author: Chenzhenyong
* @description: HMAC_SHA1算法工具类
* @date: Created in 10:52 2018/8/23
*/
public class HmacSha1Util {
    private final int[] abcde = {0x67452301, 0xefcdab89, 0x98badcfe, 0x10325476, 0xc3d2e1f0 };
    /**
     * 摘要数据存储数组
     */
    private int[] digestInt = new int[5];
    /**
     * 计算过程中的临时数据存储数组
     */
    private int[] tmpData = new int[80];

    /**
     * 计算sha-1摘要
     * @param byteData
     * @return
     */
    private int process_input_bytes(byte[] byteData) {
        // 初试化常量
        System.arraycopy(abcde, 0, digestInt, 0, abcde.length);
        // 格式化输入字节数组，补10及长度数据
        byte[] newbyte = byteArrayFormatData(byteData);
        // 获取数据摘要计算的数据单元个数
        int MCount = newbyte.length / 64;
        // 循环对每个数据单元进行摘要计算
        for (int pos = 0; pos < MCount; pos++) {
            // 将每个单元的数据转换成16个整型数据，并保存到tmpData的前16个数组元素中
            for (int j = 0; j < 16; j++) {
                tmpData[j] = byteArrayToInt(newbyte, (pos * 64) + (j * 4));
            }
            // 摘要计算函数
            encrypt();
        }
        return 20;
    }

    /**
     * 格式化输入字节数组格式
     * @param byteData
     * @return
     */
    private byte[] byteArrayFormatData(byte[] byteData) {
        // 补0数量
        int zeros ;  //int zeros = 0 ;  sonar
        // 补位后总位数
        int size  ;  // int size = 0 ;  sonar
        // 原始数据长度
        int n = byteData.length;
        // 模64后的剩余位数
        int m = n % 64;
        // 计算添加0的个数以及添加10后的总长度
        if (m < 56) {
            zeros = 55 - m;
            size = n - m + 64;
        } else if (m == 56) {
            zeros = 63;
            size = n + 8 + 64;
        } else {
            zeros = 63 - m + 56;
            size = (n + 64) - m + 64;
        }
        // 补位后生成的新数组内容
        byte[] newbyte = new byte[size];
        // 复制数组的前面部分
        System.arraycopy(byteData, 0, newbyte, 0, n);
        // 获得数组Append数据元素的位置
        int l = n;
        // 补1操作
        newbyte[l++] = (byte) 0x80;
        // 补0操作
        for (int i = 0; i < zeros; i++) {
            newbyte[l++] = (byte) 0x00;
        }
        // 计算数据长度，补数据长度位共8字节，长整型
        long n1 = (long) n * 8;
        byte h8 = (byte) (n1 & 0xFF);
        byte h7 = (byte) ((n1 >> 8) & 0xFF);
        byte h6 = (byte) ((n1 >> 16) & 0xFF);
        byte h5 = (byte) ((n1 >> 24) & 0xFF);
        byte h4 = (byte) ((n1 >> 32) & 0xFF);
        byte h3 = (byte) ((n1 >> 40) & 0xFF);
        byte h2 = (byte) ((n1 >> 48) & 0xFF);
        byte h1 = (byte) (n1 >> 56);
        newbyte[l++] = h1;
        newbyte[l++] = h2;
        newbyte[l++] = h3;
        newbyte[l++] = h4;
        newbyte[l++] = h5;
        newbyte[l++] = h6;
        newbyte[l++] = h7;
        newbyte[l] = h8;
        return newbyte;
    }

    private int f1(int x, int y, int z) {
        return (x & y) | (~x & z);
    }

    private int f2(int x, int y, int z) {
        return x ^ y ^ z;
    }

    private int f3(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }

    private int f4(int x, int y) {
        return (x << y) | x >>> (32 - y);
    }

    /**
     * 单元摘要计算函数
     */
    private void encrypt() {
        for (int i = 16; i <= 79; i++) {
            tmpData[i] = f4(tmpData[i - 3] ^ tmpData[i - 8] ^ tmpData[i - 14] ^
                    tmpData[i - 16], 1);
        }
        int[] tmpabcde = new int[5];
        for (int i1 = 0; i1 < tmpabcde.length; i1++) {
            tmpabcde[i1] = digestInt[i1];
        }
        for (int j = 0; j <= 19; j++) {
            int tmp = f4(tmpabcde[0], 5) +
                f1(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +
                tmpData[j] + 0x5a827999;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int k = 20; k <= 39; k++) {
            int tmp = f4(tmpabcde[0], 5) +
                f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +
                tmpData[k] + 0x6ed9eba1;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int l = 40; l <= 59; l++) {
            int tmp = f4(tmpabcde[0], 5) +
                f3(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +
                tmpData[l] + 0x8f1bbcdc;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int m = 60; m <= 79; m++) {
            int tmp = f4(tmpabcde[0], 5) +
                f2(tmpabcde[1], tmpabcde[2], tmpabcde[3]) + tmpabcde[4] +
                tmpData[m] + 0xca62c1d6;
            tmpabcde[4] = tmpabcde[3];
            tmpabcde[3] = tmpabcde[2];
            tmpabcde[2] = f4(tmpabcde[1], 30);
            tmpabcde[1] = tmpabcde[0];
            tmpabcde[0] = tmp;
        }
        for (int i2 = 0; i2 < tmpabcde.length; i2++) {
            digestInt[i2] = digestInt[i2] + tmpabcde[i2];
        }
        for (int n = 0; n < tmpData.length; n++) {
            tmpData[n] = 0;
        }
    }

    /**
     * 4字节数组转换为整数
     * @param bytedata
     * @param i
     * @return
     */
    private int byteArrayToInt(byte[] bytedata, int i) {
        return ((bytedata[i] & 0xff) << 24) | ((bytedata[i + 1] & 0xff) << 16) |
        ((bytedata[i + 2] & 0xff) << 8) | (bytedata[i + 3] & 0xff);
    }

    /**
     * 整数转换为4字节数组
     * @param intValue
     * @param byteData
     * @param i
     */
    private void intToByteArray(int intValue, byte[] byteData, int i) {
        byteData[i] = (byte) (intValue >>> 24);
        byteData[i + 1] = (byte) (intValue >>> 16);
        byteData[i + 2] = (byte) (intValue >>> 8);
        byteData[i + 3] = (byte) intValue;
    }

    /**
     * 计算sha-1摘要，返回相应的字节数组
     * @param byteData
     * @return
     */
    public byte[] getDigestOfBytes(byte[] byteData) {
        process_input_bytes(byteData);
        byte[] digest = new byte[20];
        for (int i = 0; i < digestInt.length; i++) {
            intToByteArray(digestInt[i], digest, i * 4);
        }
        return digest;
    }

    /**
     *
     * @param data
     * @param key
     * @return
     */
	public static byte[] getHmacSHA1( String data,String key){
		if (StringUtil.isEmpty(key)) {
			return null;
		}
        byte[] ipadArray = new byte[64];
        byte[] opadArray = new byte[64];
        byte[] keyArray = new byte[64];
        int ex = key.length();
        HmacSha1Util sha1= new HmacSha1Util();
        if (key.length() > 64) {
            byte[] temp = sha1.getDigestOfBytes(StringUtil.getBytes(key));     //sonar key.getBytes()
            ex = temp.length;
            for (int i = 0; i < ex; i++) {
                keyArray[i] = temp[i];
            }
        }else{
            byte[] temp = StringUtil.getBytes(key);     //sonar key.getBytes()
            for (int i = 0; i < temp.length; i++) {
                keyArray[i] = temp[i];
            }
        }
        for (int i = ex; i < 64; i++) {
            keyArray[i] = 0;
        }
        for (int j = 0; j < 64; j++) {
            ipadArray[j] = (byte) (keyArray[j] ^ 0x36);
            opadArray[j] = (byte) (keyArray[j] ^ 0x5C);
        }
        byte[] tempResult = sha1.getDigestOfBytes(join(ipadArray, StringUtil.getBytes(data)));   //sonar data.getBytes()
        return sha1.getDigestOfBytes(join(opadArray,tempResult));
    }

    /**
     *
     * @param b1
     * @param b2
     * @return
     */
    private static byte[] join(byte[] b1,byte[] b2){
        int length = b1.length + b2.length;
        byte[] newer = new byte[length];
        for (int i = 0; i < b1.length; i++) {
            newer[i] = b1[i];
        }
        for (int i = 0; i < b2.length; i++) {
            newer[i+b1.length] = b2[i];
        }
        return newer;
    }

    /**
     *
     * @param src
     * @return
     */
	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {  
			return null;  
		}  
		for (int i = 0; i < src.length; i++) {  
			int v = src[i] & 0xFF;  
			String hv = Integer.toHexString(v).toUpperCase(Locale.CHINA);   //sonar Define the locale to be used in this String operation.
			if (hv.length() < 2) {  
				stringBuilder.append(0);  
			}  
			stringBuilder.append(hv);  
		}  
		return stringBuilder.toString();  
	}

    public static void main(String[] args) {
	    //原字符串
        String str = "hello";
        //秘钥
        String secret = "dYmzZLbZiUPyg45ZsBYjg7kjVwe2T7z5";

        //加密
        byte[] result = getHmacSHA1(str,secret);
        System.out.println("原字符串"+str);
        System.out.println("加密后字符串"+bytesToHexString(result));
    }

}