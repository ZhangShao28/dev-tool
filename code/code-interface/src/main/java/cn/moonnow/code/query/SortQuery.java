package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 排序 PRO_SORT 查询类
 */
@Data
public class SortQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String sortId; // 排序编号

  protected String sortRule; // 排序规则

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected List<String> sortIdAndin; // 排序编号Andin查询

  protected String dtIdAndeq; // 数据库表编号Andeq查询

  protected String columnsIdAndeq; // 列编号Andeq查询

  protected String sortRuleAndKeyLike; // 排序规则AndKeyLike查询

  protected String dtIdOrKeyLike; // 数据库表编号OrKeyLike查询

  protected String columnsIdOrKeyLike; // 列编号OrKeyLike查询

}
