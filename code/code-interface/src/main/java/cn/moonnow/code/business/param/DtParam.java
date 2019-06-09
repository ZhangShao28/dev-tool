package cn.moonnow.code.business.param;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据库表 参数类
 */
@Data
public class DtParam implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String sqlFilePath; // sql文件绝对路径

  protected String dtPrefix; // 数据库表前缀

  protected String proPath; // 项目路径

  protected String proAllName; // 项目全称

}
