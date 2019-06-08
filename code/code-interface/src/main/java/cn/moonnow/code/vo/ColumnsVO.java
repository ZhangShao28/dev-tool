package cn.moonnow.code.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 列 PRO_COLUMNS 展示类
 */
@Data
public class ColumnsVO implements Serializable {

  private static final long serialVersionUID = 1L;

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
