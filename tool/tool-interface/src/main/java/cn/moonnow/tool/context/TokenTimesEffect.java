package cn.moonnow.tool.context;

import java.io.Serializable;

public class TokenTimesEffect implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String token;

  protected Long cookiesExpireTimes;

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
