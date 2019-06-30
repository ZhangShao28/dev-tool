package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 会话 DEV_SESSION 实体类
 */
@Data
@TableName(value = "DEV_SESSION")
public class Session implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String sessionId; // 会话编号

  protected String accountId; // 账号编号

  protected String loginKey; // 登录标识

  protected Long currentTimes; // 当前时间

  protected String aesKey; // AES密钥

}
