package cn.moonnow.test.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * [测试]实体类, 表名[TEST]
 */
@Data
@TableName(value = "TEST")
public class Test implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String testId; // 编号

  protected Integer version; // 版本号

  protected String createId; // 创建人编号

  protected String createAcc; // 创建人账号

  protected String createName; // 创建人名称

  protected String modifyId; // 修改人编号

  protected String modifyAcc; // 修改人账号

  protected String modifyName; // 修改人名称

}
