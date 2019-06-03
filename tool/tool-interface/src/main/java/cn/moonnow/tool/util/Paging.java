package cn.moonnow.tool.util;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

/**
 * 返回分页数据
 */
@Data
public class Paging<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long count; // 数据总条数

  private Long offset; // 偏移量(mysql使用)

  private Long rows; // 多少条

  private Long page; // 第几页

  private Collection<T> data; // 数据

  private Long startOffset; // 开始偏移量(oracle使用)

  private Long endOffset; // 结束偏移量(oracle使用)

  public Paging() {
    super();
  }

  public Paging(Param param) {
    this();
    this.offset = (param.getPage() - 1) * param.getRows();
    this.rows = param.getRows();
    this.page = param.getPage();
    this.startOffset = (param.getPage() - 1) * param.getRows() + 1;
    this.endOffset = param.getPage() * param.getRows();
  }

}
