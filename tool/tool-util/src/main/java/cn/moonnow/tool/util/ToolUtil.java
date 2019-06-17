package cn.moonnow.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   * 代码生成全局配置
   */
  public static final LinkedHashMap<String, String> CONFIG_MAP = new LinkedHashMap<String, String>();

  static {
    // 配置代码生成全局缩进
    CONFIG_MAP.put("ci", "  ");
  }

  /**
   * 自定义条件查询</br>
   * 并且 等于
   */
  public static final String AND_EQ = "Andeq";

  /**
   * 自定义条件查询</br>
   * 并且 包含
   */
  public static final String AND_LIKE = "Andlike";

  /**
   * 自定义条件查询</br>
   * 并且 在列表
   */
  public static final String AND_IN = "Andin";

  /**
   * 自定义条件查询</br>
   * 并且 不等于
   */
  public static final String AND_NE = "Andne";

  /**
   * 自定义条件查询</br>
   * 并且 不在列表
   */
  public static final String AND_NIN = "Andnin";

  /**
   * 自定义条件查询</br>
   * 并且 大于
   */
  public static final String AND_G = "Andg";

  /**
   * 自定义条件查询</br>
   * 并且 小于
   */
  public static final String AND_L = "Andl";

  /**
   * 自定义条件查询</br>
   * 并且 大于等于
   */
  public static final String AND_GE = "Andge";

  /**
   * 自定义条件查询</br>
   * 并且 小于等于
   */
  public static final String AND_LE = "Andle";

  /**
   * 自定义条件查询</br>
   * 或 等于
   */
  public static final String OR_EQ = "Oreq";

  /**
   * 自定义条件查询</br>
   * 或 包含
   */
  public static final String OR_LIKE = "Orlike";

  /**
   * 自定义条件查询</br>
   * 或 在列表
   */
  public static final String OR_IN = "Orin";

  /**
   * 自定义条件查询</br>
   * 或 不等于
   */
  public static final String OR_NE = "Orne";

  /**
   * 自定义条件查询</br>
   * 或 不在列表
   */
  public static final String OR_NIN = "Ornin";

  /**
   * 自定义条件查询</br>
   * 或 大于
   */
  public static final String OR_G = "Org";

  /**
   * 自定义条件查询</br>
   * 或 小于
   */
  public static final String OR_L = "Orl";

  /**
   * 自定义条件查询</br>
   * 或 大于等于
   */
  public static final String OR_GE = "Orge";

  /**
   * 自定义条件查询</br>
   * 或 小于等于
   */
  public static final String OR_LE = "Orle";

  /**
   * 自定义条件查询</br>
   * (搜索专用) 并且 包含
   */
  public static final String AND_KEY_LIKE = "AndKeyLike";

  /**
   * 自定义条件查询</br>
   * (搜索专用) 或 包含
   */
  public static final String OR_KEY_LIKE = "OrKeyLike";

  /**
   * 日志标志
   */
  public static final String LOG = "======= param ======>>> ";

  /**
   * 我要做什么
   */
  public static final String ACTION = "action=";

  /**
   * 我要做什么之</br>
   * 保存
   */
  public static final String ACTION_SAVE = "save";

  /**
   * 我要做什么之</br>
   * 批量保存
   */
  public static final String ACTION_BATCH_SAVE = "batch_save";

  /**
   * 我要做什么之</br>
   * 修改
   */
  public static final String ACTION_UPDATE = "update";

  /**
   * 我要做什么之</br>
   * 批量修改
   */
  public static final String ACTION_BATCH_UPDATE = "batch_update";

  /**
   * 我要做什么之</br>
   * 删除
   */
  public static final String ACTION_REMOVE = "remove";

  /**
   * 我要做什么之</br>
   * 批量删除
   */
  public static final String ACTION_BATCH_REMOVE = "batch_remove";

  /**
   * 我要做什么之</br>
   * 通过主键获取数据
   */
  public static final String ACTION_GET_BY_PK = "get_by_pk";

  /**
   * 我要做什么之</br>
   * 获取所有数据
   */
  public static final String ACTION_GET_ALL = "get_all";

  /**
   * 我要做什么之</br>
   * 分页获取数据
   */
  public static final String ACTION_PAGING = "paging";

  /**
   * 我要做什么之</br>
   * 通过主键获取展示数据
   */
  public static final String ACTION_GET_VO_BY_PK = "get_vo_by_pk";

  /**
   * 我要做什么之</br>
   * 获取所有展示数据
   */
  public static final String ACTION_GET_ALL_VO = "get_all_vo";

  /**
   * 我要做什么之</br>
   * 分页获取展示数据
   */
  public static final String ACTION_PAGING_VO = "paging_vo";

  /**
   * RSA 私钥
   */
  public static final String PRIVATE_KEY = "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";

  /**
   * RSA 公钥
   */
  public static final String PUBLIC_KEY = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAmN3JifQM+oMe+PeXFD/WoZm046XfXt0aWg30NsOFVyasT8xzWLKrNn34wUPGU2sVujJJfjKY6VnmGqHIAH0pmi9WtgD7nZgbNIl85LkJTFgEXJm6JILe9jTBTMI1qodlwnbXWCUIibTwQE/YoljHceWCJP7MFJT6u/hbi1HJlGet9C4fzDZ6znNRY6eBbWNBDHHgbqmx9q3dKdAUCGpHDQ5eVhe6CeCwh6bAtfopV+v1XStwOC0J8Gxk5sxVYltEI6WdRdAv+RyZ2ueUPAqp78uTF5Uvtx6VRX5PTcPe/G1VPTndMjM657Gy88yZJ4KIG8Ta7hHJ8CTZ0Fri5U+OAua2/CKmsPbDezSlixQu7L49+7eU867Lc6ZxhcLDai4d4o8kUK2Yh9TIerpFzSHQG2MUVhKcm3h2Oze89sdiDbeTlPVb3z8KSG8h3t+bYIjqDU9by8/3GAk32HJVIug/9z631GLu1RS1m7Ih6gJe/5Zmj6y5E2iGyOT+37j54pOXwi+KA53JIzdMdbVNSO5H+qrJGVl68RqNYAcCjU7FZ70buEpS1C+dEiAMKxiAahtMClAjr5p0e+v6KB6ua/opUhojg6zPilJ390a+s6+A4XPzLaUwc5xlWYTzpHwJz9XA2ExTDHujJ/HkUAg3FAfh2i2ry37M+eK9qYhSgHaO63kCAwEAAQ==";

  /**
   * AES 密钥
   */
  public static final String AES_KEY = "Xy7qBgpcdXlL4j5hphpKwA==";

  /**
   * 字符串是null或空字符串或全空格字符串</br>
   * 返回true
   */
  public static boolean isNullStr(String str) {
    return str == null ? true : (str.trim().length() == 0 ? true : false);
  }

  /**
   * 字符串非null或空字符串或全空格字符串</br>
   * 返回true
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
   * 集合非null或空集合</br>
   * 返回true
   */
  public static boolean isNotEmpty(Collection<?> arg) {
    return arg != null && (!arg.isEmpty());
  }

  /**
   * 集合是null或空集合</br>
   * 返回true
   */
  public static boolean isEmpty(Collection<?> arg) {
    return arg == null ? true : arg.isEmpty();
  }

  /**
   * Map非null或空Map</br>
   * 返回true
   */
  public static boolean isNotEmpty(Map<?, ? extends Object> map) {
    return map != null && (!map.isEmpty());
  }

  /**
   * Map是null或空Map</br>
   * 返回true
   */
  public static boolean isEmpty(Map<?, ? extends Object> map) {
    return map == null ? true : map.isEmpty();
  }

  /**
   * 对象是null或对象的成员变量值都是null</br>
   * 返回true
   */
  public static boolean isNullEntityAllFieldValue(Object o) {
    try {
      if (o != null) {
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

  /**
   * 拼接日志
   */
  public static String getLog(String str) {
    return new StringBuilder("=======================>>> ").append(str).append(".").append(new Exception().getStackTrace()[1].getMethodName()).append(" <<<=======================").toString();
  }

  /**
   * 传入数据库表字符串返回表名
   */
  public static String getDtNameFromDtStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

  /**
   * 从sql文件绝对路径获取表信息字符串
   */
  public static String getTableInfoStrFromSqlPath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    String str3 = null;
    File file = new File(str);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      while ((str2 = reader.readLine()) != null) {
        str1.append(str2).append("\n");
      }
      str3 = str1.toString().substring(str1.toString().indexOf("CREATE TABLE"));
      str3 = str3.substring(0, str3.indexOf(";") + 1);
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
    return str3;
  }

  /**
   * 传入数据库表字符串返回表名注释
   */
  public static String getDtNameAnnotationFromDtStr(String str) {
    return str.substring(str.indexOf("='") + 2, str.indexOf("';")).trim();
  }

  /**
   * 传入字符串(以"_"分隔)以及大小写控制变量</br>
   * 返回首字母大写或小写驼峰</br>
   * true返回大写</br>
   * false返回小写
   */
  public static String getInitialCaseOrInitialLowercaseHumpFromStr(String str, Boolean initialCaseOrLowercase) {
    StringBuilder initialCase = new StringBuilder();
    StringBuilder initialLowercase = new StringBuilder();
    if (str.indexOf("_") == -1) {
      if (str.length() == 1) {
        initialCase.append(str.toUpperCase());
        initialLowercase.append(str.toLowerCase());
      } else {
        initialCase.append(str.substring(0, 1).toUpperCase());
        initialLowercase.append(str.substring(0, 1).toLowerCase());
        initialCase.append(str.substring(1).toLowerCase());
        initialLowercase.append(str.substring(1).toLowerCase());
      }
    } else {
      String str1 = str.substring(0, str.indexOf("_"));
      str = str.substring(str.indexOf("_") + 1);
      if (str1.length() == 1) {
        initialCase.append(str1.toUpperCase());
        initialLowercase.append(str1.toLowerCase());
      } else {
        initialCase.append(str1.substring(0, 1).toUpperCase());
        initialLowercase.append(str1.substring(0, 1).toLowerCase());
        initialCase.append(str1.substring(1).toLowerCase());
        initialLowercase.append(str1.substring(1).toLowerCase());
      }
      while (str.indexOf("_") != -1) {
        String str2 = str.substring(0, str.indexOf("_"));
        str = str.substring(str.indexOf("_") + 1);
        if (str2.length() == 1) {
          initialCase.append(str2.toUpperCase());
          initialLowercase.append(str2.toUpperCase());
        } else {
          initialCase.append(str2.substring(0, 1).toUpperCase());
          initialLowercase.append(str2.substring(0, 1).toUpperCase());
          initialCase.append(str2.substring(1).toLowerCase());
          initialLowercase.append(str2.substring(1).toLowerCase());
        }
      }
      if (str.length() == 1) {
        initialCase.append(str.toUpperCase());
        initialLowercase.append(str.toUpperCase());
      } else {
        initialCase.append(str.substring(0, 1).toUpperCase());
        initialLowercase.append(str.substring(0, 1).toUpperCase());
        initialCase.append(str.substring(1).toLowerCase());
        initialLowercase.append(str.substring(1).toLowerCase());
      }
    }
    if (initialCaseOrLowercase) {
      return initialCase.toString();
    } else {
      return initialLowercase.toString();
    }
  }

  /**
   * 传入数据库表字符串返回列map
   */
  public static LinkedHashMap<Integer, String> getColumnsMapFromDtStr(String str) {
    LinkedHashMap<Integer, String> columnsMap = new LinkedHashMap<Integer, String>();
    int code = 1;
    str = str.substring(str.indexOf("(") + 1);
    while (str.indexOf(",\n") != -1) {
      columnsMap.put(code, str.substring(0, str.indexOf(",\n") + 1).replaceAll("\n", "").trim());
      code++;
      str = str.substring(str.indexOf(",\n") + 1);
    }
    // 为了兼容win win换行符合linux不同
    while (str.indexOf(",\r\n") != -1) {
      columnsMap.put(code, str.substring(0, str.indexOf(",\r\n") + 2).replaceAll("\r\n", "").trim());
      code++;
      str = str.substring(str.indexOf(",\r\n") + 2);
    }
    columnsMap.put(code, str.substring(0, str.indexOf("\n)")).replaceAll("\n", "").replaceAll("\r", "").trim());
    return columnsMap;
  }

  /**
   * 传入数据库表列字符串返回列名
   */
  public static String getColumnsNameFromDtColumnsStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

  /**
   * 传入数据库表列字符串返回列名注释
   */
  public static String getColumnsNameAnnotationFromDtColumnsStr(String str) {
    if (-1 == str.indexOf("'")) {
      return str.trim();
    } else {
      str = str.substring(str.indexOf("'") + 1);
      str = str.substring(0, str.indexOf("'"));
      return str.trim();
    }
  }

  /**
   * 传入数据库表列字符串返回数据类型
   */
  public static String getDataTypeFromDtColumnsStr(String str) {
    if (-1 == str.indexOf(" ")) {
      return str.trim();
    } else {
      str = str.substring(str.indexOf(" ") + 1);
      if (-1 == str.indexOf(" ")) {
        str = str.substring(0, str.length() - 1);
        return str.trim();
      } else {
        str = str.substring(0, str.indexOf(" "));
        return str.trim();
      }
    }
  }

  /**
   * 传入数据库表列字符串返回是否为空
   */
  public static String getIsNullFromDtColumnsStr(String str) {
    if (-1 != str.indexOf("NOT NULL")) {
      return "n";
    }
    if (-1 != str.indexOf("DEFAULT NULL")) {
      return "y";
    }
    return "y";
  }

  /**
   * 传入数据库表列字符串返回主键列名
   */
  public static String getPrimaryKeyColumnNameFromDtColumnsStr(String str) {
    str = str.substring(str.indexOf("`") + 1);
    str = str.substring(0, str.indexOf("`"));
    return str.trim().toUpperCase();
  }

  /**
   * 传入数据库表数据类型字符串返回java数据类型
   */
  public static String getJavaDataTypeFromDtDataTypeStr(String str) {
    if (str.indexOf("varchar") != -1) {
      return "String";
    } else if (str.indexOf("char") != -1) {
      return "String";
    } else if (str.indexOf("bigint") != -1) {
      return "Long";
    } else if (str.indexOf("integer") != -1) {
      return "Integer";
    } else if (str.indexOf("int") != -1) {
      return "Integer";
    } else if (str.indexOf("decimal") != -1) {
      return "BigDecimal";
    } else if (str.indexOf("text") != -1) {
      return "String";
    } else {
      return "String";
    }
  }

  /**
   * 从配置参数得到jdbc实体类文件路径
   */
  public static String getJdbcEntityFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "entity/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "entity/" + initialCaseEntityName + ".java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到持久化接口类文件路径
   */
  public static String getIPersistentFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent/I" + initialCaseEntityName + "Persistent.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到jdbc持久化实现类文件路径
   */
  public static String getJdbcPersistentImplFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "persistent/impl/" + initialCaseEntityName + "PersistentImpl.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到服务接口类文件路径
   */
  public static String getIServiceFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "service/I" + initialCaseEntityName + "Service.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到服务实现类文件路径
   */
  public static String getServiceImplFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "service/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "service/impl/" + initialCaseEntityName + "ServiceImpl.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到控制器类文件路径
   */
  public static String getControllerFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "rest/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "rest/" + initialCaseEntityName + "Controller.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到查询类文件路径
   */
  public static String getQueryFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "query/" + initialCaseEntityName + "Query.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到VO类文件路径
   */
  public static String getVoFilePathStrFromConfig(String proPath, String proAllName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath + "interface/src/main/java/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + "vo/" + initialCaseEntityName + "VO.java";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到jdbc实体类包名
   */
  public static String getEntityPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "entity";
      return proAllName;
    }
    return "";
  }

  /**
   * 模板字符串替换
   */
  public static String renderString(String content, Map<String, String> map) {
    Set<Entry<String, String>> sets = map.entrySet();
    for (Entry<String, String> entry : sets) {
      String regex = "\\$\\{" + entry.getKey() + "\\}";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(content);
      content = matcher.replaceAll(entry.getValue());
    }
    return content;
  }

  /**
   * 从资源文件路径获取文件内容字符串
   */
  public static String getStrFromFileResourcesPath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(ToolUtil.class.getResourceAsStream(str)));
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

  /**
   * 传入数据库表sql 判断是否需要导入BigDecimal包
   */
  public static boolean getIsImportBigDecimalFromDtSqlStr(String str) {
    if (-1 != str.indexOf("decimal")) {
      return true;
    }
    return false;
  }

  /**
   * 传入文件内容字符串和文件绝对路径 生成文件
   */
  public static void getFileFromContentStrAndPath(String content, String path) {
    try {
      File f = new File(path);
      if (!f.exists()) {
        new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
      }
      FileOutputStream fop = new FileOutputStream(f);
      OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
      writer.append(content);
      writer.close();
      fop.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 从文件路径获取文件内容字符串
   */
  public static String getStrFromFilePath(String str) {
    StringBuilder str1 = new StringBuilder();
    String str2 = null;
    File file = new File(str);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
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

  /**
   * 从配置参数得到查询类包名
   */
  public static String getQueryPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "query";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到数据展示(VO)类包名
   */
  public static String getVOPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "vo";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到持久化接口类包名
   */
  public static String getIPersistentPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "persistent";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到jdbc持久化实现类包名
   */
  public static String getJdbcPersistentImplPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "persistent.impl";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到持久化实现类名称前缀
   */
  public static String getPersistentImplNamePrefixStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到服务接口类包名
   */
  public static String getIServicePackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "service";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到服务实现类包名
   */
  public static String getServiceImplPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "service.impl";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到服务实现类名称前缀
   */
  public static String getServiceImplNamePrefixStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到控制器类包名
   */
  public static String getControllerPackageNameStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "cn.moonnow.";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + ".";
        } else {
          break;
        }
      }
      proAllName = proAllName + "rest";
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到控制器url
   */
  public static String getControllerUrlStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  /**
   * 从配置参数得到业务类路径
   */
  public static String getBusinessPathStrFromConfig(String proAllName) {
    if (ToolUtil.isNotNullStr(proAllName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      proAllName = "";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proAllName = proAllName + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      return proAllName;
    }
    return "";
  }

  /**
   * 传入数据库表数据类型字符串返回Angular数据类型
   */
  public static String getAngularDataTypeFromDtDataTypeStr(String str) {
    if (str.indexOf("varchar") != -1) {
      return "string";
    } else if (str.indexOf("char") != -1) {
      return "string";
    } else if (str.indexOf("bigint") != -1) {
      return "number";
    } else if (str.indexOf("integer") != -1) {
      return "number";
    } else if (str.indexOf("int") != -1) {
      return "number";
    } else if (str.indexOf("decimal") != -1) {
      return "number";
    } else if (str.indexOf("text") != -1) {
      return "string";
    } else if (str.indexOf("date") != -1) {
      return "string";
    } else {
      return "string";
    }
  }

  /**
   * 从配置参数得到Vue服务类文件路径
   */
  public static String getVueServiceFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/api/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "Service.js";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到VueStore文件路径
   */
  public static String getVueStoreFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/store/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + "Store.js";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到Vue实体类文件路径
   */
  public static String getVueEntityFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/view/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialCaseEntityName + ".js";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到Vue查询类文件路径
   */
  public static String getVueQueryFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/view/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialCaseEntityName + "Query.js";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到VueVO类文件路径
   */
  public static String getVueVoFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName, String initialCaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName) && ToolUtil.isNotNullStr(initialCaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/view/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialCaseEntityName + "VO.js";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到VueView文件路径
   */
  public static String getVueViewFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/view/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + ".vue";
      return proPath;
    }
    return "";
  }

  /**
   * 从配置参数得到VueView样式文件路径
   */
  public static String getVueViewCssFilePathStrFromConfig(String proPath, String proAllName, String initialLowercaseEntityName) {
    if (ToolUtil.isNotNullStr(proPath) && ToolUtil.isNotNullStr(proAllName) && ToolUtil.isNotNullStr(initialLowercaseEntityName)) {
      LinkedHashMap<Integer, String> proAllNameList = new LinkedHashMap<Integer, String>();
      int proAllNameSort = 1;
      while (-1 != proAllName.indexOf("-")) {
        proAllNameList.put(proAllNameSort, proAllName.substring(0, proAllName.indexOf("-")));
        proAllName = proAllName.substring(proAllName.indexOf("-") + 1);
        proAllNameSort++;
      }
      proAllNameList.put(proAllNameSort, proAllName);
      if (!"/".equals(proPath.substring(proPath.length() - 1, proPath.length()))) {
        proPath = proPath + "/";
      }
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "-";
        } else {
          break;
        }
      }
      proPath = proPath.substring(0, proPath.length() - 1);
      proPath = proPath + "/vue/src/view/cn/moonnow/";
      for (int i = 1; i > 0; i++) {
        if (proAllNameList.containsKey(i)) {
          proPath = proPath + proAllNameList.get(i) + "/";
        } else {
          break;
        }
      }
      proPath = proPath + initialLowercaseEntityName + "/" + initialLowercaseEntityName + ".less";
      return proPath;
    }
    return "";
  }

}
