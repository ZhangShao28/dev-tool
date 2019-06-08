package cn.moonnow.tool.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import cn.moonnow.tool.util.ToolUtil;

@RestControllerAdvice
@ConditionalOnProperty(prefix = "proconfig", name = "http-request-body-decode", havingValue = "true")
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    String decodeHttpInputMessageStr = ToolUtil.getStrFromInputStream(inputMessage.getBody());
//    decodeHttpInputMessageStr = ToolUtil.decodeAES(decodeHttpInputMessageStr, ToolUtil.AES_KEY);
    return new DecodeHttpInputMessage(inputMessage.getHeaders(), new ByteArrayInputStream(decodeHttpInputMessageStr.getBytes()));
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }

}
