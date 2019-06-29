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
 * 持久化修改方法 AOP 拦截器</br>
 * 更新数据版本号和数据修改者信息
 */
@Log4j2
@Aspect
@Component
public class PersistentUpdateAspect {

  @Pointcut("execution(public * cn.moonnow..persistent..*.update*(..))")
  public void UpdateAddVersionsAccount() {
  }

  @Before("UpdateAddVersionsAccount()")
  public void doBefore(JoinPoint joinPoint) throws Exception {
    if (log.isInfoEnabled()) {
      log.debug(ToolUtil.getLog("PersistentUpdateAspect"));
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
              sM.invoke(o, (Integer) o.getClass().getMethod("getVersions").invoke(o) + 1);
              continue;
            } else if ("setModifyId".equals(sM.getName())) {
              sM.invoke(o, ToolContextData.getCurrentLoginAccountInfo() != null && ToolContextData.getCurrentLoginAccountInfo().getAccountId() != null ? ToolContextData.getCurrentLoginAccountInfo().getAccountId() : "accountId");
              continue;
            } else if ("setModifyName".equals(sM.getName())) {
              sM.invoke(o, ToolContextData.getCurrentLoginAccountInfo() != null && ToolContextData.getCurrentLoginAccountInfo().getName() != null ? ToolContextData.getCurrentLoginAccountInfo().getName() : "name");
              continue;
            } else if ("setModifyTimes".equals(sM.getName())) {
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
