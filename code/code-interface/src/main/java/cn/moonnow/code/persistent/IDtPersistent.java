package cn.moonnow.code.persistent;

import java.util.Collection;

import cn.moonnow.code.entity.Dt;
import cn.moonnow.code.query.DtQuery;
import cn.moonnow.code.vo.DtVO;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;

/**
 * 数据库表 持久化接口
 */
public interface IDtPersistent {

  public static final String TABLE_NAME = "PRO_DT";

  /**
   * 保存 数据库表 信息
   */
  public Dt saveDt(Dt dt) throws Exception;

  /**
   * 批量保存 数据库表 信息
   */
  public Collection<Dt> batchSaveDt(Collection<Dt> dts) throws Exception;

  /**
   * 修改 数据库表 信息
   */
  public Dt updateDt(Dt dt) throws Exception;

  /**
   * 批量修改 数据库表 信息
   */
  public Collection<Dt> batchUpdateDt(Collection<Dt> dts) throws Exception;

  /**
   * 删除 数据库表 信息
   */
  public void removeDt(Dt dt) throws Exception;

  /**
   * 批量删除 数据库表 信息
   */
  public void batchRemoveDt(Collection<Dt> dts) throws Exception;

  /**
   * 获取 数据库表 数据量
   */
  public Long getCountDt(DtQuery dtQuery) throws Exception;

  /**
   * 通过主键获取 数据库表 数据
   */
  public Dt getDtByPk(String dtId) throws Exception;

  /**
   * 获取 数据库表 所有数据
   */
  public Collection<Dt> getAllDt() throws Exception;

  /**
   * 查询 数据库表 数据
   */
  public Collection<Dt> queryDt(DtQuery dtQuery) throws Exception;

  /**
   * 分页查询 数据库表 数据
   */
  public Paging<Dt> pagingQueryDt(Param param, DtQuery dtQuery) throws Exception;

  /**
   * 通过主键获取 数据库表 展示数据
   */
  public DtVO getDtVOByPk(String dtId) throws Exception;

  /**
   * 获取 数据库表 所有展示数据
   */
  public Collection<DtVO> getAllDtVO() throws Exception;

  /**
   * 查询 数据库表 展示数据
   */
  public Collection<DtVO> queryDtVO(DtQuery dtQuery) throws Exception;

  /**
   * 分页查询 数据库表 展示数据
   */
  public Paging<DtVO> pagingQueryDtVO(Param param, DtQuery dtQuery) throws Exception;

}
