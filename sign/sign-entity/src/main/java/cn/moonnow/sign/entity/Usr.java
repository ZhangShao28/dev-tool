package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * [用户]实体类, 表名[DEV_USR].
 */
@Data
@TableName(value = "DEV_USR")
public class Usr implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String usrId; // 用户编号

  protected String usrNo; // 用户序号

  protected String usrType; // 用户类型

  protected String usrName; // 用户名称

  protected Integer version; // 版本号

  protected String createId; // 创建人编号

  protected String createAcc; // 创建人账号

  protected String createName; // 创建人名称

  protected String modifyId; // 修改人编号

  protected String modifyAcc; // 修改人账号

  protected String modifyName; // 修改人名称

}
