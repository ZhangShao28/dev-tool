package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 账号分组 DEV_ACCOUNT_GRP_NEXUS 实体类
 */
@Data
@TableName(value = "DEV_ACCOUNT_GRP_NEXUS")
public class AccountGrpNexus implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String nexusId; // 账号分组编号

  protected String accountId; // 账号编号

  protected String grpId; // 组编号

}
