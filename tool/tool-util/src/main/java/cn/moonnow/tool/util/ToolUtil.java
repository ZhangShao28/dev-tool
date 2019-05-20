package cn.moonnow.tool.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;

public final class ToolUtil {

  // 判断是否是空字符串
  // 字符串是null或空字符串或全空格字符串 返回true
  // 返回true是无效字符串 反之返回false
  public static boolean isNullStr(String str) {
    return str == null ? true : (str.trim().length() == 0 ? true : false);
  }

  public static boolean isNotNullStr(String str) {
    return str != null && str.trim().length() != 0;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString();
  }

  public static String getUUID32() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  public static boolean isNotEmpty(Collection<?> arg) {
    return arg != null && (!arg.isEmpty());
  }

  public static boolean isEmpty(Collection<?> arg) {
    return arg == null ? true : arg.isEmpty();
  }

  public static boolean isNotEmpty(Map<?, ? extends Object> map) {
    return map != null && (!map.isEmpty());
  }

  public static boolean isEmpty(Map<?, ? extends Object> map) {
    return map == null ? true : map.isEmpty();
  }

  // 传入目标对象
  // 判断目标对象的成员变量值是否都为空
  // 都为空返回true 否则返回false
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

}
