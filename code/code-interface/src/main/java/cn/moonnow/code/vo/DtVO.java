package cn.moonnow.code.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据库表 PRO_DT 展示类
 */
@Data
public class DtVO implements Serializable {

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

}
