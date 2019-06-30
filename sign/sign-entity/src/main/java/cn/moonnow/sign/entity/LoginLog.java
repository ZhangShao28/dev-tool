package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 登录日志 DEV_LOGIN_LOG 实体类
 */
@Data
@TableName(value = "DEV_LOGIN_LOG")
public class LoginLog implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String logId; // 登录日志编号

  protected String loginKey; // 登录标识

  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String mobilePhoneNumber; // 手机号

  protected Long loginTimes; // 登录时间

}
