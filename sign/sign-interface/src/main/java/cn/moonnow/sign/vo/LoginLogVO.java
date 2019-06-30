package cn.moonnow.sign.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录日志 DEV_LOGIN_LOG 展示类
 */
@Data
public class LoginLogVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String logId; // 登录日志编号

  protected String loginKey; // 登录标识

  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String mobilePhoneNumber; // 手机号

  protected Long loginTimes; // 登录时间

}
