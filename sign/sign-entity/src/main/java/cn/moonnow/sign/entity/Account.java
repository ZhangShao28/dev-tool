package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 账号 DEV_ACCOUNT 实体类
 */
@Data
@TableName(value = "DEV_ACCOUNT")
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String password; // 密码

  protected String mobilePhoneNumber; // 手机号

  protected String nexusType; // 关联类型

  protected String nexusObjectId; // 对象编号

  protected Integer versions; // 版本号

}
