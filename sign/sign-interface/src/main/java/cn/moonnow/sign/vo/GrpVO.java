package cn.moonnow.sign.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 组 DEV_GRP 展示类
 */
@Data
public class GrpVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String grpId; // 组编号

  protected String grpKey; // 组标识

  protected String grpName; // 组名称

  protected String notes; // 说明

  protected Integer versions; // 版本号

}
