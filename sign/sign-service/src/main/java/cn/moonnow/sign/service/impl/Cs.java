package cn.moonnow.sign.service.impl;

import cn.moonnow.tool.util.ToolUtil;

public class Cs {

  public static void main(String[] args) throws Exception {
//    String s = "test";
//    String c1 = RSAUtilV2.encryptByPublic(s.getBytes(), null);
//    String m1 = RSAUtilV2.decryptByPrivate(c1, null);
//    String c2 = RSAUtilV2.encryptByPrivate(s.getBytes(), null);
//    String m2 = RSAUtilV2.decryptByPublic(c2, null);
//    System.out.println(c1);
//    System.out.println(m1);
//    System.out.println(c2);
//    System.out.println(m2);
//    System.out.println(ToolUtil.encodeBase64("胡师傅给你"));
//    System.out.println(ToolUtil.decodeBase64("6IOh5biI5YKF57uZ5L2g"));
//    System.out.println(ToolUtil.getAESKey("qweqweqweqweqweqweqweqweqweqweqweqweqwe"));
//    System.out.println(ToolUtil.getAESKey("qweqweqweqweqweqweqweqweqweqweqweqweqwe"));
//    System.out.println(ToolUtil.getAESKey(null));
//    3Kf789Qd49pPRDPMGTwePg==
//    System.out.println(ToolUtil.encodeAES("你是福建打工女教师转给你", "Iq93hoSYoxOB+RzRz00OIQ=="));
//    System.out.println(ToolUtil.decodeAES("+3DSnA7/ORO+EFkZfDE0WL8urkhdZnB9Ni8CQA6EtUHJJpla7GtjBraNpTjYH4+L", "Iq93hoSYoxOB+RzRz00OIQ=="));
//    System.out.println(ToolUtil.getRSAKey(null));
//    String pr = "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";
//    String pu = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAhgBFTNY2Vo6htdbSm57qNLNC5MLpBBQddbTWGdMUUhVlL8sM6qvRdVnuFU5ok9CVVhwSO+0RePuZt7g2D7cc8JO0BCLNpG4sn7+VAquwAWXpgadBy1aUg0Fr20Qvb6yqRwnMydW7T+UmvLD/zsXuJ8udjSPcInqtjwCpnu9xoPtgF/qZ0GCfXjuhvlvIVfzVdEFjJKygJskWO18EfE4bWPH2lFB9sv8/1WidXLn50v5RCOlagWkg5otPjP2xnXoU1UF+EvhbBBJ7QjPs1ID6Oh5guC07UWhnGmglDV2BVpTIyfFKe1wVlZNwhoU/tZPAKiygyac39SU4kl+xhkzoi/TSnBIxGtVeB/D55HxOL88IyRSa0/wjkpPItt8cEwQZCe0bjP/C1aMesmlusKD224BqhkqnxLcoQ07ptraIRvmolONvv4i8hX2xgOYhPBeBviSUFyq7TgoTvHNAZkTLK0WdjW+BNg1WH3jMcgZ9NMMd6hWkixe5jbl2g9fc42+Ic5rWdRZqe2ZIxnfUEdRWE8NB56w7fABC8Ba+uiL3pWYpOQhsW9ggok+a2n8yFnhr9mVtyxCnCdhBZq56AMvU3+tj3/yO/nn4Hk7oBeMc3q76J9yj5fSvwdEdaGhO1nj69uTL0qdT4UTGnCXGLLpCqtMuNvBg8S7Yr63iSHNiK/8CAwEAAQ==";
//    System.out.println(ToolUtil.encodeRSAByPrivateKey("Iq93hoSYoxOB+RzRz00OIQ==", pr));
//    String aa = "QW2GS47AY4CuOUOy629yorkshh5yXAQXqa4QtyMkYLmu0LhBDrx7ya+2MG/1XwaO6tHxfAbOcQPzoGXUDhDabHoIE9uHdGzNzLY9fE2uibakGnTr2XzlKRxFDZ597HJXoWuI3jDx65Jb4LAF+SRXK9nWQuamVCGMX8SgtelY3IdGBqvqFt5hAuDxR3FFhS4Du1ZdZrUI/zLpMQ1X5Nzi/yhnGuyUv5K8PAE0h3+mrNZdCDT740jHoftxwdvTUvKS6MjPnl3OS5OIfPe4xWfKfzNU/jpvkxo+z8k/0JCS3bKivHSEPufJ0BbNJdXrqvNH75doJDBV0+Op+vVkmhOuhRUT0AwtNHy1kWKyKnGzWdfDFHEE9J0mxJNgL6aZ1Ah5QZGWMWm1Q05OlvcLC8GYzNLppYp/Q3wUhkNZB6pWgL6zyX3MFfUgXH/z7iSyqqGvgZoFACvHHwALUrH1mNlglw0N+jXF7XsiEU+xY/dXy+HbN6p11hWEBJIvEK12ysXt2ZxdT3NlMX5jZcXtaKWpXkzivuYPh4uY0Swtm3T1x1QAHYK8ibZL40DSrQbhkHxukzxLQ1NS6zDMicsA0+AtpA1vSKwRo5+OeFCWXjm/Qoa63opx0pcjyEyx0E0slqDIPT9XBHMwxLjsmBaqsjDZSe3jcWfbxzd3kdB+JpM6yjc=";
//    System.out.println(ToolUtil.decodeRSAByPublicKey(aa, pu));
//    System.out.println(ToolUtil.encodeRSAByPublicKey("Iq93hoSYoxOB+RzRz00OIQ==", pu));
//    String bb = "H38E35cE+1aMwrdc/rvY83u0kXWxqJA2DIQqSkcrGtrCwIHYBPwsi5tUGac/1oarnDqH89Sy0nDSvWxuVx6vw+xjBBq/TzCg/LgwWptXGN+DpFB/YuYFL9/pZRjylWFTf+6ZVnF+zyHb6h2fT7it/DcOCXrWbeAepfK1xIO//8U1/jg/1X0vrubXqtwDqpdj52fxljJsb11NO7BN+6bti0IvDy0YGdELDi4iq01O1j8zhdgpshf0aSU9YDrbGfp51qKg7ianwt6xIdiIkY7VIBYzMnQmjr3IiifJGZyF0x9vzCXYPVmrv9xTCckkUqSP0v1PqMBxqEAwOFNjizMq26lOa77d2v9eI2AjOTLBTRShLG+UlDNkxiDzN/1mrHvbvP3eqn8w8QjuwIsT7gS3Qk6dwb9XUK9sL6TizgR1R2hdpIyeSlenyY5LjN7qyHc4h7zD0OEPZVqjlSU3mkF9Qi7HUtpwWYar+DzJq8MhG/t3rl3O8b7lA05IztSHgcJIIYyDK04gcFQtKN1i8ifheyqolwg5k9uhqYv9LvjGiY2+DVWwZIyHXivfJ3u0PbRyH0XLhY5Icy1lE00CUej/MlcF73oqqaKFt12jAExKWwLgi+EP/8Uuhd9OYQf9cuoP9LEzVthKJgZNwXqvJnaaIgTjwu+eY8hpM0ETW40rr9w=";
//    System.out.println(ToolUtil.decodeRSAByPrivateKey(aa, pr));
//    System.out.println(ToolUtil.encodeAES("qwert", ToolUtil.AES_KEY));
//    String aa = "mWaMsahIr+RM0QudkQ2jJw==";
//    System.out.println(ToolUtil.decodeAES(aa, ToolUtil.AES_KEY));
  }

//  /**
//   * 生成key，作为加密和解密密钥且只有密钥相同解密加密才会成功
//   * 
//   * @return
//   */
//  public static Key createKey() {
//
//    try {
//      // 生成key
//      KeyGenerator keyGenerator;
//      // 构造密钥生成器，指定为AES算法,不区分大小写
//      keyGenerator = KeyGenerator.getInstance("AES");
//      // 生成一个128位的随机源,根据传入的字节数组
//      keyGenerator.init(128);
//      // 产生原始对称密钥
//      SecretKey secretKey = keyGenerator.generateKey();
//      // 获得原始对称密钥的字节数组
//      byte[] keyBytes = secretKey.getEncoded();
//      // key转换,根据字节数组生成AES密钥
//      Key key = new SecretKeySpec(keyBytes, "AES");
//      return key;
//    } catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//      return null;
//    }
//  }
//  
//  /**加密
//   * @param context 需要加密的明文
//   * @param key 加密用密钥
//   * @return
//   */
//  public static byte[] jdkAES(String context, Key key) {
//    try {
// 
//      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//      
//      cipher.init(Cipher.ENCRYPT_MODE, key);
//      //将加密并编码后的内容解码成字节数组
//      byte[] result = cipher.doFinal(context.getBytes());
// 
////      System.out.println("jdk aes:" + Base64.encode(result));
// 
//      return result;
// 
//    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
//        | BadPaddingException e) {
//      e.printStackTrace();
//      return null;
//    }
//  }
//  
//  /** 解密
//   * @param result 加密后的密文byte数组
//   * @param key 解密用密钥
//   */
//  public static void decrypt(byte[] result, Key key) {
//    
//    Cipher cipher;
//    try {
//      
//      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//      //初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
//      cipher.init(Cipher.DECRYPT_MODE, key);
//      result = cipher.doFinal(result);
// 
//    } catch (NoSuchAlgorithmException e) {
//      e.printStackTrace();
//    } catch (NoSuchPaddingException e) {
//      e.printStackTrace();
//    } catch (InvalidKeyException e) {
//      e.printStackTrace();
//    } catch (IllegalBlockSizeException e) {
//      e.printStackTrace();
//    } catch (BadPaddingException e) {
//      e.printStackTrace();
//    }
// 
//    System.out.println("jdk aes desrypt:" + new String(result));
//  }

}
