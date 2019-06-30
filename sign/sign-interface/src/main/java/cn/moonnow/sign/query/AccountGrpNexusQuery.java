package cn.moonnow.sign.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 账号分组 DEV_ACCOUNT_GRP_NEXUS 查询类
 */
@Data
public class AccountGrpNexusQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String nexusId; // 账号分组编号

  protected String accountId; // 账号编号

  protected String grpId; // 组编号

  protected List<String> nexusIdAndin; // 账号分组编号Andin查询

  protected String accountIdAndeq; // 账号编号Andeq查询

  protected String grpIdAndeq; // 组编号Andeq查询

  protected String accountIdAndKeyLike; // 账号编号AndKeyLike查询

  protected String grpIdOrKeyLike; // 组编号OrKeyLike查询

}
