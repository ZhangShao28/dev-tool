package cn.moonnow.sign.service;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.sign.entity.Grp;
import cn.moonnow.sign.query.GrpQuery;
import cn.moonnow.sign.vo.GrpVO;

/**
 * 组 服务接口
 */
public interface IGrpService {

  /**
   * 保存 组 信息
   */
  public Grp saveGrp(Grp grp) throws Exception;

  /**
   * 批量保存 组 信息
   */
  public Collection<Grp> batchSaveGrp(Collection<Grp> grps) throws Exception;

  /**
   * 修改 组 信息
   */
  public Grp updateGrp(Grp grp) throws Exception;

  /**
   * 批量修改 组 信息
   */
  public Collection<Grp> batchUpdateGrp(Collection<Grp> grps) throws Exception;

  /**
   * 删除 组 信息
   */
  public void removeGrp(Grp grp) throws Exception;

  /**
   * 批量删除 组 信息
   */
  public void batchRemoveGrp(Collection<Grp> grps) throws Exception;

  /**
   * 获取 组 数据量
   */
  public Long getCountGrp(GrpQuery grpQuery) throws Exception;

  /**
   * 通过主键获取 组 数据
   */
  public Grp getGrpByPk(String grpId) throws Exception;

  /**
   * 获取 组 所有数据
   */
  public Collection<Grp> getAllGrp() throws Exception;

  /**
   * 查询 组 数据
   */
  public Collection<Grp> queryGrp(GrpQuery grpQuery) throws Exception;

  /**
   * 分页查询 组 数据
   */
  public Paging<Grp> pagingQueryGrp(Param param, GrpQuery grpQuery) throws Exception;

  /**
   * 通过主键获取 组 展示数据
   */
  public GrpVO getGrpVOByPk(String grpId) throws Exception;

  /**
   * 获取 组 所有展示数据
   */
  public Collection<GrpVO> getAllGrpVO() throws Exception;

  /**
   * 查询 组 展示数据
   */
  public Collection<GrpVO> queryGrpVO(GrpQuery grpQuery) throws Exception;

  /**
   * 分页查询 组 展示数据
   */
  public Paging<GrpVO> pagingQueryGrpVO(Param param, GrpQuery grpQuery) throws Exception;

}
