package com.example.demo.util.freelogin;

/**
* @author: Chenzhenyong
* @description: xxtea加解密工具类
* @date: Created in 12:07 2018/7/24
*/
public class XXTeaUtil {
    public XXTeaUtil() {
    }

    public static byte[] encrypt(byte[] plainData, byte[] key) {
        return plainData != null && plainData.length != 0 && key != null ? toByteArray(encrypt(toIntArray(plainData, true), toIntArray(key, false)), false) : null;
    }

    public static byte[] decrypt(byte[] cipherData, byte[] key) {
        return cipherData != null && cipherData.length != 0 && key != null ? toByteArray(decrypt(toIntArray(cipherData, false), toIntArray(key, false)), true) : null;
    }

    private static int[] encrypt(int[] v, int[] k) {
        int n = v.length - 1;
        if (n < 1) {
            return v;
        } else {
            if (k.length < 4) {
                int[] key = new int[4];
                System.arraycopy(k, 0, key, 0, k.length);
                k = key;
            }

            int z = v[n];
            int y = v[0];
            int delta = -1640531527;
            int sum = 0;

            int e;
            int p;
            for(int var9 = 6 + 52 / (n + 1); var9-- > 0; z = v[n] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z)) {
                sum += delta;
                e = sum >>> 2 & 3;

                for(p = 0; p < n; ++p) {
                    y = v[p + 1];
                    z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
                }

                y = v[0];
            }

            return v;
        }
    }

    private static int[] decrypt(int[] v, int[] k) {
        int n = v.length - 1;
        if (n < 1) {
            return v;
        } else {
            if (k.length < 4) {
                int[] key = new int[4];
                System.arraycopy(k, 0, key, 0, k.length);
                k = key;
            }

            int var10000 = v[n];
            int y = v[0];
            int delta = -1640531527;
            int q = 6 + 52 / (n + 1);

            for(int sum = q * delta; sum != 0; sum -= delta) {
                int e = sum >>> 2 & 3;

                int p;
                int z;
                for(p = n; p > 0; --p) {
                    z = v[p - 1];
                    y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
                }

                z = v[n];
                y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            }
            return v;
        }
    }

    private static int[] toIntArray(byte[] data, boolean includeLength) {
        int n = (data.length & 3) == 0 ? data.length >>> 2 : (data.length >>> 2) + 1;
        int[] result;
        if (includeLength) {
            result = new int[n + 1];
            result[n] = data.length;
        } else {
            result = new int[n];
        }
        n = data.length;
        for(int i = 0; i < n; ++i) {
            result[i >>> 2] |= (255 & data[i]) << ((i & 3) << 3);
        }
        return result;
    }

    private static byte[] toByteArray(int[] data, boolean includeLength) {
        int n = data.length << 2;
        if (includeLength) {
            int m = data[data.length - 1];
            if (m > n || m <= 0) {
                return null;
            }

            n = m;
        }
        byte[] result = new byte[n];
        for(int i = 0; i < n; ++i) {
            result[i] = (byte)(data[i >>> 2] >>> ((i & 3) << 3) & 255);
        }
        return result;
    }

    public static void main(String[] args) {
        //待加密字符串
        String plainText = "accessCode=nm9279689e92624c9ab11c7bfd476c576f&gwAuth=asda11231";
        //秘钥
//        String appSecret = "hc6R8nXp5emo1lKXW1SJO1IlDrqFwmiR";
        String appSecret = "wvcsUt8qT4rvrUy2vA6wXvj3Ds2SL3lA";
        try {
            //xxtea 加密
            byte[] key = "8a584E9AgKgWHECzitAZ2bkZIBkyZEAL".getBytes("UTF-8");
            byte[]  encBytes = encrypt(plainText.getBytes("UTF-8"), appSecret.getBytes("UTF-8"));
            String params = ByteFormat.bytesToHexString(encBytes);
            System.out.println("原字符串："+plainText);
            System.out.println("加密后："+params);

            System.out.println("----------------------");
            //xxtea解密
//            String encStr = "F287C396804E607D15B86BFD1A08A474487DA28C92600E8473A61818B62A3331EAECD2C4055B2C4ECF4F38577EBED99F69CA445786F2D2F17AAE9CE85EE96B0E656CBB24DEB5E3D8D3B41C3CC72B2600BB3207371D923402576A52B529B31BCCD64C0C329E083D4665D43C2E112A64AC4F1AD9ACEEB93D69BEBC39FA";
            String encStr = "e86900d003512d5d3a4b71361914aa543f5d385a490530238cc91c38d504b752451b9ed2902abece78158b0cdbdd53d9af1a3073";
            byte[] pText = decrypt(ByteFormat.hexToBytes(encStr), appSecret.getBytes("UTF-8"));
            System.out.println("原密串："+encStr);
            System.out.println("解密后："+new String(pText));
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }
}
