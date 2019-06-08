package cn.moonnow.code.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 主键 PRO_PK 展示类
 */
@Data
public class PkVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String pkId; // 主键编号

  protected String dtId; // 数据库表编号

  protected String columnsId; // 列编号

}
