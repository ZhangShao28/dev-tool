package cn.moonnow.dict.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import cn.moonnow.dict.entity.DictValue;
import cn.moonnow.dict.query.DictValueQuery;
import cn.moonnow.dict.service.IDictValueService;
import cn.moonnow.dict.vo.DictValueVO;
import lombok.extern.log4j.Log4j2;

/**
 * 数据字典值 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "dict/dictValue" })
public class DictValueController extends ToolController {

  public static final String LOG = "DictValueController";

  @Resource(name = "cn.moonnow.dict.DictValueService")
  private IDictValueService dictValueService;

  /**
   * 保存 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<DictValue> save(Param param, @RequestBody DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValue);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.saveDictValue(dictValue));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<DictValue> batchSave(Param param, @RequestBody List<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValues);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.batchSaveDictValue(dictValues));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<DictValue> update(Param param, @RequestBody DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValue);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.updateDictValue(dictValue));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<DictValue> batchUpdate(Param param, @RequestBody List<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValues);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.batchUpdateDictValue(dictValues));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<DictValue> remove(Param param, @RequestBody DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValue);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dictValueService.removeDictValue(dictValue);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 数据字典值 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<DictValue> batchRemove(Param param, @RequestBody List<DictValue> dictValues) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValues);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dictValues)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dictValueService.batchRemoveDictValue(dictValues);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 数据字典值 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<DictValue> getByPk(Param param, @RequestBody DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValue);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.getDictValueByPk(dictValue.getDictValueId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据字典值 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<DictValue> getAll(Param param, @RequestBody DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValueQuery)) {
        responses.setData(dictValueService.getAllDictValue());
      } else {
        responses.setData(dictValueService.queryDictValue(dictValueQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 数据字典值 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<DictValue> paging(Param param, @RequestBody DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    Responses<DictValue> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValueQuery)) {
        responses.setData(dictValueService.pagingQueryDictValue(param, null));
      } else {
        responses.setData(dictValueService.pagingQueryDictValue(param, dictValueQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 数据字典值 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<DictValueVO> getVOByPk(Param param, @RequestBody DictValue dictValue) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValue);
    }
    Responses<DictValueVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValue)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dictValue.getDictValueId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictValueService.getDictValueVOByPk(dictValue.getDictValueId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据字典值 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<DictValueVO> getAllVO(Param param, @RequestBody DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    Responses<DictValueVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValueQuery)) {
        responses.setData(dictValueService.getAllDictValueVO());
      } else {
        responses.setData(dictValueService.queryDictValueVO(dictValueQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 分页获取 数据字典值 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<DictValueVO> pagingVO(Param param, @RequestBody DictValueQuery dictValueQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictValueQuery);
    }
    Responses<DictValueVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictValueQuery)) {
        responses.setData(dictValueService.pagingQueryDictValueVO(param, null));
      } else {
        responses.setData(dictValueService.pagingQueryDictValueVO(param, dictValueQuery));
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

}
