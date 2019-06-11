package cn.moonnow.code.business.service;

import cn.moonnow.code.business.param.DtParam;
import cn.moonnow.code.business.vo.ConfigVO;
import cn.moonnow.code.entity.Dt;

/**
 * 生成代码 服务接口
 */
public interface ICodingService {

  /**
   * 测试专用</br>
   * 保存 数据库表 信息</br>
   * 从sql文件获取建表sql
   */
  public Dt saveDtInfo(DtParam dtParam) throws Exception;

  /**
   * 测试专用</br>
   * 提取所有数据库表数据
   */
  public void initData() throws Exception;

  /**
   * 提取数据库表数据
   */
  public void extract(Dt dt) throws Exception;

  /**
   * 获取代码文件绝对路径配置信息
   */
  public ConfigVO getConfig(Dt dt) throws Exception;

  /**
   * 生成 java实体类 代码
   */
  public void codingJdbcEntity(ConfigVO configVo) throws Exception;

  /**
   * 生成 java查询类 代码
   */
  public void codingQuery(ConfigVO configVo) throws Exception;

  /**
   * 生成 java展示类 代码
   */
  public void codingVo(ConfigVO configVo) throws Exception;

  /**
   * 生成 java持久化接口 代码
   */
  public void codingIPersistent(ConfigVO configVo) throws Exception;

  /**
   * 生成 java持久化实现 代码
   */
  public void codingJdbcPersistentImpl(ConfigVO configVo) throws Exception;

  /**
   * 生成 java服务接口 代码
   */
  public void codingIService(ConfigVO configVo) throws Exception;

  /**
   * 生成 java服务实现 代码
   */
  public void codingServiceImpl(ConfigVO configVo) throws Exception;

  /**
   * 生成 java控制器 代码
   */
  public void codingController(ConfigVO configVo) throws Exception;

  /**
   * 生成项目
   */
  public void codingProject(ConfigVO configVo) throws Exception;

}
