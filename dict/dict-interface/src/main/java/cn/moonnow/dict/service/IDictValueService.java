package cn.moonnow.dict.service;

import java.util.Collection;

import cn.moonnow.tool.util.Paging;
import cn.moonnow.tool.util.Param;
import cn.moonnow.dict.entity.DictValue;
import cn.moonnow.dict.query.DictValueQuery;
import cn.moonnow.dict.vo.DictValueVO;

/**
 * 数据字典值 服务接口
 */
public interface IDictValueService {

  /**
   * 保存 数据字典值 信息
   */
  public DictValue saveDictValue(DictValue dictValue) throws Exception;

  /**
   * 批量保存 数据字典值 信息
   */
  public Collection<DictValue> batchSaveDictValue(Collection<DictValue> dictValues) throws Exception;

  /**
   * 修改 数据字典值 信息
   */
  public DictValue updateDictValue(DictValue dictValue) throws Exception;

  /**
   * 批量修改 数据字典值 信息
   */
  public Collection<DictValue> batchUpdateDictValue(Collection<DictValue> dictValues) throws Exception;

  /**
   * 删除 数据字典值 信息
   */
  public void removeDictValue(DictValue dictValue) throws Exception;

  /**
   * 批量删除 数据字典值 信息
   */
  public void batchRemoveDictValue(Collection<DictValue> dictValues) throws Exception;

  /**
   * 获取 数据字典值 数据量
   */
  public Long getCountDictValue(DictValueQuery dictValueQuery) throws Exception;

  /**
   * 通过主键获取 数据字典值 数据
   */
  public DictValue getDictValueByPk(String dictValueId) throws Exception;

  /**
   * 获取 数据字典值 所有数据
   */
  public Collection<DictValue> getAllDictValue() throws Exception;

  /**
   * 查询 数据字典值 数据
   */
  public Collection<DictValue> queryDictValue(DictValueQuery dictValueQuery) throws Exception;

  /**
   * 分页查询 数据字典值 数据
   */
  public Paging<DictValue> pagingQueryDictValue(Param param, DictValueQuery dictValueQuery) throws Exception;

  /**
   * 通过主键获取 数据字典值 展示数据
   */
  public DictValueVO getDictValueVOByPk(String dictValueId) throws Exception;

  /**
   * 获取 数据字典值 所有展示数据
   */
  public Collection<DictValueVO> getAllDictValueVO() throws Exception;

  /**
   * 查询 数据字典值 展示数据
   */
  public Collection<DictValueVO> queryDictValueVO(DictValueQuery dictValueQuery) throws Exception;

  /**
   * 分页查询 数据字典值 展示数据
   */
  public Paging<DictValueVO> pagingQueryDictValueVO(Param param, DictValueQuery dictValueQuery) throws Exception;

}
