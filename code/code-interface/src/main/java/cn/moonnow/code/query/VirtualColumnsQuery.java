package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 虚拟列 PRO_VIRTUAL_COLUMNS 查询类
 */
@Data
public class VirtualColumnsQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String virtualColumnsId; // 虚拟列编号

  protected String sourceDtId; // 源表数据库表编号

  protected String targetDtId; // 目标表数据库表编号

  protected String sourceColumnsId; // 源表列编号

  protected String targetColumnsId; // 目标表列编号

  protected String targetDisplayColumnsId; // 目标表显示字段列编号

  protected String displayColumnsAlias; // 显示字段别名

  protected String virtualColumnsSql; // 虚拟列sql

  protected Integer weightOrder; // 排序权重

  protected List<String> virtualColumnsIdAndin; // 虚拟列编号Andin查询

  protected String sourceDtIdAndKeyLike; // 源表数据库表编号AndKeyLike查询

  protected String targetDtIdOrKeyLike; // 目标表数据库表编号OrKeyLike查询

  protected String sourceColumnsIdOrKeyLike; // 源表列编号OrKeyLike查询

  protected String targetColumnsIdOrKeyLike; // 目标表列编号OrKeyLike查询

  protected String targetDisplayColumnsIdOrKeyLike; // 目标表显示字段列编号OrKeyLike查询

  protected String displayColumnsAliasOrKeyLike; // 显示字段别名OrKeyLike查询

  protected String virtualColumnsSqlOrKeyLike; // 虚拟列sqlOrKeyLike查询

}
