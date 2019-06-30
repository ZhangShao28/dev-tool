package cn.moonnow.sign.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 会话 DEV_SESSION 展示类
 */
@Data
public class SessionVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String sessionId; // 会话编号

  protected String accountId; // 账号编号

  protected String loginKey; // 登录标识

  protected Long currentTimes; // 当前时间

  protected String aesKey; // AES密钥

}
