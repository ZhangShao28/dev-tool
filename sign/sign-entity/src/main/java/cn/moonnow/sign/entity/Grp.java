package cn.moonnow.sign.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 组 DEV_GRP 实体类
 */
@Data
@TableName(value = "DEV_GRP")
public class Grp implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  protected String grpId; // 组编号

  protected String grpKey; // 组标识

  protected String grpName; // 组名称

  protected String notes; // 说明

  protected Integer versions; // 版本号

}
