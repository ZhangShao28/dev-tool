package cn.moonnow.sign.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 账号 DEV_ACCOUNT 查询类
 */
@Data
public class AccountQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String accountId; // 账号编号

  protected String account; // 账号

  protected String password; // 密码

  protected String mobilePhoneNumber; // 手机号

  protected String nexusType; // 关联类型

  protected String nexusObjectId; // 对象编号

  protected Integer versions; // 版本号

  protected List<String> accountIdAndin; // 账号编号Andin查询

  protected String accountAndeq; // 账号Andeq查询

  protected String accountAndKeyLike; // 账号AndKeyLike查询

  protected String passwordOrKeyLike; // 密码OrKeyLike查询

  protected String mobilePhoneNumberOrKeyLike; // 手机号OrKeyLike查询

  protected String nexusTypeOrKeyLike; // 关联类型OrKeyLike查询

  protected String nexusObjectIdOrKeyLike; // 对象编号OrKeyLike查询

}
