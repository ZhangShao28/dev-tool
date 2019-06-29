package cn.moonnow.tool.persistent.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.moonnow.tool.context.ToolContextData;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 持久化保存方法 AOP 拦截器</br>
 * 添加数据版本号和数据创建者信息
 */
@Log4j2
@Aspect
@Component
public class PersistentSaveAspect {

  @Pointcut("execution(public * cn.moonnow..persistent..*.save*(..))")
  public void SaveAddVersionsAccount() {
  }

  @Before("SaveAddVersionsAccount()")
  public void doBefore(JoinPoint joinPoint) throws Exception {
    if (log.isInfoEnabled()) {
      log.debug(ToolUtil.getLog("PersistentSaveAspect"));
    }
    try {
      for (Object o : joinPoint.getArgs()) {
        if (o != null) {
          LinkedHashSet<Method> oSetMethods = new LinkedHashSet<Method>();
          Class<?> oclass = o.getClass();
          while (oclass != null) {
            oSetMethods.addAll(Arrays.asList(oclass.getMethods()));
            oclass = oclass.getSuperclass();
          }
          for (Method sM : oSetMethods) {
            if ("setVersions".equals(sM.getName())) {
              sM.invoke(o, 1);
              continue;
            } else if ("setCreateId".equals(sM.getName())) {
              sM.invoke(o, ToolContextData.getCurrentLoginAccountInfo() != null && ToolContextData.getCurrentLoginAccountInfo().getAccountId() != null ? ToolContextData.getCurrentLoginAccountInfo().getAccountId() : "accountId");
              continue;
            } else if ("setCreateName".equals(sM.getName())) {
              sM.invoke(o, ToolContextData.getCurrentLoginAccountInfo() != null && ToolContextData.getCurrentLoginAccountInfo().getName() != null ? ToolContextData.getCurrentLoginAccountInfo().getName() : "name");
              continue;
            } else if ("setCreateTimes".equals(sM.getName())) {
              sM.invoke(o, System.currentTimeMillis());
              continue;
            }
          }
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

}
