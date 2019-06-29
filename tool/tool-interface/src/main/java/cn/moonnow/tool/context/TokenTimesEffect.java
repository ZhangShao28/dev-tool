package cn.moonnow.tool.context;

import java.io.Serializable;

import lombok.Data;

/**
 * 当前登录者的会话信息
 */
@Data
public class TokenTimesEffect implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String token;

  protected Long cookiesExpireTimes;

}
