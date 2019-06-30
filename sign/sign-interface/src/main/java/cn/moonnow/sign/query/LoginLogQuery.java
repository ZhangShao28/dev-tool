package cn.moonnow.sign.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 登录日志 DEV_LOGIN_LOG 查询类
 */
@Data
public class LoginLogQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String logId; // 登录日志编号

  protected String loginKey; // 登录标识

  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String mobilePhoneNumber; // 手机号

  protected Long loginTimes; // 登录时间

  protected List<String> logIdAndin; // 登录日志编号Andin查询

  protected String loginKeyAndKeyLike; // 登录标识AndKeyLike查询

  protected String accountIdOrKeyLike; // 账号编号OrKeyLike查询

  protected String accountOrKeyLike; // 账号OrKeyLike查询

  protected String mobilePhoneNumberOrKeyLike; // 手机号OrKeyLike查询

}
