package cn.moonnow.tool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 工具类
 */
public final class ToolUtil {

  /**
   * 自定义条件查询 并且 等于
   */
  public static final String AND_EQ = "Andeq";

  /**
   * 自定义条件查询 并且 包含
   */
  public static final String AND_LIKE = "Andlike";

  /**
   * 自定义条件查询 并且 在列表
   */
  public static final String AND_IN = "Andin";

  /**
   * 自定义条件查询 并且 不等于
   */
  public static final String AND_NE = "Andne";

  /**
   * 自定义条件查询 并且 不在列表
   */
  public static final String AND_NIN = "Andnin";

  /**
   * 自定义条件查询 并且 大于
   */
  public static final String AND_G = "Andg";

  /**
   * 自定义条件查询 并且 小于
   */
  public static final String AND_L = "Andl";

  /**
   * 自定义条件查询 并且 大于等于
   */
  public static final String AND_GE = "Andge";

  /**
   * 自定义条件查询 并且 小于等于
   */
  public static final String AND_LE = "Andle";

  /**
   * 自定义条件查询 或 等于
   */
  public static final String OR_EQ = "Oreq";

  /**
   * 自定义条件查询 或 包含
   */
  public static final String OR_LIKE = "Orlike";

  /**
   * 自定义条件查询 或 在列表
   */
  public static final String OR_IN = "Orin";

  /**
   * 自定义条件查询 或 不等于
   */
  public static final String OR_NE = "Orne";

  /**
   * 自定义条件查询 或 不在列表
   */
  public static final String OR_NIN = "Ornin";

  /**
   * 自定义条件查询 或 大于
   */
  public static final String OR_G = "Org";

  /**
   * 自定义条件查询 或 小于
   */
  public static final String OR_L = "Orl";

  /**
   * 自定义条件查询 或 大于等于
   */
  public static final String OR_GE = "Orge";

  /**
   * 自定义条件查询 或 小于等于
   */
  public static final String OR_LE = "Orle";

  /**
   * 自定义条件查询(搜索专用) 并且 包含
   */
  public static final String AND_KEY_LIKE = "AndKeyLike";

  /**
   * 自定义条件查询(搜索专用) 或 包含
   */
  public static final String OR_KEY_LIKE = "OrKeyLike";

  /**
   * 字符串是null或空字符串或全空格字符串 返回true
   */
  public static boolean isNullStr(String str) {
    return str == null ? true : (str.trim().length() == 0 ? true : false);
  }

  /**
   * 字符串非null或空字符串或全空格字符串 返回true
   */
  public static boolean isNotNullStr(String str) {
    return str != null && str.trim().length() != 0;
  }

  /**
   * 36位uuid
   */
  public static String getUUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * 32位uuid
   */
  public static String getUUID32() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /**
   * 集合非null或空集合 返回true
   */
  public static boolean isNotEmpty(Collection<?> arg) {
    return arg != null && (!arg.isEmpty());
  }

  /**
   * 集合是null或空集合 返回true
   */
  public static boolean isEmpty(Collection<?> arg) {
    return arg == null ? true : arg.isEmpty();
  }

  /**
   * Map非null或空Map 返回true
   */
  public static boolean isNotEmpty(Map<?, ? extends Object> map) {
    return map != null && (!map.isEmpty());
  }

  /**
   * Map是null或空Map 返回true
   */
  public static boolean isEmpty(Map<?, ? extends Object> map) {
    return map == null ? true : map.isEmpty();
  }

  /**
   * 对象的成员变量值都是null 返回true
   */
  public static boolean isNullEntityAllFieldValue(Object o) {
    try {
      // 取得目标对象的字段集合以及方法集合
      LinkedHashSet<Field> tFields = new LinkedHashSet<Field>();
      LinkedHashSet<Method> getMethods = new LinkedHashSet<Method>();
      Class<?> ppclass = o.getClass();
      while (ppclass != null) {
        tFields.addAll(Arrays.asList(ppclass.getDeclaredFields()));
        getMethods.addAll(Arrays.asList(ppclass.getMethods()));
        ppclass = ppclass.getSuperclass();
      }
      if (tFields != null && tFields.size() >= 1) {
        for (Field tField : tFields) {
          // 根据成员变量名定义get方法名
          String getMethodStr = "get" + tField.getName().substring(0, 1).toUpperCase() + tField.getName().substring(1);
          // 标识成员变量是否有get方法
          boolean isGetMethodStr = false;
          // 目标对象成员变量有get方法 改变isSetMethodStr变量的值为true
          for (Method getMethod : getMethods) {
            if (getMethodStr.equals(getMethod.getName())) {
              isGetMethodStr = true;
              break;
            }
          }
          // 目标对象成员变量有get方法 就进行非空判断
          if (isGetMethodStr) {
            Method tM = o.getClass().getMethod(getMethodStr);
            if (tM.invoke(o) != null) {
              return false;
            }
          }
        }
      }
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * Base64加密
   */
  public static String encodeBase64(String str) throws UnsupportedEncodingException {
    return new String(Base64.encodeBase64(str.getBytes("UTF-8")));
  }

  /**
   * Base64解密
   */
  public static String decodeBase64(String str) throws UnsupportedEncodingException {
    return new String(Base64.decodeBase64(str.getBytes("UTF-8")));
  }

  /**
   * AES密钥
   */
  public static String getAESKey(String str) {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      if (isNullStr(str)) {
        keyGenerator.init(128);
      } else {
        keyGenerator.init(128, new SecureRandom(str.getBytes("UTF-8")));
      }
      SecretKey secretKey = keyGenerator.generateKey();
      return new String(Base64.encodeBase64(secretKey.getEncoded()), "UTF-8");
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * AES加密
   */
  public static String encodeAES(String str, String key) {
    try {
      Security.addProvider(new BouncyCastleProvider());
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
      cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")), "AES"));
      return new String(Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * AES解密
   */
  public static String decodeAES(String str, String key) {
    try {
      Security.addProvider(new BouncyCastleProvider());
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")), "AES"));
      return new String(cipher.doFinal(Base64.decodeBase64(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * RSA密钥
   */
  public static LinkedHashMap<String, String> getRSAKey(String str) {
    try {
      LinkedHashMap<String, String> rHashMap = new LinkedHashMap<String, String>();
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      if (isNullStr(str)) {
        keyPairGenerator.initialize(4096, new SecureRandom());
      } else {
        keyPairGenerator.initialize(4096, new SecureRandom(str.getBytes("UTF-8")));
      }
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
      RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
      rHashMap.put("privateKey", new String(Base64.encodeBase64(privateKey.getEncoded()), "UTF-8"));
      rHashMap.put("publicKey", new String(Base64.encodeBase64(publicKey.getEncoded()), "UTF-8"));
      return rHashMap;
    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * RSA私钥加密
   */
  public static String encodeRSAByPrivateKey(String str, String key) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")))));
      return new String(Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * RSA公钥解密
   */
  public static String decodeRSAByPublicKey(String str, String key) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")))));
      return new String(cipher.doFinal(Base64.decodeBase64(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * RSA公钥加密
   */
  public static String encodeRSAByPublicKey(String str, String key) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")))));
      return new String(Base64.encodeBase64(cipher.doFinal(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * RSA私钥解密
   */
  public static String decodeRSAByPrivateKey(String str, String key) {
    try {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(key.getBytes("UTF-8")))));
      return new String(cipher.doFinal(Base64.decodeBase64(str.getBytes("UTF-8"))), "UTF-8");
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 字节输入流转字符串
   */
  public static String getStrFromInputStream(InputStream inputStream) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(inputStream));
      while ((str2 = reader.readLine()) != null) {
        str1.append(str2).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
    return str1.toString();
  }

}
