package cn.moonnow.code.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 列 PRO_COLUMNS 实体类
 */
@Data
@TableName(value = "PRO_COLUMNS")
public class Columns implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String columnsId; // 列编号

  protected String columnName; // 列名

  protected String columnNameAnnotation; // 列名注释

  protected String dataType; // 数据类型

  protected String isNull; // 是否为空

  protected String initialCaseColumnName; // 首字母大写列名

  protected String initialLowercaseColumnName; // 首字母小写列名

  protected Integer weightOrder; // 排序权重

  protected String dtId; // 数据库表编号

}
