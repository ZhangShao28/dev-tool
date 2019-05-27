package cn.moonnow.dict.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * [数据字典]实体类, 表名[DEV_DICT].
 */
@Data
@TableName(value = "DEV_DICT")
public class Dict implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String dictId; // 数据字典编号

  protected String dictKey; // 数据字典标识

  protected String dictName; // 数据字典名称

  protected String notes; // 说明

  protected Integer weightOrder; // 排序权重

  protected Integer version; // 版本号

  protected String createId; // 创建人编号

  protected String createAcc; // 创建人账号

  protected String createName; // 创建人名称

  protected String modifyId; // 修改人编号

  protected String modifyAcc; // 修改人账号

  protected String modifyName; // 修改人名称

}
