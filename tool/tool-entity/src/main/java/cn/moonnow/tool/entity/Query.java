package cn.moonnow.tool.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 查询 PRO_QUERY 实体类
 */
@Data
@TableName(value = "PRO_QUERY")
public class Query implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String queryId; // 查询编号

  protected String queryType; // 查询类型

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

  protected Integer weightOrder; // 排序权重

}
