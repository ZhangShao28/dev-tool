package cn.moonnow.tool.context;

import java.io.Serializable;

import lombok.Data;

/**
 * 当前登录账号信息</br>
 * 名字 存放业务实际需要的人员姓名</br>
 * 例如 员工姓名</br>
 * 默认存放账号
 */
@Data
public class CurrentLoginAccountInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String accountId; // 账号编号

  protected String name; // 名字

}
