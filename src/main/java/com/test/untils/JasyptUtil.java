package com.test.untils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JasyptUtil {

	 private static final Logger logger = LoggerFactory.getLogger(JasyptUtil.class);

    private static final String DES_ALGORITHM = "DES";
    /**
     * DES加密
     * @param plainData
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String encryption(String plainData, String secretKey) throws Exception{
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(secretKey));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }catch(InvalidKeyException e){

        }

        try {
            // 为了防止解密时报javax.crypto.IllegalBlockSizeException: Input length must be multiple of 8 when decrypting with padded cipher异常，
            // 不能把加密后的字节数组直接转换成字符串
            byte[] buf = cipher.doFinal(plainData.getBytes());

            return Base64Utils.encode(buf);

        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("BadPaddingException", e);
        }

    }

    /**
     * DES解密
     * @param secretData
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String decryption(String secretData, String secretKey) throws Exception {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(secretKey));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw new Exception("NoSuchPaddingException", e);
        }catch(InvalidKeyException e){
            e.printStackTrace();
            throw new Exception("InvalidKeyException", e);

        }

        try {

            byte[] buf = cipher.doFinal(Base64Utils.decode(secretData.toCharArray()));

            return new String(buf);

        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new Exception("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw new Exception("BadPaddingException", e);
        }
    }

    /**
     * 获得秘密密钥
     * @param secretKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException{

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKey.getBytes());

        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(DES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
        }
        kg.init(secureRandom);
        //kg.init(56, secureRandom);

        // 生成密钥
        return kg.generateKey();
    }
	
    public static void main(String[] a) throws Exception{

        String input = "root";
        String key = "heweiqing";

        JasyptUtil des = new JasyptUtil();

        String result = des.encryption(input, key);
        System.out.println(result);

        System.out.println(des.decryption(result, key));

    }

}
