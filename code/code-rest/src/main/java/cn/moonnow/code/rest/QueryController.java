package cn.moonnow.code.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.moonnow.code.entity.Query;
import cn.moonnow.code.query.QueryQuery;
import cn.moonnow.code.service.IQueryService;
import cn.moonnow.code.vo.QueryVO;
import cn.moonnow.tool.exception.ToolException;
import cn.moonnow.tool.rest.Responses;
import cn.moonnow.tool.rest.ToolController;
import cn.moonnow.tool.util.Param;
import cn.moonnow.tool.util.ToolUtil;
import lombok.extern.log4j.Log4j2;

/**
 * 查询 REST API
 */
@Log4j2
@RestController
@Scope("prototype")
@RequestMapping(value = { "code/query" })
public class QueryController extends ToolController {

  public static final String LOG = "QueryController";

  @Resource(name = "cn.moonnow.code.QueryService")
  private IQueryService queryService;

  /**
   * 保存 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  public Responses<Query> save(Param param, @RequestBody Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.saveQuery(query));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量保存 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  public Responses<Query> batchSave(Param param, @RequestBody List<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.batchSaveQuery(querys));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 修改 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  public Responses<Query> update(Param param, @RequestBody Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.updateQuery(query));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量修改 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  public Responses<Query> batchUpdate(Param param, @RequestBody List<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.batchUpdateQuery(querys));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 删除 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  public Responses<Query> remove(Param param, @RequestBody Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      queryService.removeQuery(query);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 批量删除 查询 信息
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  public Responses<Query> batchRemove(Param param, @RequestBody List<Query> querys) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + querys);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      queryService.batchRemoveQuery(querys);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 通过主键获取 查询 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  public Responses<Query> getByPk(Param param, @RequestBody Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + query);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(query.getQueryId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.getQueryByPk(query.getQueryId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 查询 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL })
  public Responses<Query> getAll(Param param, @RequestBody QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        responses.setData(queryService.getAllQuery());
      } else {
        responses.setData(queryService.queryQuery(queryQuery));
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
   * 分页获取 查询 数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING })
  public Responses<Query> paging(Param param, @RequestBody QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    Responses<Query> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        responses.setData(queryService.pagingQueryQuery(param, null));
      } else {
        responses.setData(queryService.pagingQueryQuery(param, queryQuery));
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
   * 通过主键获取 查询 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  public Responses<QueryVO> getVOByPk(Param param, @RequestBody Query query) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + query);
    }
    Responses<QueryVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(query)) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      if (ToolUtil.isNullStr(query.getQueryId())) {
        throw new ToolException(ToolException.E_PARAM_ERR);
      }
      responses.setData(queryService.getQueryVOByPk(query.getQueryId()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw e;
    }
    return responses;
  }

  /**
   * 获取 查询 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_ALL_VO })
  public Responses<QueryVO> getAllVO(Param param, @RequestBody QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    Responses<QueryVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        responses.setData(queryService.getAllQueryVO());
      } else {
        responses.setData(queryService.queryQueryVO(queryQuery));
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
   * 分页获取 查询 展示数据
   */
  @PostMapping(params = { ToolUtil.ACTION + ToolUtil.ACTION_PAGING_VO })
  public Responses<QueryVO> pagingVO(Param param, @RequestBody QueryQuery queryQuery) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug(ToolUtil.getLog(LOG));
      log.debug(ToolUtil.LOG + param);
      log.debug(ToolUtil.LOG + queryQuery);
    }
    Responses<QueryVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullEntityAllFieldValue(queryQuery)) {
        responses.setData(queryService.pagingQueryQueryVO(param, null));
      } else {
        responses.setData(queryService.pagingQueryQueryVO(param, queryQuery));
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
