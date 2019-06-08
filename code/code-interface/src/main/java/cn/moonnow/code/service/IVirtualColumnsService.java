package cn.moonnow.code.service;

import java.util.Collection;

import cn.moonnow.code.entity.VirtualColumns;
import cn.moonnow.code.query.VirtualColumnsQuery;
import cn.moonnow.code.vo.VirtualColumnsVO;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;

/**
 * 虚拟列 服务接口
 */
public interface IVirtualColumnsService {

  /**
   * 保存 虚拟列 信息
   */
  public VirtualColumns saveVirtualColumns(VirtualColumns virtualColumns) throws Exception;

  /**
   * 批量保存 虚拟列 信息
   */
  public Collection<VirtualColumns> batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception;

  /**
   * 修改 虚拟列 信息
   */
  public VirtualColumns updateVirtualColumns(VirtualColumns virtualColumns) throws Exception;

  /**
   * 批量修改 虚拟列 信息
   */
  public Collection<VirtualColumns> batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception;

  /**
   * 删除 虚拟列 信息
   */
  public void removeVirtualColumns(VirtualColumns virtualColumns) throws Exception;

  /**
   * 批量删除 虚拟列 信息
   */
  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws Exception;

  /**
   * 获取 虚拟列 数据量
   */
  public Long getCountVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws Exception;

  /**
   * 通过主键获取 虚拟列 数据
   */
  public VirtualColumns getVirtualColumnsByPk(String virtualColumnsId) throws Exception;

  /**
   * 获取 虚拟列 所有数据
   */
  public Collection<VirtualColumns> getAllVirtualColumns() throws Exception;

  /**
   * 查询 虚拟列 数据
   */
  public Collection<VirtualColumns> queryVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws Exception;

  /**
   * 分页查询 虚拟列 数据
   */
  public Paging<VirtualColumns> pagingQueryVirtualColumns(Param param, VirtualColumnsQuery virtualColumnsQuery) throws Exception;

  /**
   * 通过主键获取 虚拟列 展示数据
   */
  public VirtualColumnsVO getVirtualColumnsVOByPk(String virtualColumnsId) throws Exception;

  /**
   * 获取 虚拟列 所有展示数据
   */
  public Collection<VirtualColumnsVO> getAllVirtualColumnsVO() throws Exception;

  /**
   * 查询 虚拟列 展示数据
   */
  public Collection<VirtualColumnsVO> queryVirtualColumnsVO(VirtualColumnsQuery virtualColumnsQuery) throws Exception;

  /**
   * 分页查询 虚拟列 展示数据
   */
  public Paging<VirtualColumnsVO> pagingQueryVirtualColumnsVO(Param param, VirtualColumnsQuery virtualColumnsQuery) throws Exception;

}
