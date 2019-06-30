package cn.moonnow.sign.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 账号 DEV_ACCOUNT 展示类
 */
@Data
public class AccountVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String password; // 密码

  protected String mobilePhoneNumber; // 手机号

  protected String nexusType; // 关联类型

  protected String nexusObjectId; // 对象编号

  protected Integer versions; // 版本号

}
