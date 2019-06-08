package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 主键 PRO_PK 查询类
 */
@Data
public class PkQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String pkId; // 主键编号

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected List<String> pkIdAndin; // 主键编号Andin查询

  protected String dtIdAndeq; // 数据库表编号Andeq查询

  protected String columnsIdAndeq; // 列编号Andeq查询

  protected String dtIdAndKeyLike; // 数据库表编号搜索

  protected String columnsIdOrKeyLike; // 列编号搜索

}
