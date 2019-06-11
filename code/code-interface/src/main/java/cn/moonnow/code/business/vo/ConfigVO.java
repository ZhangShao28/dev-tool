package cn.moonnow.code.business.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 代码文件绝对路径配置信息 展示类
 */
@Data
public class ConfigVO implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String jdbcEntityFilePath;

  protected String hibernateEntityFilePath;

  protected String exceptionFilePath;

  protected String persistentInterfaceFilePath;

  protected String jdbcPersistentImplFilePath;

  protected String serviceInterfaceFilePath;

  protected String serviceImplFilePath;

  protected String controllerFilePath;

  protected String angularModuleFilePath;

  protected String angularRoutingFilePath;

  protected String angularEntityFilePath;

  protected String angularServiceFilePath;

  protected String angularListComponentFilePath;

  protected String angularListHtmlFilePath;

  protected String angularListCssFilePath;

  protected String angularEditComponentFilePath;

  protected String angularEditHtmlFilePath;

  protected String angularEditCssFilePath;

  protected String angularDetailComponentFilePath;

  protected String angularDetailHtmlFilePath;

  protected String angularDetailCssFilePath;

  protected String queryFilePath;

  protected String angularQueryFilePath;

  protected String projectPath;

  protected String voFilePath;

  protected String vueEntityFilePath;

  protected String vueQueryFilePath;

  protected String vueVoFilePath;

  protected String vueServiceFilePath;

  protected String vueStoreFilePath;

  protected String vueListFilePath;

  protected String vueListCssFilePath;

  protected String vueEditFilePath;

  protected String vueEditCssFilePath;

  protected String vueDetailFilePath;

  protected String vueDetailCssFilePath;

  protected String jpaPersistentInterfaceFilePath;

  protected String vueViewFilePath;

  protected String vueViewCssFilePath;

  protected String dtId;

}
