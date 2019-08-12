package cn.moonnow.customer.management.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 客户 DEV_CUSTOMER 查询类
 */
@Data
public class CustomerQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String toolExists; // 解决多对多关系预留字段之存在

  protected String toolNotExists; // 解决多对多关系预留字段之不存在

  protected String customerId; // 客户编号

  protected String customerNo; // 客户序号

  protected String customerName; // 客户名称

  protected String creditCode; // 社会统一信用代码

  protected String address; // 地址

  protected String fax; // 传真

  protected String legalRepresentative; // 法定代表人

  protected String legalRepresentativePhone; // 法定代表人电话

  protected String legalRepresentativeFax; // 法定代表人传真

  protected String agent; // 委托代理人

  protected String agentPhone; // 委托代理人电话

  protected String agentFax; // 委托代理人传真

  protected String openingBank; // 开户银行

  protected String bankAccount; // 银行账号

  protected String taxRegistrationNumber; // 纳税登记号

  protected String phoneNumber; // 联系电话

  protected BigDecimal accountBalance; // 账户余额

  protected BigDecimal availableBalance; // 可用余额

  protected List<String> customerIdAndin; // 客户编号Andin查询

  protected String customerNoAndKeyLike; // 客户序号AndKeyLike查询

  protected String customerNameOrKeyLike; // 客户名称OrKeyLike查询

  protected String creditCodeOrKeyLike; // 社会统一信用代码OrKeyLike查询

  protected String addressOrKeyLike; // 地址OrKeyLike查询

  protected String faxOrKeyLike; // 传真OrKeyLike查询

  protected String legalRepresentativeOrKeyLike; // 法定代表人OrKeyLike查询

  protected String legalRepresentativePhoneOrKeyLike; // 法定代表人电话OrKeyLike查询

  protected String legalRepresentativeFaxOrKeyLike; // 法定代表人传真OrKeyLike查询

  protected String agentOrKeyLike; // 委托代理人OrKeyLike查询

  protected String agentPhoneOrKeyLike; // 委托代理人电话OrKeyLike查询

  protected String agentFaxOrKeyLike; // 委托代理人传真OrKeyLike查询

  protected String openingBankOrKeyLike; // 开户银行OrKeyLike查询

  protected String bankAccountOrKeyLike; // 银行账号OrKeyLike查询

  protected String taxRegistrationNumberOrKeyLike; // 纳税登记号OrKeyLike查询

  protected String phoneNumberOrKeyLike; // 联系电话OrKeyLike查询

}
