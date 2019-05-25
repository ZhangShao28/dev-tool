package cn.moonnow.config;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.moonnow.tool.rest.AuthorizationInterceptor;

@Configuration
@ConditionalOnProperty(prefix = "proconfig", name = "authorization-interceptor", havingValue = "true")
public class AuthorizationInterceptorWebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private AuthorizationInterceptor authorizationInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LinkedList<String> excludeUrl = new LinkedList<>();
    excludeUrl.add("/pro/login");
    excludeUrl.add("/pro/register/wechatapp/account");
    excludeUrl.add("/initdata");
    registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**").excludePathPatterns(excludeUrl);
  }

}
