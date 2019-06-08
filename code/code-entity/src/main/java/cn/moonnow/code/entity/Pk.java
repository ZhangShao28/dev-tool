package cn.moonnow.code.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 主键 PRO_PK 实体类
 */
@Data
@TableName(value = "PRO_PK")
public class Pk implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String pkId; // 主键编号

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

}
