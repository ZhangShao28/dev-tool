package cn.moonnow.code.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 数据库表 PRO_DT 查询类
 */
@Data
public class DtQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String dtId; // 数据库表编号

  protected String dtSql; // 数据库表sql

  protected String dtName; // 表名

  protected String dtNameAnnotation; // 表名注释

  protected String dtPrefix; // 数据库表前缀

  protected String initialCaseEntityName; // 首字母大写实体类名

  protected String initialLowercaseEntityName; // 首字母小写实体类名

  protected String proPath; // 项目路径

  protected String proAllName; // 项目全称

  protected List<String> dtIdAndin; // 数据库表编号Andin查询

  protected String dtNameAndeq; // 表名Andeq查询

  protected String dtNameAndKeyLike; // 表名搜索

  protected String dtNameAnnotationOrKeyLike; // 表名注释搜索

  protected String dtPrefixOrKeyLike; // 数据库表前缀搜索

  protected String initialCaseEntityNameOrKeyLike; // 首字母大写实体类名搜索

  protected String initialLowercaseEntityNameOrKeyLike; // 首字母小写实体类名搜索

  protected String proPathOrKeyLike; // 项目路径搜索

  protected String proAllNameOrKeyLike; // 项目全称搜索

}
