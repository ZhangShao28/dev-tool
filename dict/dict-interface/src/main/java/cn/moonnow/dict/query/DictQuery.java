package cn.moonnow.dict.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 数据字典 DEV_DICT 查询类
 */
@Data
public class DictQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String dictId; // 数据字典编号

  protected String dictKey; // 数据字典标识

  protected String dictName; // 数据字典名称

  protected String notes; // 说明

  protected Integer weightOrder; // 排序权重

  protected Integer versions; // 版本号

  protected String createId; // 创建人账号

  protected String createName; // 创建人名称

  protected Long createTimes; // 创建时间

  protected String modifyId; // 修改人账号

  protected String modifyName; // 修改人名称

  protected Long modifyTimes; // 修改时间

  protected List<String> dictIdAndin; // 数据字典编号Andin查询

  protected String dictKeyAndeq; // 数据字典标识Andeq查询

  protected String dictKeyAndKeyLike; // 数据字典标识AndKeyLike查询

  protected String dictNameOrKeyLike; // 数据字典名称OrKeyLike查询

  protected String notesOrKeyLike; // 说明OrKeyLike查询

  protected String createIdOrKeyLike; // 创建人账号OrKeyLike查询

  protected String createNameOrKeyLike; // 创建人名称OrKeyLike查询

  protected String modifyIdOrKeyLike; // 修改人账号OrKeyLike查询

  protected String modifyNameOrKeyLike; // 修改人名称OrKeyLike查询

}
