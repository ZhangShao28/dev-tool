package cn.moonnow.code.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 虚拟列 PRO_VIRTUAL_COLUMNS 实体类
 */
@Data
@TableName(value = "PRO_VIRTUAL_COLUMNS")
public class VirtualColumns implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String virtualColumnsId; // 虚拟列编号

  protected String sourceDtId; // 源表数据库表编号

  protected String targetDtId; // 目标表数据库表编号

  protected String sourceColumnsId; // 源表列编号

  protected String targetColumnsId; // 目标表列编号

  protected String targetDisplayColumnsId; // 目标表显示字段列编号

  protected String displayColumnsAlias; // 显示字段别名

  protected String virtualColumnsSql; // 虚拟列sql

  protected Integer weightOrder; // 排序权重

}
