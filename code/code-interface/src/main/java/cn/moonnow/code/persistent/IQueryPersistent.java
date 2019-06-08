package cn.moonnow.code.persistent;

import java.util.Collection;

import cn.moonnow.code.entity.Query;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.vo.QueryVO;
import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;

/**
 * 查询 持久化接口
 */
public interface IQueryPersistent {

  public static final String TABLE_NAME = "PRO_QUERY";

  /**
   * 保存 查询 信息
   */
  public Query saveQuery(Query query) throws Exception;

  /**
   * 批量保存 查询 信息
   */
  public Collection<Query> batchSaveQuery(Collection<Query> querys) throws Exception;

  /**
   * 修改 查询 信息
   */
  public Query updateQuery(Query query) throws Exception;

  /**
   * 批量修改 查询 信息
   */
  public Collection<Query> batchUpdateQuery(Collection<Query> querys) throws Exception;

  /**
   * 删除 查询 信息
   */
  public void removeQuery(Query query) throws Exception;

  /**
   * 批量删除 查询 信息
   */
  public void batchRemoveQuery(Collection<Query> querys) throws Exception;

  /**
   * 获取 查询 数据量
   */
  public Long getCountQuery(QueryQuery queryQuery) throws Exception;

  /**
   * 通过主键获取 查询 数据
   */
  public Query getQueryByPk(String queryId) throws Exception;

  /**
   * 获取 查询 所有数据
   */
  public Collection<Query> getAllQuery() throws Exception;

  /**
   * 查询 查询 数据
   */
  public Collection<Query> queryQuery(QueryQuery queryQuery) throws Exception;

  /**
   * 分页查询 查询 数据
   */
  public Paging<Query> pagingQueryQuery(Param param, QueryQuery queryQuery) throws Exception;

  /**
   * 通过主键获取 查询 展示数据
   */
  public QueryVO getQueryVOByPk(String queryId) throws Exception;

  /**
   * 获取 查询 所有展示数据
   */
  public Collection<QueryVO> getAllQueryVO() throws Exception;

  /**
   * 查询 查询 展示数据
   */
  public Collection<QueryVO> queryQueryVO(QueryQuery queryQuery) throws Exception;

  /**
   * 分页查询 查询 展示数据
   */
  public Paging<QueryVO> pagingQueryQueryVO(Param param, QueryQuery queryQuery) throws Exception;

}
