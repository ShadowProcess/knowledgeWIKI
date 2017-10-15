package com.example.demo.util.secure;

public class Coding {

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n < b.length - 1)
			// hs = hs + " ";
		}
		return hs.toLowerCase();
	}

	public static byte[] hex2byte(String msgIncoming, String... inteval) {
		msgIncoming = msgIncoming.toUpperCase();
		byte[] tempByte;
		String tempStr[];
		if (inteval.length > 0 && inteval[0].length() > 0) {
			tempStr = msgIncoming.split(inteval[0]);

		} else {
			tempStr = new String[msgIncoming.length() / 2];
			for (int i = 0; i < (msgIncoming.length() / 2); i++) {
				tempStr[i] = msgIncoming.substring(i * 2, (i + 1) * 2);
			}
		}
		tempByte = new byte[tempStr.length];
		try {
			for (int i = 0; i < tempStr.length; i++) {
				tempByte[i] = (byte) Short.parseShort(tempStr[i], 16);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempByte;
	}
}
