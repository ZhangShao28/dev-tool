package cn.moonnow.code.business.service;

import cn.moonnow.code.business.param.DtParam;
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

}
