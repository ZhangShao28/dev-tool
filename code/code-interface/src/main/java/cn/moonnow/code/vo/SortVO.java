package cn.moonnow.code.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 排序 PRO_SORT 展示类
 */
@Data
public class SortVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String sortId; // 排序编号

  protected String sortRule; // 排序规则

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected String columnName; // 列名

  protected String columnNameAnnotation; // 列名注释

}
