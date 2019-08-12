package cn.moonnow.customer.management.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 客户 DEV_CUSTOMER 展示类
 */
@Data
public class CustomerVO implements Serializable {

  private static final long serialVersionUID = 1L;

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

}
