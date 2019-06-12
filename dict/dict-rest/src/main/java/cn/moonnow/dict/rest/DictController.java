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
import cn.moonnow.dict.entity.Dict;
import cn.moonnow.dict.query.DictQuery;
import cn.moonnow.dict.service.IDictService;
import cn.moonnow.dict.vo.DictVO;
import lombok.extern.log4j.Log4j2;

/**
 * 数据字典 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "dict/dict" })
public class DictController extends ToolController {

  public static final String LOG = "DictController";

  @Resource(name = "cn.moonnow.dict.DictService")
  private IDictService dictService;

  /**
   * 保存 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Dict> save(Param param, @RequestBody Dict dict) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dict);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dict)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.saveDict(dict));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Dict> batchSave(Param param, @RequestBody List<Dict> dicts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dicts);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dicts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.batchSaveDict(dicts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Dict> update(Param param, @RequestBody Dict dict) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dict);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dict)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.updateDict(dict));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Dict> batchUpdate(Param param, @RequestBody List<Dict> dicts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dicts);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dicts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.batchUpdateDict(dicts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Dict> remove(Param param, @RequestBody Dict dict) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dict);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dict)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dictService.removeDict(dict);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 数据字典 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Dict> batchRemove(Param param, @RequestBody List<Dict> dicts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dicts);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(dicts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      dictService.batchRemoveDict(dicts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 数据字典 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Dict> getByPk(Param param, @RequestBody Dict dict) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dict);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dict)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dict.getDictId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.getDictByPk(dict.getDictId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据字典 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Dict> getAll(Param param, @RequestBody DictQuery dictQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictQuery);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictQuery)) {
        responses.setData(dictService.getAllDict());
      } else {
        responses.setData(dictService.queryDict(dictQuery));
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
   * 分页获取 数据字典 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Dict> paging(Param param, @RequestBody DictQuery dictQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictQuery);
    }
    Responses<Dict> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictQuery)) {
        responses.setData(dictService.pagingQueryDict(param, null));
      } else {
        responses.setData(dictService.pagingQueryDict(param, dictQuery));
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
   * 通过主键获取 数据字典 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<DictVO> getVOByPk(Param param, @RequestBody Dict dict) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dict);
    }
    Responses<DictVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dict)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(dict.getDictId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(dictService.getDictVOByPk(dict.getDictId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 数据字典 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<DictVO> getAllVO(Param param, @RequestBody DictQuery dictQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictQuery);
    }
    Responses<DictVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictQuery)) {
        responses.setData(dictService.getAllDictVO());
      } else {
        responses.setData(dictService.queryDictVO(dictQuery));
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
   * 分页获取 数据字典 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<DictVO> pagingVO(Param param, @RequestBody DictQuery dictQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + dictQuery);
    }
    Responses<DictVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(dictQuery)) {
        responses.setData(dictService.pagingQueryDictVO(param, null));
      } else {
        responses.setData(dictService.pagingQueryDictVO(param, dictQuery));
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
