package cn.moonnow.code.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 查询 PRO_QUERY 展示类
 */
@Data
public class QueryVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String queryId; // 查询编号

  protected String queryType; // 查询类型

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected Integer weightOrder; // 排序权重

  protected String columnName; // 列名

  protected String columnNameAnnotation; // 列名注释

}
