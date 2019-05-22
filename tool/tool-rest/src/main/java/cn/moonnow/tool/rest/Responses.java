package cn.moonnow.tool.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import cn.moonnow.tool.context.ToolContextData;

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

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getRows() {
    return rows;
  }

  public void setRows(Long rows) {
    this.rows = rows;
  }

  public Long getPage() {
    return page;
  }

  public void setPage(Long page) {
    this.page = page;
  }

  public Collection<T> getData() {
    return data;
  }

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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getCookiesExpireTimes() {
    return cookiesExpireTimes;
  }

  public void setCookiesExpireTimes(Long cookiesExpireTimes) {
    this.cookiesExpireTimes = cookiesExpireTimes;
  }

  public final void setException(String errMsg) {
    success = false;
    this.msg = errMsg;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

}
