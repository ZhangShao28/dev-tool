package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "PRO_DATA_DICT")
public class DataDict implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected java.lang.String dataDictId; // 数据字典编号

  protected java.lang.String dataDictKey; // 数据字典标识

  protected java.lang.String dataDictName; // 数据字典名称

  protected java.lang.Integer weightOrder; // 排序权重

  protected java.lang.String description; // 描述

}
