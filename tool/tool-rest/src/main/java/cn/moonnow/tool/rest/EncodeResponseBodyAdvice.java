package cn.moonnow.tool.rest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.google.gson.Gson;

import cn.moonnow.tool.util.ToolUtil;

/**
 * 请求加密
 */
@RestControllerAdvice
@ConditionalOnProperty(prefix = "proconfig", name = "http-response-body-encode", havingValue = "true")
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    String encodeBodyStr = new Gson().toJson(body);
    return ToolUtil.encodeAES(encodeBodyStr, ToolUtil.AES_KEY);
//    return body;
  }

}
