package cn.moonnow.sign.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 账号分组 DEV_ACCOUNT_GRP_NEXUS 展示类
 */
@Data
public class AccountGrpNexusVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String nexusId; // 账号分组编号

  protected String accountId; // 账号编号

  protected String grpId; // 组编号

}
