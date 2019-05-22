package cn.moonnow.sign.service.impl;
/**
 * RSA算法加密/解密工具类V2
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSAUtilV2 {
  private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtilV2.class);
  /** 算法名称 */
  private static final String ALGORITHM = "RSA";
  /** 默认密钥大小 */
  private static final int KEY_SIZE = 1024;
  /** 用来指定保存密钥对的文件名和存储的名称 */
  private static final String PUBLIC_KEY_NAME = "publicKey";
  private static final String PRIVATE_KEY_NAME = "privateKey";
  private static final String PUBLIC_FILENAME = "publicKey.properties";
  private static final String PRIVATE_FILENAME = "privateKey.properties";
  /** 密钥对生成器 */
  private static KeyPairGenerator keyPairGenerator = null;

  private static KeyFactory keyFactory = null;
  /** 缓存的密钥对 */
  private static KeyPair keyPair = null;
  /** Base64 编码/解码器 JDK1.8 */
  private static Base64.Decoder decoder = Base64.getDecoder();
  private static Base64.Encoder encoder = Base64.getEncoder();
  /** 初始化密钥工厂 */
  static {
    try {
      keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
      keyFactory = KeyFactory.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  /** 私有构造器 */
  private RSAUtilV2() {
  }

  /**
   * 生成密钥对 将密钥分别用Base64编码保存到#publicKey.properties#和#privateKey.properties#文件中 保存的默认名称分别为publicKey和privateKey
   */
  public static synchronized void generateKeyPair() {
    try {
      keyPairGenerator.initialize(KEY_SIZE, new SecureRandom(UUID.randomUUID().toString().replaceAll("-", "").getBytes()));
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (InvalidParameterException e) {
      LOGGER.error("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".", e);
    } catch (NullPointerException e) {
      LOGGER.error("RSAUtils#key_pair_gen is null,can not generate KeyPairGenerator instance.", e);
    }
    RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
    String publicKeyString = encoder.encodeToString(rsaPublicKey.getEncoded());
    String privateKeyString = encoder.encodeToString(rsaPrivateKey.getEncoded());
    storeKey(publicKeyString, PUBLIC_KEY_NAME, PUBLIC_FILENAME);
    storeKey(privateKeyString, PRIVATE_KEY_NAME, PRIVATE_FILENAME);
  }

  /**
   * 将指定的密钥字符串保存到文件中,如果找不到文件，就创建
   * 
   * @param keyString 密钥的Base64编码字符串（值）
   * @param keyName   保存在文件中的名称（键）
   * @param fileName  目标文件名
   */
  private static void storeKey(String keyString, String keyName, String fileName) {
    Properties properties = new Properties();
    // 存放密钥的绝对地址
    String path = null;
    try {
      path = RSAUtilV2.class.getClassLoader().getResource(fileName).toString();
      path = path.substring(path.indexOf(":") + 1);
    } catch (NullPointerException e) {
      // 如果不存#fileName#就创建
      LOGGER.warn("storeKey()# " + fileName + " is not exist.Begin to create this file.");
      String classPath = RSAUtilV2.class.getClassLoader().getResource("").toString();
      String prefix = classPath.substring(classPath.indexOf(":") + 1);
      String suffix = fileName;
      File file = new File(prefix + suffix);
      try {
        file.createNewFile();
        path = file.getAbsolutePath();
      } catch (IOException e1) {
        LOGGER.error(fileName + " create fail.", e1);
      }
    }
    try (OutputStream out = new FileOutputStream(path)) {
      properties.setProperty(keyName, keyString);
      properties.store(out, "There is " + keyName);
    } catch (FileNotFoundException e) {
      LOGGER.error("ModulusAndExponent.properties is not found.", e);
    } catch (IOException e) {
      LOGGER.error("OutputStream output failed.", e);
    }
  }

  /**
   * 获取密钥字符串
   * 
   * @param keyName  需要获取的密钥名
   * @param fileName 密钥所在文件
   * @return Base64编码的密钥字符串
   */
  private static String getKeyString(String keyName, String fileName) {
    if (RSAUtilV2.class.getClassLoader().getResource(fileName) == null) {
      LOGGER.warn("getKeyString()# " + fileName + " is not exist.Will run #generateKeyPair()# firstly.");
      generateKeyPair();
    }
    try (InputStream in = RSAUtilV2.class.getClassLoader().getResource(fileName).openStream()) {
      Properties properties = new Properties();
      properties.load(in);
      return properties.getProperty(keyName);
    } catch (IOException e) {
      LOGGER.error("getKeyString()#" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 从文件获取RSA公钥
   * 
   * @return RSA公钥
   * @throws InvalidKeySpecException
   */
  public static RSAPublicKey getPublicKey() {
    try {
      byte[] keyBytes = decoder.decode(getKeyString(PUBLIC_KEY_NAME, PUBLIC_FILENAME));
      X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
      return (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
    } catch (InvalidKeySpecException e) {
      LOGGER.error("getPublicKey()#" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 从文件获取RSA私钥
   * 
   * @return RSA私钥
   * @throws InvalidKeySpecException
   */
  public static RSAPrivateKey getPrivateKey() {
    try {
      byte[] keyBytes = decoder.decode(getKeyString(PRIVATE_KEY_NAME, PRIVATE_FILENAME));
      PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
      return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    } catch (InvalidKeySpecException e) {
      LOGGER.error("getPrivateKey()#" + e.getMessage(), e);
    }
    return null;
  }

  /**
   * RSA公钥加密
   * 
   * @param content   等待加密的数据
   * @param publicKey RSA 公钥 if null then getPublicKey()
   * @return 加密后的密文(16进制的字符串)
   */
  public static String encryptByPublic(byte[] content, PublicKey publicKey) {
    if (publicKey == null) {
      publicKey = getPublicKey();
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      // 该密钥能够加密的最大字节长度
      int splitLength = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8 - 11;
      byte[][] arrays = splitBytes(content, splitLength);
      StringBuffer stringBuffer = new StringBuffer();
      for (byte[] array : arrays) {
        stringBuffer.append(bytesToHexString(cipher.doFinal(array)));
      }
      return stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("encrypt()#NoSuchAlgorithmException", e);
    } catch (NoSuchPaddingException e) {
      LOGGER.error("encrypt()#NoSuchPaddingException", e);
    } catch (InvalidKeyException e) {
      LOGGER.error("encrypt()#InvalidKeyException", e);
    } catch (BadPaddingException e) {
      LOGGER.error("encrypt()#BadPaddingException", e);
    } catch (IllegalBlockSizeException e) {
      LOGGER.error("encrypt()#IllegalBlockSizeException", e);
    }
    return null;
  }

  /**
   * RSA私钥加密
   * 
   * @param content    等待加密的数据
   * @param privateKey RSA 私钥 if null then getPrivateKey()
   * @return 加密后的密文(16进制的字符串)
   */
  public static String encryptByPrivate(byte[] content, PrivateKey privateKey) {
    if (privateKey == null) {
      privateKey = getPrivateKey();
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, privateKey);
      // 该密钥能够加密的最大字节长度
      int splitLength = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8 - 11;
      byte[][] arrays = splitBytes(content, splitLength);
      StringBuffer stringBuffer = new StringBuffer();
      for (byte[] array : arrays) {
        stringBuffer.append(bytesToHexString(cipher.doFinal(array)));
      }
      return stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("encrypt()#NoSuchAlgorithmException", e);
    } catch (NoSuchPaddingException e) {
      LOGGER.error("encrypt()#NoSuchPaddingException", e);
    } catch (InvalidKeyException e) {
      LOGGER.error("encrypt()#InvalidKeyException", e);
    } catch (BadPaddingException e) {
      LOGGER.error("encrypt()#BadPaddingException", e);
    } catch (IllegalBlockSizeException e) {
      LOGGER.error("encrypt()#IllegalBlockSizeException", e);
    }
    return null;
  }

  /**
   * RSA私钥解密
   * 
   * @param content    等待解密的数据
   * @param privateKey RSA 私钥 if null then getPrivateKey()
   * @return 解密后的明文
   */
  public static String decryptByPrivate(String content, PrivateKey privateKey) {
    if (privateKey == null) {
      privateKey = getPrivateKey();
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      // 该密钥能够加密的最大字节长度
      int splitLength = ((RSAPrivateKey) privateKey).getModulus().bitLength() / 8;
      byte[] contentBytes = hexStringToBytes(content);
      byte[][] arrays = splitBytes(contentBytes, splitLength);
      StringBuffer stringBuffer = new StringBuffer();
      String sTemp = null;
      for (byte[] array : arrays) {
        stringBuffer.append(new String(cipher.doFinal(array)));
      }
      return stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("encrypt()#NoSuchAlgorithmException", e);
    } catch (NoSuchPaddingException e) {
      LOGGER.error("encrypt()#NoSuchPaddingException", e);
    } catch (InvalidKeyException e) {
      LOGGER.error("encrypt()#InvalidKeyException", e);
    } catch (BadPaddingException e) {
      LOGGER.error("encrypt()#BadPaddingException", e);
    } catch (IllegalBlockSizeException e) {
      LOGGER.error("encrypt()#IllegalBlockSizeException", e);
    }
    return null;
  }

  /**
   * RSA公钥解密
   * 
   * @param content   等待解密的数据
   * @param publicKey RSA 公钥 if null then getPublicKey()
   * @return 解密后的明文
   */
  public static String decryptByPublic(String content, PublicKey publicKey) {
    if (publicKey == null) {
      publicKey = getPublicKey();
    }
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, publicKey);
      // 该密钥能够加密的最大字节长度
      int splitLength = ((RSAPublicKey) publicKey).getModulus().bitLength() / 8;
      byte[] contentBytes = hexStringToBytes(content);
      byte[][] arrays = splitBytes(contentBytes, splitLength);
      StringBuffer stringBuffer = new StringBuffer();
      String sTemp = null;
      for (byte[] array : arrays) {
        stringBuffer.append(new String(cipher.doFinal(array)));
      }
      return stringBuffer.toString();
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("encrypt()#NoSuchAlgorithmException", e);
    } catch (NoSuchPaddingException e) {
      LOGGER.error("encrypt()#NoSuchPaddingException", e);
    } catch (InvalidKeyException e) {
      LOGGER.error("encrypt()#InvalidKeyException", e);
    } catch (BadPaddingException e) {
      LOGGER.error("encrypt()#BadPaddingException", e);
    } catch (IllegalBlockSizeException e) {
      LOGGER.error("encrypt()#IllegalBlockSizeException", e);
    }
    return null;
  }

  /**
   * 根据限定的每组字节长度，将字节数组分组
   * 
   * @param bytes       等待分组的字节组
   * @param splitLength 每组长度
   * @return 分组后的字节组
   */
  public static byte[][] splitBytes(byte[] bytes, int splitLength) {
    // bytes与splitLength的余数
    int remainder = bytes.length % splitLength;
    // 数据拆分后的组数，余数不为0时加1
    int quotient = remainder != 0 ? bytes.length / splitLength + 1 : bytes.length / splitLength;
    byte[][] arrays = new byte[quotient][];
    byte[] array = null;
    for (int i = 0; i < quotient; i++) {
      // 如果是最后一组（quotient-1）,同时余数不等于0，就将最后一组设置为remainder的长度
      if (i == quotient - 1 && remainder != 0) {
        array = new byte[remainder];
        System.arraycopy(bytes, i * splitLength, array, 0, remainder);
      } else {
        array = new byte[splitLength];
        System.arraycopy(bytes, i * splitLength, array, 0, splitLength);
      }
      arrays[i] = array;
    }
    return arrays;
  }

  /**
   * 将字节数组转换成16进制字符串
   * 
   * @param bytes 即将转换的数据
   * @return 16进制字符串
   */
  public static String bytesToHexString(byte[] bytes) {
    StringBuffer sb = new StringBuffer(bytes.length);
    String temp = null;
    for (int i = 0; i < bytes.length; i++) {
      temp = Integer.toHexString(0xFF & bytes[i]);
      if (temp.length() < 2) {
        sb.append(0);
      }
      sb.append(temp);
    }
    return sb.toString();
  }

  /**
   * 将16进制字符串转换成字节数组
   * 
   * @param hex 16进制字符串
   * @return byte[]
   */
  public static byte[] hexStringToBytes(String hex) {
    int len = (hex.length() / 2);
    hex = hex.toUpperCase();
    byte[] result = new byte[len];
    char[] chars = hex.toCharArray();
    for (int i = 0; i < len; i++) {
      int pos = i * 2;
      result[i] = (byte) (toByte(chars[pos]) << 4 | toByte(chars[pos + 1]));
    }
    return result;
  }

  /**
   * 将char转换为byte
   * 
   * @param c char
   * @return byte
   */
  private static byte toByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }

}
