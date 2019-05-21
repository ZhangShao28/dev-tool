package cn.moonnow.sign.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * [用户]实体类, 表名[DEV_USR].
 */
@Data
@TableName(value = "DEV_USR")
public class Usr implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected java.lang.String usrId; // 用户编号

  protected java.lang.String usrNo; // 用户序号

  protected java.lang.String usrType; // 用户类型

  protected java.lang.String usrName; // 用户名称

  protected java.lang.Integer version; // 版本号

  protected java.lang.String createId; // 创建人编号

  protected java.lang.String createAcc; // 创建人账号

  protected java.lang.String createName; // 创建人名称

  protected java.lang.String modifyId; // 修改人编号

  protected java.lang.String modifyAcc; // 修改人账号

  protected java.lang.String modifyName; // 修改人名称

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

}
