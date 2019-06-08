package cn.moonnow.code.service;

import java.util.Collection;

import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.vo.SortVO;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;

/**
 * 排序 服务接口
 */
public interface ISortService {

  /**
   * 保存 排序 信息
   */
  public Sort saveSort(Sort sort) throws Exception;

  /**
   * 批量保存 排序 信息
   */
  public Collection<Sort> batchSaveSort(Collection<Sort> sorts) throws Exception;

  /**
   * 修改 排序 信息
   */
  public Sort updateSort(Sort sort) throws Exception;

  /**
   * 批量修改 排序 信息
   */
  public Collection<Sort> batchUpdateSort(Collection<Sort> sorts) throws Exception;

  /**
   * 删除 排序 信息
   */
  public void removeSort(Sort sort) throws Exception;

  /**
   * 批量删除 排序 信息
   */
  public void batchRemoveSort(Collection<Sort> sorts) throws Exception;

  /**
   * 获取 排序 数据量
   */
  public Long getCountSort(SortQuery sortQuery) throws Exception;

  /**
   * 通过主键获取 排序 数据
   */
  public Sort getSortByPk(String sortId) throws Exception;

  /**
   * 获取 排序 所有数据
   */
  public Collection<Sort> getAllSort() throws Exception;

  /**
   * 查询 排序 数据
   */
  public Collection<Sort> querySort(SortQuery sortQuery) throws Exception;

  /**
   * 分页查询 排序 数据
   */
  public Paging<Sort> pagingQuerySort(Param param, SortQuery sortQuery) throws Exception;

  /**
   * 通过主键获取 排序 展示数据
   */
  public SortVO getSortVOByPk(String sortId) throws Exception;

  /**
   * 获取 排序 所有展示数据
   */
  public Collection<SortVO> getAllSortVO() throws Exception;

  /**
   * 查询 排序 展示数据
   */
  public Collection<SortVO> querySortVO(SortQuery sortQuery) throws Exception;

  /**
   * 分页查询 排序 展示数据
   */
  public Paging<SortVO> pagingQuerySortVO(Param param, SortQuery sortQuery) throws Exception;

}
