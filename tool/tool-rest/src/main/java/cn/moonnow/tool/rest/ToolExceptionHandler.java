package cn.moonnow.tool.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.moonnow.tool.exception.ToolException;

/**
 * 请求异常拦截器</br>
 * 同一处理异常
 */
@RestControllerAdvice
public class ToolExceptionHandler {

  @ExceptionHandler(Exception.class)
  public Responses<?> exceptionHandler(HttpServletRequest request, Exception e) {
    Responses<?> responses = new Responses<>();
    if (e instanceof ToolException) {
      responses.setException(((ToolException) e).getErrMsg());
      return responses;
    } else {
      responses.setException(ToolException.E_ERR);
      return responses;
    }
  }

}
