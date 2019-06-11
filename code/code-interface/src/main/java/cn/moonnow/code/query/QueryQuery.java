package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 查询 PRO_QUERY 查询类
 */
@Data
public class QueryQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String queryId; // 查询编号

  protected String queryType; // 查询类型

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected Integer weightOrder; // 排序权重

  protected List<String> queryIdAndin; // 查询编号Andin查询

  protected String queryTypeAndeq; // 查询类型Andeq查询

  protected String dtIdAndeq; // 数据库表编号Andeq查询

  protected String columnsIdAndeq; // 列编号Andeq查询

  protected String queryTypeAndKeyLike; // 查询类型AndKeyLike查询

  protected String dtIdOrKeyLike; // 数据库表编号OrKeyLike查询

  protected String columnsIdOrKeyLike; // 列编号OrKeyLike查询

}
