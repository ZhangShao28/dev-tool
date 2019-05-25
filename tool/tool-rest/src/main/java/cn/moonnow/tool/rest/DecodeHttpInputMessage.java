package cn.moonnow.tool.rest;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public class DecodeHttpInputMessage implements HttpInputMessage {

  HttpHeaders headers;
  InputStream body;

  public DecodeHttpInputMessage(HttpHeaders headers, InputStream body) {
    this.headers = headers;
    this.body = body;
  }

  @Override
  public InputStream getBody() throws IOException {
    return body;
  }

  @Override
  public HttpHeaders getHeaders() {
    return headers;
  }

}
