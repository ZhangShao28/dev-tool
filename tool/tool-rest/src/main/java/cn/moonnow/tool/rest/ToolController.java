package cn.moonnow.tool.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class ToolController {

  @Autowired(required = false)
  private HttpServletRequest httpServletRequest;

  @Autowired(required = false)
  private HttpServletResponse httpServletResponse;

  public HttpServletRequest getHttpServletRequest() {
    return httpServletRequest;
  }

  public HttpServletResponse getHttpServletResponse() {
    return httpServletResponse;
  }

}
