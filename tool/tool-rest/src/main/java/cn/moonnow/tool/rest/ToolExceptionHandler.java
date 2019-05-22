package cn.moonnow.tool.rest;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.moonnow.tool.exception.ToolException;

@RestControllerAdvice
public class ToolExceptionHandler {

  @ExceptionHandler(Exception.class)
  public LinkedHashMap<String, Object> exceptionHandler(HttpServletRequest request, Exception e) {
    LinkedHashMap<String, Object> r = new LinkedHashMap<String, Object>();
    if (e instanceof ToolException) {
      r.put("msg", ((ToolException) e).getErrMsg());
      return r;
    } else {
      r.put("msg", ToolException.E_ERR);
      return r;
    }
  }

}
