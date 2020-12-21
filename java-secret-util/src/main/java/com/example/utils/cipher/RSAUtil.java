package com.example.utils.cipher;

import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSAUtil {


    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
    private static final String RSA_ALGORITHM_PADDING = "RSA/ECB/PKCS1Padding";

    private static final String NO_SUCH_ALGORITHM_EXCEPTION_MSG = "No such algorithm";
    private static final String INVALID_KEY_SPEC_EXCEPTION_MSG = "Invalid key spec";
    private static final String IO_EXCEPTION_MSG = "Reading key data error";
    private static final String NO_SUCH_PADDING_EXCEPTION_MSG = "No such padding";
    private static final String NULL_POINTER_EXCEPTION_MSG = "No key data found";
    private static final String INVALID_KEY_EXCEPTION_MSG = "Invalid key";
    private static final String ILLEGAL_BLOCK_SIZE_EXCEPTION_MSG = "Illegal block size";
    private static final String BAD_PADDING_EXCEPTION_MSG = "Bad padding";
    private static final String SIGNATURE_EXCEPTION_MSG = "Signature exception";
    private static final String UnsupportedEncodingExceptionMsg = "Unsupported encoding";


    /**
     * 加密
     * @param publicKey
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(RSAPublicKey publicKey, String content) throws Exception {
        if(publicKey== null|| StringUtil.isEmpty(content)){
            throw new Exception("加密公钥为空, 请设置");
        }
        byte[] plainTextData = content.getBytes();
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output= cipher.doFinal(plainTextData);
            return StringUtil.bytesToHexString(output);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
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
     * 加载私钥
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        RSAPrivateKey privateKey;
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(NO_SUCH_ALGORITHM_EXCEPTION_MSG);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKeySpecException(INVALID_KEY_SPEC_EXCEPTION_MSG);
        } catch (IOException e) {
            throw new IOException(IO_EXCEPTION_MSG);
        } catch (NullPointerException e) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MSG);
        }
        return privateKey;
    }

}
