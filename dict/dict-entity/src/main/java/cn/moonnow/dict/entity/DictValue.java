package cn.moonnow.dict.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 数据字典值 DEV_DICT_VALUE 实体类
 */
@Data
@TableName(value = "DEV_DICT_VALUE")
public class DictValue implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String dictValueId; // 数据字典值编号

  protected String code; // 代码

  protected String displayName; // 显示名称

  protected Integer weightOrder; // 排序权重

  protected String description; // 描述

  protected String dictId; // 数据字典编号

  protected Integer versions; // 版本号

  protected String createId; // 创建人账号

  protected String createName; // 创建人名称

  protected Long createTimes; // 创建时间

  protected String modifyId; // 修改人账号

  protected String modifyName; // 修改人名称

  protected Long modifyTimes; // 修改时间

}
