package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.Sort;
import cn.moonnow.code.query.SortQuery;
import cn.moonnow.code.service.ISortService;
import cn.moonnow.code.vo.SortVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 排序 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/sort" })
public class SortController extends ToolController {

  public static final String LOG = "SortController";

  @Resource(name = "cn.moonnow.code.SortService")
  private ISortService sortService;

  /**
   * 保存 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Sort> save(Param param, @RequestBody Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.saveSort(sort));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Sort> batchSave(Param param, @RequestBody List<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.batchSaveSort(sorts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Sort> update(Param param, @RequestBody Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.updateSort(sort));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Sort> batchUpdate(Param param, @RequestBody List<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.batchUpdateSort(sorts));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Sort> remove(Param param, @RequestBody Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      sortService.removeSort(sort);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 排序 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Sort> batchRemove(Param param, @RequestBody List<Sort> sorts) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sorts);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      sortService.batchRemoveSort(sorts);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 排序 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Sort> getByPk(Param param, @RequestBody Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sort);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.getSortByPk(sort.getSortId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 排序 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Sort> getAll(Param param, @RequestBody SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        responses.setData(sortService.getAllSort());
      } else {
        responses.setData(sortService.querySort(sortQuery));
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
   * 分页获取 排序 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Sort> paging(Param param, @RequestBody SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
    }
    Responses<Sort> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        responses.setData(sortService.pagingQuerySort(param, null));
      } else {
        responses.setData(sortService.pagingQuerySort(param, sortQuery));
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
   * 通过主键获取 排序 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<SortVO> getVOByPk(Param param, @RequestBody Sort sort) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sort);
    }
    Responses<SortVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(sortService.getSortVOByPk(sort.getSortId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 排序 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<SortVO> getAllVO(Param param, @RequestBody SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
    }
    Responses<SortVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        responses.setData(sortService.getAllSortVO());
      } else {
        responses.setData(sortService.querySortVO(sortQuery));
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
   * 分页获取 排序 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<SortVO> pagingVO(Param param, @RequestBody SortQuery sortQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + sortQuery);
    }
    Responses<SortVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(sortQuery)) {
        responses.setData(sortService.pagingQuerySortVO(param, null));
      } else {
        responses.setData(sortService.pagingQuerySortVO(param, sortQuery));
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
