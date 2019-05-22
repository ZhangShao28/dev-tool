package cn.moonnow.sign.service.impl;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cs {

  public static void main(String[] args) {
    String s = "test";
    String c1 = RSAUtilV2.encryptByPublic(s.getBytes(), null);
    String m1 = RSAUtilV2.decryptByPrivate(c1, null);
    String c2 = RSAUtilV2.encryptByPrivate(s.getBytes(), null);
    String m2 = RSAUtilV2.decryptByPublic(c2, null);
    System.out.println(c1);
    System.out.println(m1);
    System.out.println(c2);
    System.out.println(m2);
  }

  /**
   * 生成key，作为加密和解密密钥且只有密钥相同解密加密才会成功
   * 
   * @return
   */
  public static Key createKey() {

    try {
      // 生成key
      KeyGenerator keyGenerator;
      // 构造密钥生成器，指定为AES算法,不区分大小写
      keyGenerator = KeyGenerator.getInstance("AES");
      // 生成一个128位的随机源,根据传入的字节数组
      keyGenerator.init(128);
      // 产生原始对称密钥
      SecretKey secretKey = keyGenerator.generateKey();
      // 获得原始对称密钥的字节数组
      byte[] keyBytes = secretKey.getEncoded();
      // key转换,根据字节数组生成AES密钥
      Key key = new SecretKeySpec(keyBytes, "AES");
      return key;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**加密
   * @param context 需要加密的明文
   * @param key 加密用密钥
   * @return
   */
  public static byte[] jdkAES(String context, Key key) {
    try {
 
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      
      cipher.init(cipher.ENCRYPT_MODE, key);
      //将加密并编码后的内容解码成字节数组
      byte[] result = cipher.doFinal(context.getBytes());
 
//      System.out.println("jdk aes:" + Base64.encode(result));
 
      return result;
 
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
        | BadPaddingException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /** 解密
   * @param result 加密后的密文byte数组
   * @param key 解密用密钥
   */
  public static void decrypt(byte[] result, Key key) {
    
    Cipher cipher;
    try {
      
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
      cipher.init(Cipher.DECRYPT_MODE, key);
      result = cipher.doFinal(result);
 
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
    }
 
    System.out.println("jdk aes desrypt:" + new String(result));
  }

}
