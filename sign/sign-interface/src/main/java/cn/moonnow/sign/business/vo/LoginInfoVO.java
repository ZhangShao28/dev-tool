package cn.moonnow.sign.business.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录信息
 */
@Data
public class LoginInfoVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String account; // 账号

  protected String password; // 密码

  protected String mobilePhoneNumber; // 手机号

  protected String verificationCode; // 验证码

}
