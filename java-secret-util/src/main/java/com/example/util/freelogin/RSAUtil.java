package com.example.util.freelogin;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;


public class RSAUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    private static final String RSA_ALGORITHM_PADDING = "RSA/ECB/PKCS1Padding";

    private static final String NO_SUCH_ALGORITHM_EXCEPTION_MSG = "No such algorithm";
    private static final String INVALID_KEY_SPEC_EXCEPTION_MSG = "Invalid key spec";
    private static final String NO_SUCH_PADDING_EXCEPTION_MSG = "No such padding";
    private static final String NULL_POINTER_EXCEPTION_MSG = "No key data found";
    private static final String INVALID_KEY_EXCEPTION_MSG = "Invalid key";
    private static final String ILLEGAL_BLOCK_SIZE_EXCEPTION_MSG = "Illegal block size";
    private static final String BAD_PADDING_EXCEPTION_MSG = "Bad padding";
    private static final String SIGNATURE_EXCEPTION_MSG = "Signature exception";
    private static final String UnsupportedEncodingExceptionMsg = "Unsupported encoding";

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
        RSAPublicKey publicKey;
        try {
        	Decoder decoder=Base64.getMimeDecoder();
        	byte[] buffer =decoder.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException(INVALID_KEY_SPEC_EXCEPTION_MSG);
        }catch (NullPointerException e) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MSG);
        }
        return publicKey;
    }

    /**
     * 加载私钥
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        RSAPrivateKey privateKey;
        try {
        	Decoder decoder=Base64.getMimeDecoder();
        	byte[] buffer =decoder.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException(INVALID_KEY_SPEC_EXCEPTION_MSG);
        }catch (NullPointerException e) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MSG);
        }
        return privateKey;
    }

    /**
     * 加密过程
     *
     * @param publicKey 公钥
     * @param content   明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(RSAPublicKey publicKey, String content) throws Exception {
        byte[] plainTextData = content.getBytes();
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(RSA_ALGORITHM_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return ByteFormat.bytesToHexString(output);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException(NO_SUCH_PADDING_EXCEPTION_MSG);
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException(INVALID_KEY_EXCEPTION_MSG);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalBlockSizeException(ILLEGAL_BLOCK_SIZE_EXCEPTION_MSG);
        } catch (BadPaddingException e) {
            throw new BadPaddingException(BAD_PADDING_EXCEPTION_MSG);
        }
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param content    密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(RSAPrivateKey privateKey, String content) throws Exception {
        byte[] cipherData = ByteFormat.hexToBytes(content);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(RSA_ALGORITHM_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException(NO_SUCH_PADDING_EXCEPTION_MSG);
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException(INVALID_KEY_EXCEPTION_MSG);
        } catch (IllegalBlockSizeException e) {
            throw new IllegalBlockSizeException(ILLEGAL_BLOCK_SIZE_EXCEPTION_MSG);
        } catch (BadPaddingException e) {
            throw new BadPaddingException(BAD_PADDING_EXCEPTION_MSG);
        }
    }

    /**
     * RSA签名
     * @param content 待签名数据
     * @param privateKey 私钥
     * @return 签名值
     */
    public static String sign(String content, PrivateKey privateKey) throws Exception {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(content.getBytes(DEFAULT_CHARSET));
            byte[] signed = signature.sign();
            return ByteFormat.bytesToHexString(signed);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (InvalidKeyException e) {
            throw  new InvalidKeyException(INVALID_KEY_EXCEPTION_MSG);
        } catch (SignatureException e) {
            throw new SignatureException(SIGNATURE_EXCEPTION_MSG);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException(UnsupportedEncodingExceptionMsg);
        }
    }

    /**
     * RSA验签
     * @param content 待签名数据
     * @param sign 签名值
     * @param publicKey 分配给开发商公钥
     * @return 布尔值
     */
    public static boolean verifySign(String content, String sign, PublicKey publicKey) throws Exception {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update( content.getBytes(DEFAULT_CHARSET) );
            return signature.verify(ByteFormat.hexToBytes(sign));
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (InvalidKeyException e) {
            throw  new InvalidKeyException(INVALID_KEY_EXCEPTION_MSG);
        } catch (SignatureException e) {
            throw new SignatureException(SIGNATURE_EXCEPTION_MSG);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException(UnsupportedEncodingExceptionMsg);
        }
    }

    /**
     * 私钥解密
     *
     * @param encryptedStr 已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKeyForLongStr(String encryptedStr, String privateKey)
            throws Exception {
        byte[] encryptedData = ByteFormat.hexToBytes(encryptedStr);
        byte[] keyBytes = Base64.getMimeDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > 128) {
                cache = cipher.doFinal(encryptedData, offSet, 128);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }
}
