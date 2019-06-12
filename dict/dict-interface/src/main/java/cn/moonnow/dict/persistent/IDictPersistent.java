package cn.moonnow.dict.persistent;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.dict.entity.Dict;
import cn.moonnow.dict.query.DictQuery;
import cn.moonnow.dict.vo.DictVO;

/**
 * 数据字典 持久化接口
 */
public interface IDictPersistent {

  public static final String TABLE_NAME = "DEV_DICT";

  /**
   * 保存 数据字典 信息
   */
  public Dict saveDict(Dict dict) throws Exception;

  /**
   * 批量保存 数据字典 信息
   */
  public Collection<Dict> batchSaveDict(Collection<Dict> dicts) throws Exception;

  /**
   * 修改 数据字典 信息
   */
  public Dict updateDict(Dict dict) throws Exception;

  /**
   * 批量修改 数据字典 信息
   */
  public Collection<Dict> batchUpdateDict(Collection<Dict> dicts) throws Exception;

  /**
   * 删除 数据字典 信息
   */
  public void removeDict(Dict dict) throws Exception;

  /**
   * 批量删除 数据字典 信息
   */
  public void batchRemoveDict(Collection<Dict> dicts) throws Exception;

  /**
   * 获取 数据字典 数据量
   */
  public Long getCountDict(DictQuery dictQuery) throws Exception;

  /**
   * 通过主键获取 数据字典 数据
   */
  public Dict getDictByPk(String dictId) throws Exception;

  /**
   * 获取 数据字典 所有数据
   */
  public Collection<Dict> getAllDict() throws Exception;

  /**
   * 查询 数据字典 数据
   */
  public Collection<Dict> queryDict(DictQuery dictQuery) throws Exception;

  /**
   * 分页查询 数据字典 数据
   */
  public Paging<Dict> pagingQueryDict(Param param, DictQuery dictQuery) throws Exception;

  /**
   * 通过主键获取 数据字典 展示数据
   */
  public DictVO getDictVOByPk(String dictId) throws Exception;

  /**
   * 获取 数据字典 所有展示数据
   */
  public Collection<DictVO> getAllDictVO() throws Exception;

  /**
   * 查询 数据字典 展示数据
   */
  public Collection<DictVO> queryDictVO(DictQuery dictQuery) throws Exception;

  /**
   * 分页查询 数据字典 展示数据
   */
  public Paging<DictVO> pagingQueryDictVO(Param param, DictQuery dictQuery) throws Exception;

}
