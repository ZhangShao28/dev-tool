package cn.moonnow.dict.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 数据字典值 DEV_DICT_VALUE 查询类
 */
@Data
public class DictValueQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String dictValueId; // 数据字典值编号

  protected String code; // 代码

  protected String displayName; // 显示名称

  protected Integer weightOrder; // 排序权重

  protected String description; // 描述

  protected String dictId; // 数据字典编号

  protected Integer versions; // 版本号

  protected String createId; // 创建人账号

  protected String createName; // 创建人名称

  protected Long createTimes; // 创建时间

  protected String modifyId; // 修改人账号

  protected String modifyName; // 修改人名称

  protected Long modifyTimes; // 修改时间

  protected List<String> dictValueIdAndin; // 数据字典值编号Andin查询

  protected String codeAndeq; // 代码Andeq查询

  protected String dictIdAndeq; // 数据字典编号Andeq查询

  protected String codeAndKeyLike; // 代码AndKeyLike查询

  protected String displayNameOrKeyLike; // 显示名称OrKeyLike查询

  protected String descriptionOrKeyLike; // 描述OrKeyLike查询

  protected String dictIdOrKeyLike; // 数据字典编号OrKeyLike查询

  protected String createIdOrKeyLike; // 创建人账号OrKeyLike查询

  protected String createNameOrKeyLike; // 创建人名称OrKeyLike查询

  protected String modifyIdOrKeyLike; // 修改人账号OrKeyLike查询

  protected String modifyNameOrKeyLike; // 修改人名称OrKeyLike查询

}
