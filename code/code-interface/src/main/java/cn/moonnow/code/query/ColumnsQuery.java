package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 列 PRO_COLUMNS 查询类
 */
@Data
public class ColumnsQuery implements Serializable {

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

  protected List<String> columnsIdAndin; // 列编号Andin查询

  protected String columnNameAndeq; // 列名Andeq查询

  protected String dtIdAndeq; // 数据库表编号Andeq查询

  protected String columnNameAndKeyLike; // 列名AndKeyLike查询

  protected String columnNameAnnotationOrKeyLike; // 列名注释OrKeyLike查询

  protected String dataTypeOrKeyLike; // 数据类型OrKeyLike查询

  protected String isNullOrKeyLike; // 是否为空OrKeyLike查询

  protected String initialCaseColumnNameOrKeyLike; // 首字母大写列名OrKeyLike查询

  protected String initialLowercaseColumnNameOrKeyLike; // 首字母小写列名OrKeyLike查询

  protected String dtIdOrKeyLike; // 数据库表编号OrKeyLike查询

}
