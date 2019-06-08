package cn.moonnow.tool.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

//  @Value("${proconfig.cookies-times-effect}")
//  private Long cookiesTimesEffect;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (log.isInfoEnabled()) {
      log.debug(ToolUtil.getLog("AuthorizationInterceptor"));
    }
    try {
//      System.out.println("qw");
//      throw new ToolException(ToolException.E_PARAM_ERR);
//      return false;
      return true;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    if (log.isInfoEnabled()) {
      log.debug(ToolUtil.getLog("AuthorizationInterceptor"));
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    if (log.isInfoEnabled()) {
      log.debug(ToolUtil.getLog("AuthorizationInterceptor"));
    }
  }

}
