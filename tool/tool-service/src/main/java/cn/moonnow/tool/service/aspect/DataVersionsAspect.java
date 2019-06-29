//package cn.moonnow.tool.service.aspect;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import cn.moonnow.tool.util.ToolUtil;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@Aspect
//@Component
//public class DataVersionsAspect {
//
//  @Pointcut("execution(public * cn.moonnow..service..*.*(..))")
//  public void AddUpVersionsAccount() {
//  }
//
//  @Before("AddUpVersionsAccount()")
//  public void doBefore(JoinPoint joinPoint) {
//    if (log.isInfoEnabled()) {
//      log.debug(ToolUtil.getLog("DataVersionsAspect"));
//    }
//  }
//
//  @After("AddUpVersionsAccount()")
//  public void doAfter(JoinPoint joinPoint) {
//    if (log.isInfoEnabled()) {
//      log.debug(ToolUtil.getLog("DataVersionsAspect"));
//    }
//  }
//
//  @AfterReturning("AddUpVersionsAccount()")
//  public void doAfterReturning(JoinPoint joinPoint) {
//    if (log.isInfoEnabled()) {
//      log.debug(ToolUtil.getLog("DataVersionsAspect"));
//    }
//  }
//
//  @AfterThrowing("AddUpVersionsAccount()")
//  public void deAfterThrowing(JoinPoint joinPoint) {
//    if (log.isInfoEnabled()) {
//      log.debug(ToolUtil.getLog("DataVersionsAspect"));
//    }
//  }
//
//  @Around("AddUpVersionsAccount()")
//  public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
//    if (log.isInfoEnabled()) {
//      log.debug(ToolUtil.getLog("DataVersionsAspect"));
//    }
//    return joinPoint.proceed();
//  }
//
//}
