package cn.moonnow.sign.query;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 组 DEV_GRP 查询类
 */
@Data
public class GrpQuery implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String grpId; // 组编号

  protected String grpKey; // 组标识

  protected String grpName; // 组名称

  protected String notes; // 说明

  protected Integer versions; // 版本号

  protected List<String> grpIdAndin; // 组编号Andin查询

  protected String grpKeyAndeq; // 组标识Andeq查询

  protected String grpKeyAndKeyLike; // 组标识AndKeyLike查询

  protected String grpNameOrKeyLike; // 组名称OrKeyLike查询

  protected String notesOrKeyLike; // 说明OrKeyLike查询

}
