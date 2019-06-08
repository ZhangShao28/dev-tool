package cn.moonnow.tool.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 排序 PRO_SORT 实体类
 */
@Data
@TableName(value = "PRO_SORT")
public class Sort implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String sortId; // 排序编号

  protected String sortRule; // 排序规则

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

}
