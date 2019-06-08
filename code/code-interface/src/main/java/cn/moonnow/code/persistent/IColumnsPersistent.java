package cn.moonnow.code.persistent;

import java.util.Collection;

import cn.moonnow.code.entity.Columns;
import cn.moonnow.code.query.ColumnsQuery;
import cn.moonnow.code.vo.ColumnsVO;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;

/**
 * 列 持久化接口
 */
public interface IColumnsPersistent {

  public static final String TABLE_NAME = "PRO_COLUMNS";

  /**
   * 保存 列 信息
   */
  public Columns saveColumns(Columns columns) throws Exception;

  /**
   * 批量保存 列 信息
   */
  public Collection<Columns> batchSaveColumns(Collection<Columns> columnss) throws Exception;

  /**
   * 修改 列 信息
   */
  public Columns updateColumns(Columns columns) throws Exception;

  /**
   * 批量修改 列 信息
   */
  public Collection<Columns> batchUpdateColumns(Collection<Columns> columnss) throws Exception;

  /**
   * 删除 列 信息
   */
  public void removeColumns(Columns columns) throws Exception;

  /**
   * 批量删除 列 信息
   */
  public void batchRemoveColumns(Collection<Columns> columnss) throws Exception;

  /**
   * 获取 列 数据量
   */
  public Long getCountColumns(ColumnsQuery columnsQuery) throws Exception;

  /**
   * 通过主键获取 列 数据
   */
  public Columns getColumnsByPk(String columnsId) throws Exception;

  /**
   * 获取 列 所有数据
   */
  public Collection<Columns> getAllColumns() throws Exception;

  /**
   * 查询 列 数据
   */
  public Collection<Columns> queryColumns(ColumnsQuery columnsQuery) throws Exception;

  /**
   * 分页查询 列 数据
   */
  public Paging<Columns> pagingQueryColumns(Param param, ColumnsQuery columnsQuery) throws Exception;

  /**
   * 通过主键获取 列 展示数据
   */
  public ColumnsVO getColumnsVOByPk(String columnsId) throws Exception;

  /**
   * 获取 列 所有展示数据
   */
  public Collection<ColumnsVO> getAllColumnsVO() throws Exception;

  /**
   * 查询 列 展示数据
   */
  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws Exception;

  /**
   * 分页查询 列 展示数据
   */
  public Paging<ColumnsVO> pagingQueryColumnsVO(Param param, ColumnsQuery columnsQuery) throws Exception;

}
