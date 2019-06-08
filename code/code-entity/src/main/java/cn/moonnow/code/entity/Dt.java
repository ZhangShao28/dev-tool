package cn.moonnow.code.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 数据库表 PRO_DT 实体类
 */
@Data
@TableName(value = "PRO_DT")
public class Dt implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
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
