package cn.moonnow.tool.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import cn.moonnow.tool.context.ToolContextData;
import lombok.Data;

@Data
public class Responses<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean success;

  private String msg;

  private Long count;

  private Long rows;

  private Long page;

  private java.util.Collection<T> data;

  protected String token;

  protected Long cookiesExpireTimes;

  public Responses() {
    super();
    this.success = true;
    this.count = 0l;
    this.rows = 10l;
    this.page = 1l;
    this.data = Collections.emptyList();
    this.token = ToolContextData.getTokenTimesEffect() != null && ToolContextData.getTokenTimesEffect().getToken() != null ? ToolContextData.getTokenTimesEffect().getToken() : "token";
    this.cookiesExpireTimes = ToolContextData.getTokenTimesEffect() != null && ToolContextData.getTokenTimesEffect().getCookiesExpireTimes() != null ? ToolContextData.getTokenTimesEffect().getCookiesExpireTimes() : 0;
  }

//  public Responses(Parameter parameter) {
//    super();
//    this.isSuccess = true;
//    this.count = 0l;
//    this.rows = parameter.getRows();
//    this.page = parameter.getPage();
//    this.data = Collections.emptyList();
//    this.token = ToolContextData.getTokenTimesEffect() != null && ToolContextData.getTokenTimesEffect().getToken() != null ? ToolContextData.getTokenTimesEffect().getToken() : "token";
//    this.cookiesExpireTimes = ToolContextData.getTokenTimesEffect() != null && ToolContextData.getTokenTimesEffect().getCookiesExpireTimes() != null ? ToolContextData.getTokenTimesEffect().getCookiesExpireTimes() : 0;
//  }

  public void setData(Collection<T> data) {
    this.count = (long) data.size();
    this.data = data;
  }

  public void setData(T data) {
    this.count = 1l;
    this.data = new ArrayList<T>();
    this.data.add(data);
  }

//  public void setData(Paging<T> paging) {
//    this.count = paging.getCount();
//    this.rows = paging.getRows();
//    this.page = paging.getPage();
//    if (paging.getData() == null) {
//      this.data = Collections.emptyList();
//    } else {
//      this.data = paging.getData();
//    }
//  }

  public final void setException(String errMsg) {
    success = false;
    this.msg = errMsg;
  }

}
